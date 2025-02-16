package A09_Fork_Join_Pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/*
 * ForkJoinPool can be used, where we need to split the single task into multiple subtasks, 
 * and these subtasks can be processed by multipel threads again (This is called work stealing)
 * 
 * - ForkJoin pool threads fetch the tasks from 2 queues
 *    1. Task submission queue (main queue) - This is where the task submitted to threadpool will be sitting before gettign processed
 *    2. Subtask Queue (Work stealing queue) - When thread picks up any task, and if that task can be devided into subtasks, those subtasks will be pushed to this queue
 * 
 * Note: - Each thread will have its won 'work stealing queue',
 *       - When thread completes its task, it'll first check in its own subtask queue(work stealing queue)
 *       - If emtpy, then it'll check in the submission task queue
 *       - If thats also empty, it'll pick task from other 'work stealing queue' of other Threads
 * 
 * To create a Proper forkJoin pool, follow below steps
 *  1. Create a Task which is extended from class 'RecursiveTask' or 'RecursiveAction' 
 *  2. Override the method 'compute()' - this is method is same as the 'run()' method
 *      - all the task devision(fork) and joining of results from different subtasks will be implemented in this method only
 *  3. create Fork Join Pool -> ForkJoinPool pool = ForkJoinPool.commonPool()
 *  4. Submit the tasks to this common pool
 */

public class ForkJoinProper {
  public static void main(String[] args) {
    ComputeSumTask task = new ComputeSumTask(1, 100);
    
    ForkJoinPool pool = ForkJoinPool.commonPool();
    Future<Integer> future = pool.submit(task);
    
    try {
      System.out.println(future.get());

    } catch (Exception e) {}
    
  }
  
}

//creating a main task
class ComputeSumTask extends RecursiveTask<Integer> {
  int start;
  int end;

  ComputeSumTask(int start, int end){
    this.start = start;
    this.end = end;
  }

  public Integer compute(){
    
    if(end-start <= 4){
      //Base condition

      int totalSum = 0;
      for(int i=start; i<=end; i++){
        totalSum += i;
      }

      return totalSum;
    } else {
      //Recursive condition (split task into subtasks)
      int mid = (start+end)/2;

      //create 2 more subtasks
      ComputeSumTask leftTask = new ComputeSumTask(start, mid);
      ComputeSumTask rightTask = new ComputeSumTask(mid+1, end);

      //Sumbit these subtasks to work stealing queue
      leftTask.fork();
      rightTask.fork();

      //get the resuls of subtasks (here controller will wait till these subtasks are completed by some thread)
      // The reason why we are calling join is, these subtasks might get devided into further subtasks, 
      // if thats the case, all those subtask values should he joined before returned here
      int leftResult = leftTask.join();
      int rightResult = rightTask.join();

      //combine results from all subtasks

      return leftResult + rightResult;
    }
  }
}
