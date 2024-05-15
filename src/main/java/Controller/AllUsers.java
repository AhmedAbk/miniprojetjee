package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.UserDao;
import java.util.List;
import beans.User;

@WebServlet("/AllUsers")
public class AllUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDao userDao = new UserDao();

    public AllUsers() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user list
        List<User> userList = userDao.getAll();
        request.setAttribute("userList", userList);

        // Retrieve counts
        int totalUsers = userDao.getTotalUserCount();
        int usersWithNameN = userDao.getCountOfUsersWithNameStartingWithN();
        int usersWithPrenomChouchen = userDao.getCountOfUsersWithPrenomChouchen();

        // Set counts as attributes
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("usersWithNameN", usersWithNameN);
        request.setAttribute("usersWithPrenomChouchen", usersWithPrenomChouchen);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/ListUsers.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty()) {
            if (action.equals("delete")) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                boolean deletionResult = userDao.deleteUser(userId);
                if (deletionResult) {
                    System.out.println("User with ID " + userId + " successfully deleted.");
                } else {
                    System.out.println("Failed to delete user with ID: " + userId);
                }
            } else if (action.equals("update")) {
                int userId = Integer.parseInt(request.getParameter("userId"));
                
                response.sendRedirect(request.getContextPath() + "/UpdateUser?id=" + userId);
                return;
            }
        } else {
            // Handle the case where action parameter is not present or not recognized
            // For example, you could log an error or display a message
            System.out.println("Action parameter not provided or not recognized");
        }

        // Redirect back to the user list page
        response.sendRedirect(request.getContextPath() + "/AllUsers");
    }

}
