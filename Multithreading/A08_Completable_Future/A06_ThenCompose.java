package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;

/*
 * Here the goal is to fetch the userDetails first, and after getting userDetails
 *  We need to get credit rating of user, by passing user details - asynchronously
 */
public class A06_ThenCompose {
  //async function to fetch User detail
  static CompletableFuture<User> getUserDetail(String userId){
    return CompletableFuture.supplyAsync(() -> {
      return UserService.getUserDetails(userId);
    });
  }

  //async function to fetch User credit rating
  static CompletableFuture<Double> getCreditRating(User user){
    return CompletableFuture.supplyAsync(() ->{
      return CreditRatingService.getCreditRating(user);
    });
  }
   
  public static void main(String[] args) {

    // Solution 1: Using 'thenApply'
    CompletableFuture<CompletableFuture<Double>> result = getUserDetail("user1")
    .thenApply((user) ->{
      return getCreditRating(user);
    });

    try {
      double creditRaging = result.get().get();
      System.out.println("credit rating 1: "+ creditRaging);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // Note: as we can see above, current approach returns nested objects of CompletableFuture,
    // and if we have to call multiple such functions, then there would be lot of nested objects

    

    //Solution 2: using 'thenCompose' - proper solution
    // It returns final result to be a top-level Future
    CompletableFuture<Double> future = getUserDetail("user-1")
    .thenCompose(user -> {
      return getCreditRating(user);
    });

    try {
      double creditRaging = future.get();
      System.out.println("credit rating 2: "+ creditRaging);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
}












//-------- Helper classes -------
class User{

}

class UserService{
  public static User getUserDetails(String userId){
    return new User();
  }
}

class CreditRatingService{
  public static double getCreditRating(User user){
    return 10.2;
  }
}
