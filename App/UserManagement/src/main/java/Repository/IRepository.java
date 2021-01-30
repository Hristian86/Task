package Repository;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.List;

public interface IRepository<Entity, Key> {
    Entity save(Entity entity);

    void update(Entity entity);

    List<Entity> findAll();

    Entity findById(Key id);

    void delete(Key id);
}
