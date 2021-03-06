package APP.Service;


import APP.Model.UserViewModel;

import java.util.List;

public interface IUSerService {

    public Iterable<UserViewModel> getAll(String filter, String searchWord);

    public void create(UserViewModel user);

    public void update(UserViewModel entity);

    public void delete(int entity);

    public UserViewModel getById(int id);
}
