package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDao;
import beans.User;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDao userDao = new UserDao();

    public UpdateUser() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user ID from the request parameter
        int userId = Integer.parseInt(request.getParameter("id"));
        
        // Fetch user details by ID
        User user = userDao.findUser(userId);

        // Set user details as request attribute
        request.setAttribute("user", user);

        // Forward the request to the JSP page for updating the user
        request.getRequestDispatcher("/WEB-INF/UpdateUser.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get updated user information from the request
        String userId = request.getParameter("userId");
        String name = request.getParameter("name");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Update user information in the database
        userDao.updateUser(Integer.parseInt(userId), name, prenom, email, password);

        // Redirect the request to ListUsers servlet to display updated user list
        response.sendRedirect(request.getContextPath() + "/AllUsers");
    }
}
