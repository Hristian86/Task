package APP.Domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseModel {
    private String id;

    public BaseModel() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false,unique = true, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
