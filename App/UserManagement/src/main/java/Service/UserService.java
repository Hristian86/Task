package Service;

import Entity.User;
import Repository.DataRepo;

import java.util.List;

public class UserService implements IUSerService {
    private final DataRepo userRepo;

    public UserService(DataRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAll(String filter) {

        return null;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void Update(User entity) {

    }

    @Override
    public void Delete(User entity) {

    }
}
