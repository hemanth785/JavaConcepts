package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

/*
 * Using thenCompose and thenCombine, we can fetch combine results from limited number of Future calls
 * 
 * - If we want to combine results from arbitary(n) number of future calls, then we can make use of 'allOf()' function in 'CompletableFuture'
 * 
 * CompletableFuture.allOf is used in scenarios when you have a List of independent futures 
 * that you want to run in parallel and do something after all of them are complete.
 *
 * Let’s say that you want to download the contents of 100 different web pages of a website. 
 * You can do this operation sequentially but this will take a lot of time. 
 * So, you have written a function which takes a web page link, and returns a CompletableFuture, 
 * i.e. It downloads the web page’s content asynchronously -
 */
public class A08_AllOf {
  static CompletableFuture<String> downloadWebpage(String pageLink){
    return CompletableFuture.supplyAsync(() -> {
      //TODO: Logic to fetch webpage 
      return "webpage response";
    });
  }

  public static void main(String[] args) {
    //List of 100 webpage links
    List<String> webPageLinks = Arrays.asList(new String[]{"url -1", "url-2", "url-3"});

    // 1. Download contents of all the web pages asynchronously
    List<CompletableFuture<String>> pageContentFutures = webPageLinks.stream()
                                                          .map(webPageLink -> {
                                                            return downloadWebpage(webPageLink);
                                                          })
                                                          .collect(Collectors.toList());

    // 2. Create a combined future using allOf()
    CompletableFuture<Void> allOfFutures = CompletableFuture.allOf(
        pageContentFutures.toArray(new CompletableFuture[pageContentFutures.size()])
      );

    // 3. When all the Futures are completed, call `future.join()` to get their results and collect the results in a list
    CompletableFuture<List<String>> allPageContentsFuture = allOfFutures.thenApply((unused) -> { //when all the futures complets their execution
      return pageContentFutures.stream()
                                .map(pageContentFuture -> pageContentFuture.join()) //Get result from each future | Note: It is recommended to use join() instead of get()
                                .collect(Collectors.toList());
    });

    //4. Count the number of webpages, which contains substring 'tiger'
    CompletableFuture<Long> countFuture = allPageContentsFuture.thenApply(pageContents -> {
      return pageContents.stream()
                          .filter(pageContent -> pageContent.contains("tiger"))
                          .count();
    });

    // 5. Fetch count value from future
    long count = countFuture.join(); //we can also use get here
    System.out.println("webpages having word count = "+ count);
  }

}
