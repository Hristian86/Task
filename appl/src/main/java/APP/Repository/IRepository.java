package APP.Repository;

import java.util.List;

public interface IRepository<Entity, Key> {
    Entity save(Entity entity);

    void update(Entity entity);

    List<Entity> findAll(String filter, String searchWord);

    Entity findById(Key id);

    void delete(Key id);
}
