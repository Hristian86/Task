package APP.Controllers;

import APP.Model.UserViewModel;
import APP.Repository.DataRepo;
import APP.Service.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/userss")
public class UserController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/users.jsp";
    /*
    private DataRepo data = new DataRepo();
    private UserService userService = new UserService(data);
     */

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String forward = "";
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            String userId = req.getParameter("userId");
            this.userService.delete(userId);
            forward = LIST_USER;

            //TODO connect filter and search word.
            req.setAttribute("users", this.userService.getAll(null,null));

        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            String userId = req.getParameter("userId");
            UserViewModel user = this.userService.getById(userId);
            req.setAttribute("user", user);
            req.setAttribute("title", "Edit");
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            req.setAttribute("users", this.userService.getAll(null, null));
        } else {
            forward = INSERT_OR_EDIT;
            req.setAttribute("title", "Add");
        }

        req.getRequestDispatcher(forward)
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserViewModel user = new UserViewModel();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));


        this.userService.create(user);
        try {

            Date birthday = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("birthday"));
            user.setBirthDate(birthday);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));

        String userid = request.getParameter("userid");

            if (userid == null || userid.isEmpty()) {
                this.userService.create(user);
            } else {
                user.setId(userid);
                this.userService.update(user);
            }

        request.setAttribute("users", this.userService.getAll(null,null));
        request.getRequestDispatcher(LIST_USER)
        .forward(request, response);
    }
}
