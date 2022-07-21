package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserByCar(String carModel, int carSeries) {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                "select u from User u join Car c on u.car = c.id " +
                        "where c.model = :model and c.series = :series");
        query.setParameter("model", carModel);
        query.setParameter("series", carSeries);
        return (User) query.getSingleResult();
    }
}
