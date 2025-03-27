package A03_ThreadLocal;

public class ProperThreadLocal {
  public static void main(String[] args) {
     // UserService service = new UserService();
     UserService2 service = new UserService2();

     Thread t1 = new Thread(() -> {
       service.setUser("Alice");
       service.processUser();
     });
 
     Thread t2 = new Thread(() -> {
       service.setUser("Bob");
       service.processUser();
     });
 
     Thread t3 = new Thread(() -> {
       service.setUser("Mark");
       service.processUser();
     });
 
     t1.start();
     t2.start();
     t3.start();
  }
}


// Scenario 1 - "WITHOUT" Threadlocal
class UserService {
  String user; //Issue here

  public void setUser(String user){
    this.user = user;
  }

  public void processUser() {
    logUser();
  }

  public void logUser() {
    try { 
      Thread.sleep(2000);   
    } catch (Exception e) {}

    System.out.println(Thread.currentThread().getName() + 
      " - Processing user: " + user);
  }

  /*
   * Output: 
   * Thread-1 - Processing user: Mark
   * Thread-0 - Processing user: Mark
   * Thread-2 - Processing user: Mark
   */
}


// Scenario 1 - WITH Threadlocal
class UserService2 {
  // String user; 
  ThreadLocal<String> user = new ThreadLocal<>();

  public void setUser(String user){
    this.user.set(user);
  }

  public void processUser() {
    logUser();
  }

  public void logUser() {
    try { 
      Thread.sleep(2000);   
    } catch (Exception e) {}

    System.out.println(Thread.currentThread().getName() + 
      " - Processing user: " + user.get());
  }

  /*
   * Output: 
   * Thread-2 - Processing user: Mark
   * Thread-0 - Processing user: Alice
   * Thread-1 - Processing user: Bob
   */
}