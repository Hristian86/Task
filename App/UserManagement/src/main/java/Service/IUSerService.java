package Service;

import Entity.User;

import java.util.List;

public interface IUSerService {

    public List<User> getAll(String filter);

    public void add(User user);

    public void Update(User entity);

    public void Delete(User entity);
}
