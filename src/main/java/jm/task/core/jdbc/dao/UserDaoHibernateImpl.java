package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;
    private Session session = null;

    public UserDaoHibernateImpl() {
        this.sessionFactory = getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `Users` (\n" +
                "  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  `name` CHAR(40) NOT NULL,\n" +
                "  `lastname` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT UNSIGNED NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");";

        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";

        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.save(new User (name, lastName, age));

        session.getTransaction().commit();
    }

    @Override
    public void removeUserById(long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createQuery("delete User where id = " + id ).executeUpdate();

        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<User> users = session.createQuery("from User", User.class)
                .getResultList();

        session.getTransaction().commit();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.createSQLQuery("TRUNCATE TABLE Users").addEntity(User.class).executeUpdate();

        session.getTransaction().commit();
    }
}
