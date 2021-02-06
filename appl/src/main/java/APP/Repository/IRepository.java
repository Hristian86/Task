package APP.Repository;

import java.util.List;

public interface IRepository<Entity> {
    Entity save(Entity entity);

    void update(Entity entity);

    List<Entity> findAll(String filter, String searchWord);

    Entity findById(int id);

    void delete(int id);
}
