package APP.Controllers;

import APP.Model.UserViewModel;
import APP.Service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserViewModel user = new UserViewModel();
        user.setId("1asd");
        user.setFirstName("asd");
        user.setLastName("aa");
        long date = 1212121212;
        user.setBirthDate(new Date(date));
        user.setPhoneNumber("+123123");
        user.setEmail("asd@asd.com");
        List<UserViewModel> listOfUsers = new ArrayList<UserViewModel>();
        listOfUsers.add(user);
        listOfUsers.add(user);
        listOfUsers.add(user);

        req.setAttribute("users", listOfUsers);

        req.getRequestDispatcher("index.jsp")
                    .forward(req, resp);
    }
}
