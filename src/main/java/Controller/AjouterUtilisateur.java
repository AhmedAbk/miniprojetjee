package Controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.UserDao;
import beans.User;

@WebServlet("/AjouterUtilisateur")
public class AjouterUtilisateur extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDao dao = new UserDao();

    public AjouterUtilisateur() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/AjouterUser.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = "";
        String prenom = "";
        String email = "";
        String password = "";
        String imagePath = "";

        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {// File field
                        // Get only the file name without the path
                        String fileName = new File(item.getName()).getName();
                        String filePath = getServletContext().getRealPath("/image/");
                        File uploadedFile = new File(filePath + File.separator + fileName);
                        item.write(uploadedFile);
                        // Store the relative path
                        imagePath = "image/" + fileName;
                    } else {
                        // Text fields
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();

                        switch (fieldName) {
                            case "name":
                                name = fieldValue;
                                break;
                            case "prenom":
                                prenom = fieldValue;
                                break;
                            case "email":
                                email = fieldValue;
                                break;
                            case "password":
                                password = fieldValue;
                                break;
                        }
                    }
                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Hash the password
        String hashedPassword = hashPassword(password);

        User user = new User(name, prenom, email, hashedPassword, imagePath);
        dao.addUser(user);

        response.sendRedirect("AllUsers");
    }



    // Method to hash the password
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Handle hashing error appropriately
        }
    }
}