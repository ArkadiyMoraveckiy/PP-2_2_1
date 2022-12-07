package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("user1", "Lastname1", "user1@gmail.com");
      User user2 = new User("user2", "Lastname2", "user2@gmail.com");
      User user3 = new User("user3", "Lastname3", "user3@gmail.com");
      User user4 = new User("user4", "Lastname4", "user4@gmail.com");

      Car car1 = new Car("Car1", 1);
      Car car2 = new Car("Car2", 2);
      Car car3 = new Car("Car3", 3);
      Car car4 = new Car("Car4", 4);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         //System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println(user.getCar());
         //System.out.println("Last Name = "+user.getLastName());
         //System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      //Достаем юзера владеющего машиной по модели и серри
      System.out.println(userService.getUserByCar("Car3", 3));

      context.close();
   }
}
