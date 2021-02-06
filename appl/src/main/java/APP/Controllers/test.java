package APP.Controllers;

import APP.Model.UserViewModel;
import APP.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/test")
public class test extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserViewModel user = new UserViewModel();
        user.setFirstName("pencho");
        user.setLastName("penchev");
        user.setBirthDate(new Date());
        user.setPhoneNumber("123123123");
        user.setEmail("asdasdcom");

        this.userService.create(user);

        req.setAttribute("users", userService.getAll(null,null));
        req.getRequestDispatcher("index.jsp")
                .forward(req, resp);
    }
}
