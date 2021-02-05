package APP.Repository;

import APP.Domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class DataRepo implements IRepository<Users, String> {
    private static final String ORDER_BY_ASCENDING_BIRTH_DATE = "birth_date_asc";
    private static final String ORDER_BY_DESCENDING_BIRTH_DATE = "birth_date_desc";
    private static final String ORDER_BY_ASCENDING_LAST_NAME = "last_name_asc";
    private static final String ORDER_BY_DESCENDING_LAST_NAME = "last_name_desc";
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
    public List<Users> findAll(String filter, String searchWord) {
        this.entityManager.getTransaction().begin();
        List<Users> listOfUsers = this.filterResult(filter, searchWord);
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


    // It filters for one parameter in this implementation.
    private List<Users> filterResult(String filter, String searchWord) {
        List<Users> result = new ArrayList<>();
        String toSearch = null;

        if (searchWord == null || searchWord.length() < 1){
            return this.cleanFilter(filter);
        } else {
            // TODO sanitize searchWord.
            toSearch = searchWord;
        }

        if (filter == this.ORDER_BY_ASCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.birthDate ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.birthDate DESC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_ASCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.lastName ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.lastName DESC", Users.class)
                    .getResultList();
        }

        return result;
    }

    private List<Users> cleanFilter(String filter) {
        List<Users> result = new ArrayList<>();

        if (filter == this.ORDER_BY_ASCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u ORDER BY u.birthDate ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u ORDER BY u.birthDate DESC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_ASCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u ORDER BY u.lastName ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u ORDER BY u.lastName DESC", Users.class)
                    .getResultList();
        } else {
            result = this.entityManager
                    .createQuery("SELECT u FROM Users u ORDER BY u.id DESC", Users.class)
                    .getResultList();
        }

        return result;
    }
}
