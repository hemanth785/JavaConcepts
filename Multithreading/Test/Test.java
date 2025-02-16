package Test;

public class Test {
  public static void main(String args[]){
    User user1 = new User("Hemanth");
    User user2 = new User("Rahul");

    System.out.println(user1.name+" - "+user1.age);
    System.out.println(user2.name+" - "+user2.age);

    System.out.println("----After update-----");
    user1.age = 35;
    user1.name = "Hemanth2";
    System.out.println(user1.name+" - "+user1.age);
    System.out.println(user2.name+" - "+user2.age);
  }
}

class User {
  public String name;
  public static int age = 27;

  User(String name){
    this.name = name;
  }
}
