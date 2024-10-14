package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
 * We know that, by using 'thenCompose' we can get the final result of multiple functions which returns 'CompletableFuture'
 * by running it sequentially (or in-order)
 * 
 * But if we want to run two futures, parallelly and then do some operations after both the completable future returns some value
 *  then we have to make use of 'thenCombine'
 */
public class A07_ThenCombine {  
  //Function to retreive weight
  static CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
      try {
          TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException err) {
        throw new IllegalStateException(err);
      }
      return 65.0;
  });

  //Function to retreive height
  static CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
      try {
          TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException err2) {
        throw new IllegalStateException(err2);
      }
      return 177.8;
  });

  public static void main(String[] args) {
    // Here we need to calculate BMI after getting both height and weight,
    CompletableFuture<Double> combinedFuture = weightInKgFuture
    .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
      Double heightInMeter = heightInCm/100;
      return weightInKg/(heightInMeter*heightInMeter);
    });

    try {
      
      double bmi = combinedFuture.get();
      System.out.println("Your BMI is - " + bmi);

    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  

}
