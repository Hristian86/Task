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
import java.util.Date;

@WebServlet("")
public class Home extends HttpServlet {
    /*
    private final UserService userService;

    @Inject
    public Home(UserService userService) {
        this.userService = userService;
    }
     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /*
        UserViewModel user = new UserViewModel();
        user.setId("asd");
        user.setFirstName("pencho");
        user.setLastName("penchev");
        user.setBirthDate(new Date());
        user.setPhoneNumber("123123123");
        user.setEmail("asd@asd.com");

        this.userService.create(user);

        req.setAttribute("users", this.userService.getAll(null,null));
        */
        req .getRequestDispatcher("home.jsp")
                .forward(req, resp);
    }
}
