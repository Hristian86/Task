package APP.Controllers;

import APP.Model.UserViewModel;
import APP.Repository.DataRepo;
import APP.Service.UserService;
import org.hibernate.loader.plan.build.internal.returns.ScalarReturnImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/userss")
public class UserController extends HttpServlet {
    private static String INSERT_OR_EDIT = "/user.jsp";
    private static String LIST_USER = "/index.jsp";
    /*
    private DataRepo data = new DataRepo();
    private UserService userService = new UserService(data);
     */

    /*
    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }
     */

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> listEr = new ArrayList<>();
        req.setAttribute("errors", listEr);
        req.setAttribute("success", "");
        String forward = "";
        String action = req.getParameter("action");

        String sort = req.getParameter("sort");

        String search = req.getParameter("search");

        if (action == null) {
            if (search != null) {
                forward = LIST_USER;
                req.setAttribute("users", this.userService.getAll(req.getParameter("sort"),req.getParameter("search")));
            } else {
                forward = INSERT_OR_EDIT;
                req.setAttribute("title", "Add");
            }
        } else if (action.equalsIgnoreCase("delete")) {
            int userId = Integer.parseInt(req.getParameter("userId"));
            this.userService.delete(userId);
            forward = LIST_USER;
            req.setAttribute("users", this.userService.getAll(req.getParameter("sort"),req.getParameter("search")));

        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(req.getParameter("userId"));
            UserViewModel user = this.userService.getById(userId);
            req.setAttribute("user", user);
            req.setAttribute("title", "Edit");
        } else if (action.equalsIgnoreCase("listUser")) {
            forward = LIST_USER;
            req.setAttribute("users", this.userService.getAll(req.getParameter("sort"),req.getParameter("search")));
        } else if (action == "insert") {
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

        try {

            Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"));
            user.setBirthDate(birthday);
        } catch (ParseException e) {

            e.printStackTrace();
        }
        user.setEmail(request.getParameter("email"));
            String userId = request.getParameter("userid");
            if (userId == null || userId.isEmpty()) {

                List<String> validate = this.validateUser(user);

                if (validate.isEmpty()) {
                    request.setAttribute("success", "Success");
                    this.userService.create(user);
                } else {
                    request.setAttribute("errors", validate);
                }

            } else {
                List<String> validate = this.validateUser(user);

                if (validate.isEmpty()) {
                    request.setAttribute("success", "Success");
                    int userid = Integer.parseInt(request.getParameter("userid"));
                    user.setId(userid);
                    this.userService.update(user);
                } else {
                    request.setAttribute("errors", validate);
                }

            }

        request.setAttribute("users", this.userService.getAll(request.getParameter("sort"),request.getParameter("search")));
        request.getRequestDispatcher(LIST_USER)
        .forward(request, response);
    }

    private List<String> validateUser(UserViewModel user) {
        List<String> errors = new ArrayList<>();
        if (user.getFirstName() == null) {
            errors.add("First name must be at least 1 symbol long.");
        } else if(user.getFirstName().length() < 2 || user.getFirstName().length() > 50) {
            errors.add("First name must be at least 1 symbol long and max 50 symbols.");
        }

        if (user.getLastName() == null) {
            errors.add("Last name must be at least 1 symbol long.");
        } else if(user.getLastName().length() < 2 || user.getLastName().length() > 50) {
            errors.add("Last name must be at least 1 symbol long and max 50 symbols.");
        }

        if (user.getPhoneNumber() == null) {
            errors.add("Phone number must be provided.");
        } else if(user.getPhoneNumber().length() < 10 || user.getPhoneNumber().length() > 50) {
            errors.add("Incorect phone number .");
        }

        // TODO regex.
        if (user.getEmail() == null) {
            errors.add("Email must be provided.");
        } else if(user.getEmail().length() < 6 || user.getEmail().length() > 50) {
            errors.add("Incorect email address.");
        }

        return  errors;
    }
}
