package Service;

import Model.UserViewModel;
import Repository.DataRepo;

import java.util.List;

public class UserService implements IUSerService {
    private final DataRepo userRepo;

    public UserService(DataRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserViewModel> getAll(String filter) {

        return null;
    }

    @Override
    public void create(UserViewModel user) {

    }


    @Override
    public void update(UserViewModel entity) {

    }

    @Override
    public void delete(UserViewModel entity) {

    }
}
