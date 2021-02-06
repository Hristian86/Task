package APP.Repository;

import APP.Domain.Users;
import APP.Utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class DataRepo implements IRepository<Users> {
    private static final String ORDER_BY_ASCENDING_BIRTH_DATE = "birth_date_asc";
    private static final String ORDER_BY_DESCENDING_BIRTH_DATE = "birth_date_desc";
    private static final String ORDER_BY_ASCENDING_LAST_NAME = "last_name_asc";
    private static final String ORDER_BY_DESCENDING_LAST_NAME = "last_name_desc";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (firstName, lastName, birthDate, phoneNumber, email) VALUES "
            + " (?, ?, ?, ?, ?);";

    private EntityManager entityManager;
    //private Session session;

    public DataRepo() {

        //session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    private void init() {
        this.entityManager = Persistence.createEntityManagerFactory("user_management")
                .createEntityManager();
    }

    @Transactional
    @Override
    public Users save(Users user) {
        this.init();

        this.entityManager.getTransaction().begin();
        /*
        this.entityManager
                .createNamedQuery("INSERT INTO users" + "  (firstName, lastName, birthDate, phoneNumber, email) VALUES "
                        + " ("+ user.getFirstName() +", "+ user.getLastName() +", "+ user.getBirthDate() +", " + user.getPhoneNumber() + ", " + user.getEmail() + ");")
                .executeUpdate();

         */

        /*
        this.entityManager
                .createQuery("INSERT INTO users" + "  (firstName, lastName, birthDate, phoneNumber, email) VALUES "
                        + " ("+ user.getFirstName().toString() +","+ user.getLastName().toString() +","+ user.getBirthDate() +"," + user.getPhoneNumber().toString() + "," + user.getEmail().toString() + ")");


         */

        this.entityManager
                .createQuery("INSERT INTO users" + "  (firstName, lastName, birthDate, phoneNumber, email) VALUES ('pesho','peshev',"+ user.getBirthDate() +",'123123','asdasd')");



        //this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();

        /*
        Transaction t = this.session.beginTransaction();
        this.session.save(users);
        t.commit();
         */

        return user;
    }

    @Override
    public void update(Users user) {

        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();

        /*
        Transaction t = this.session.beginTransaction();
        this.session.update(users);
        t.commit();
         */

    }

    @Override
    public List<Users> findAll(String filter, String searchWord) {
        this.init();

        this.entityManager.getTransaction().begin();
        List<Users> listOfUsers = this.filterResult(filter, searchWord);
        this.entityManager.getTransaction().commit();

        /*
        Transaction t = this.session.beginTransaction();
        List<Users> listOfUsers = this.filterResult(filter, searchWord);
        t.commit();
         */

        return listOfUsers;
    }

    @Override
    public Users findById(int id) {

        //Transaction t = this.session.beginTransaction();
        Users user = this.entityManager
                .createQuery("SELECT u FROM users u WHERE u.id = :id", Users.class)
                .setParameter("id", id)
                .getSingleResult();
        //t.commit();

        return user;
    }

    @Override
    public void delete(int id) {

        Users user = this.entityManager
                .createQuery("SELECT u FROM users u WHERE u.id = :id", Users.class)
                .setParameter("id", id)
                .getSingleResult();

        /*
        Transaction t = this.session.beginTransaction();
        Users user = this.findById(id);
        this.session.delete(user);
        t.commit();
         */

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
                    .createQuery("SELECT u FROM users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.birthDate ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.birthDate DESC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_ASCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.lastName ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u WHERE u.firstName LIKE '%"+ toSearch +"%' OR u.lastName LIKE '%"+ toSearch + "%' OR u.email LIKE '%"+ toSearch +"%' ORDER BY u.lastName DESC", Users.class)
                    .getResultList();
        }

        return result;
    }

    private List<Users> cleanFilter(String filter) {
        List<Users> result = new ArrayList<>();

        if (filter == this.ORDER_BY_ASCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u ORDER BY u.birthDate ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_BIRTH_DATE) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u ORDER BY u.birthDate DESC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_ASCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u ORDER BY u.lastName ASC", Users.class)
                    .getResultList();
        } else if (filter == this.ORDER_BY_DESCENDING_LAST_NAME) {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u ORDER BY u.lastName DESC", Users.class)
                    .getResultList();
        } else {
            result = this.entityManager
                    .createQuery("SELECT u FROM users u ORDER BY u.id DESC", Users.class)
                    .getResultList();
        }

        return result;
    }
}
