package Repository;

import Domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.xml.registry.infomodel.User;
import java.util.List;

public class DataRepo implements IRepository<Users, String> {
    private EntityManager entityManager;

    public DataRepo() {
        this.entityManager = Persistence
                .createEntityManagerFactory("UserManagemen")
                .createEntityManager();
    }

    @Override
    public Users save(Users users) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(users);
        this.entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public void update(Users users) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(users);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Users> findAll() {
        this.entityManager.getTransaction().begin();
        List<Users> listOfUsers = this.entityManager
                .createQuery("SELECT u FROM Users u", Users.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return listOfUsers;
    }

    @Override
    public Users findById(String id) {
        Users user = this.entityManager
                .createQuery("SELECT u FROM Users u WHERE u.id = :id", Users.class)
                .setParameter("id", id)
                .getSingleResult();

        return user;
    }

    @Override
    public void delete(String id) {
        Users user = this.entityManager
                .createQuery("SELECT u FROM Users u WHERE u.id = :id", Users.class)
                .setParameter("id", id)
                .getSingleResult();

        this.entityManager.remove(user);
    }
}
