package APP.Service;

import APP.Domain.Users;
import APP.Model.UserViewModel;
import APP.Repository.DataRepo;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserService implements IUSerService {
    private final DataRepo userRepo;

    //TODO Auto mapper...
    @Inject
    public UserService(DataRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public Iterable<UserViewModel> getAll(String filter, String searchWord) {

        List<UserViewModel> viewModel = userRepo.findAll(filter, searchWord)
                .stream()
                .map(x -> new UserViewModel(x.getId(),x.getFirstName(),x.getLastName(),x.getBirthDate(), x.getPhoneNumber(), x.getEmail()))
                .collect(Collectors.toList());

        return viewModel;
    }

    @Override
    public void create(UserViewModel user) {
        Users userToBeCreated = new Users();

        userToBeCreated.setFirstName(user.getFirstName());
        userToBeCreated.setLastName(user.getLastName());
        userToBeCreated.setBirthDate(user.getBirthDate());
        userToBeCreated.setPhoneNumber(user.getPhoneNumber());
        userToBeCreated.setEmail(user.getEmail());

        this.userRepo.save(userToBeCreated);
    }

    @Override
    public void update(UserViewModel user) {
        Users userToBeCreated = new Users();

        userToBeCreated.setFirstName(user.getFirstName());
        userToBeCreated.setLastName(user.getLastName());
        userToBeCreated.setBirthDate(user.getBirthDate());
        userToBeCreated.setPhoneNumber(user.getPhoneNumber());
        userToBeCreated.setEmail(user.getEmail());

        this.userRepo.update(userToBeCreated);
    }

    @Override
    public void delete(String userId) {

        this.userRepo.delete(userId);
    }

    @Override
    public UserViewModel getById(String id) {
        Users user = userRepo.findById(id);
        UserViewModel model = new UserViewModel(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthDate(), user.getPhoneNumber(), user.getEmail());
        return model;
    }
}
