
// Abstract class for the User
// The User class is an abstract class that represents a user in the system. It has the following attributes:
// name: a string representing the name of the user
// id: a long representing the unique identifier of the user
// password: a string representing the password of the user
// isConnected: a boolean indicating whether the user is currently connected to the system
// notifications: a list of strings representing the notifications received by the user
// The User class has the following methods:
// A constructor that takes the name, id, and password of the user and initializes the attributes
// A login method that takes the id and password of the user and logs the user into the system if the id and password match the user's id and password
// A logout method that logs the user out of the system

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Abstract User class
public abstract class User {
    protected final String name;
    protected final Long id;
    protected String password;
    protected boolean isConnected=false;
    protected List<String> notifications = new ArrayList<>();

    public User(String name, Long id, String password){
        while ((id<19999999)||(id>1000000000))
      {System.out.println  ("not valid id please enter a valid id");}
        while ((password.length()<4)||(password.length()>10))
        {System.out.println("not valid password-shoud be between 4 and 10 characters");}
        this.id=id;
        this.name=name;
        this.password=password;
        isConnected =false;
    }
    public void login(Long id,String password){
      if (this.password.equals(password)&&Objects.equals(id, this.id)){
        isConnected =true;
          System.out.println("you connected");
      }
      else {
          System.out.println("the password or the id is not equal");
          isConnected =false;
      }
    }

    public void logout() {
        this.isConnected = false;
        System.out.println(name + " logged out.");
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void addNotification(String notification) {
        notifications.add(notification);
    }

    public void printNotifications() {
        for (String notification : notifications) {
            System.out.println(notification);
        }
    }

    public abstract void update(String message);
}