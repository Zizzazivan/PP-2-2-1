package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   public void addCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, int series) {
      System.out.println(1);
      sessionFactory.getCurrentSession();
      System.out.println(2);
      Query query = sessionFactory.getCurrentSession()
              .createQuery("select u.user from Car u where u.series = :series and u.model = :model");
      query.setParameter("series", series);
      query.setParameter("model", model);
      return (User) query.getSingleResult();
//      System.out.println(userId);
//      Query query1 = sessionFactory.getCurrentSession().createQuery("from User u where u.id = :id");
//      query1.setParameter("id", userId);
//      System.out.println(query1.getSingleResult());
//      return (User) query1.getSingleResult();

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
