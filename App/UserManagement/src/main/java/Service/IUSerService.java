package Service;

import Model.UserViewModel;

import java.util.List;

public interface IUSerService {

    public List<UserViewModel> getAll(String filter);

    public void create(UserViewModel user);

    public void update(UserViewModel entity);

    public void delete(UserViewModel entity);
}
