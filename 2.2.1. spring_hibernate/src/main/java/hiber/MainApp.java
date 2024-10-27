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

      User user1 = new User("Ivan", "Smych", "gavanaclub@mail.ru");
      userService.add(user1);
      User user2 = new User("George", "Field", "greentea@mail.ru");
      userService.add(user2);
      User user3 = new User("Askold", "Zapashny", "eyeofthetiger322@mail.ru");
      userService.add(user3);
      User user4 = new User("Michail", "Gorshenev", "donotusedrugs@mail.ru");
      userService.add(user4);
      Car car1 = new Car("BMW X5", 5, user2);
      userService.addCar(car1);
      Car car2 = new Car("Audi A3", 3, user3);
      userService.addCar(car2);
      Car car3 = new Car("LadaGranta", 1, user4);
      userService.addCar(car3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      userService.listCars().stream().forEach(car -> {
         System.out.println("Id = "+car.getId());
         System.out.println("Model = "+car.getModel());
         System.out.println("Series = "+car.getSeries());
         System.out.println("User = "+car.getUser().getFirstName() + " " + car.getUser().getLastName());
         System.out.println();
              });

      System.out.println("Владелец машины: " + userService.getUserByCar("LadaGranta", 1).getLastName() + " " + userService.getUserByCar("LadaGranta", 1).getFirstName());


      context.close();
   }
}
