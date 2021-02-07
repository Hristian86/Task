package APP.Controllers;

import APP.Model.UserViewModel;
import APP.Service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/test")
public class test extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserViewModel user = new UserViewModel();
        user.setFirstName("pencho");
        user.setLastName("penchev");
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01,01,2020");

        user.setBirthDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setPhoneNumber("123123123");
        user.setEmail("asdasdcom");

        this.userService.create(user);

        req.setAttribute("users", userService.getAll(null,null));
        req.getRequestDispatcher("index.jsp")
                .forward(req, resp);
    }
}
