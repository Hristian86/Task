package APP.Repository;

import APP.Domain.Users;
import APP.Utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepositoryImpl implements IRepository<Users>{

    private static final String ORDER_BY_ASCENDING_BIRTH_DATE = "birth_date_asc";
    private static final String ORDER_BY_DESCENDING_BIRTH_DATE = "birth_date_desc";
    private static final String ORDER_BY_ASCENDING_LAST_NAME = "last_name_asc";
    private static final String ORDER_BY_DESCENDING_LAST_NAME = "last_name_desc";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (firstName, lastName, birthDate, phoneNumber, email) VALUES "
            + " (?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select id, firstName, lastName, birthDate, phoneNumber, email from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set firstName = ?, lastName = ?, birthDate = ?, phoneNumber = ?, email = ? where id = ?;";


    private Connection con;

    public UserRepositoryImpl() {
        try {
            this.con = DatabaseConnection.initializeDatabase();
        } catch (Exception exception) {

        }
    }

    @Override
    public Users save(Users users) {
        // try-with-resource statement will auto close the connection.
        try (
            Connection connection = DatabaseConnection.initializeDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, users.getFirstName());
            preparedStatement.setString(2, users.getLastName());
            preparedStatement.setDate(3, users.getBirthDate());
            preparedStatement.setString(4, users.getPhoneNumber());
            preparedStatement.setString(5, users.getEmail());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void update(Users users) {

        // try-with-resource statement will auto close the connection.
        try (
                Connection connection = DatabaseConnection.initializeDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, users.getFirstName());
            preparedStatement.setString(2, users.getLastName());
            preparedStatement.setDate(3, users.getBirthDate());
            preparedStatement.setString(4, users.getPhoneNumber());
            preparedStatement.setString(5, users.getEmail());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Users> findAll(String filter, String searchWord) {

        List<Users> userList = new ArrayList<>();
        // try-with-resource statement will auto close the connection.
        try (
                Connection connection = DatabaseConnection.initializeDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date birthdate = rs.getDate("birthdate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                Users user = new Users();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBirthDate(new java.sql.Date(birthdate.getTime()));
                user.setPhoneNumber(phoneNumber);
                user.setEmail(email);
                userList.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public Users findById(int id) {
        Users user = null;
        // try-with-resource statement will auto close the connection.
        try (
                Connection connection = DatabaseConnection.initializeDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {

            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Date birthdate = rs.getDate("birthdate");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                user = new Users();
                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBirthDate(new java.sql.Date(birthdate.getTime()));
                user.setPhoneNumber(phoneNumber);
                user.setEmail(email);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void delete(int id) {
        // try-with-resource statement will auto close the connection.
        try (
                Connection connection = DatabaseConnection.initializeDatabase(); PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)) {
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
