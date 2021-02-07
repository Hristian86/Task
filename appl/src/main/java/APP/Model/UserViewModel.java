package APP.Model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.sql.Date;

public class UserViewModel {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String phoneNumber;
    private String email;
    public UserViewModel() {

    }

    public UserViewModel(int id, String firstName, String lastName, Date birthDate, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    @Min(value = 1)
    @Max(value = 100)
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Min(value = 1)
    @Max(value = 50)
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(java.util.Date birthDate) {
        this.birthDate = new java.sql.Date(birthDate.getTime());
    }

    @Min(value = 1)
    @Max(value = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }

    @Min(value = 6)
    @Max(value = 50)
    @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid email address!")
    public void setEmail(String email) {
        this.email = email;
    }
}
