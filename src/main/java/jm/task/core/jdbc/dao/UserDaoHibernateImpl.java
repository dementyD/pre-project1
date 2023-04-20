package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session = Util.getSessionFactory().openSession();


    @Override
    public void createUsersTable() {
        session.getTransaction().begin();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS dbUsers " +
                "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR (40) NOT NULL," +
                "lastName VARCHAR (40) NOT NULL," +
                "age TINYINT UNSIGNED NOT NULL)").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        session.getTransaction().begin();
        session.createSQLQuery("DROP TABLE IF EXISTS dbUsers").executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session.getTransaction().begin();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        Criteria criteria = session.createCriteria(User.class);
        session.getTransaction().begin();
        User user = (User) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session.getTransaction().begin();
        Criteria criteria = session.createCriteria(User.class);
        List<User> result = criteria.list();
        session.getTransaction().commit();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        session.getTransaction().begin();
        session.createSQLQuery("TRUNCATE dbUsers").executeUpdate();
        session.getTransaction().commit();
    }
}
