package DAO;

import java.io.File;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import beans.User;

public class UserDao implements userInterface {

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user(name, prenom, email, password, image) VALUES (?, ?, ?, ?, ?)";
        try (
            Connection maconnexion = dbc.createConnection();
            PreparedStatement stmt = maconnexion.prepareStatement(sql)
        ){
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPassword());
            stmt.setString(5, user.getImage());
            
            // Debugging output
            System.out.println("Adding user with details: " + user.getName() + ", " + user.getPrenom() + ", " + user.getEmail() + ", " + user.getPassword() + ", " + user.getImage());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<User> getAll() {
        try {
            Connection maconnexion = dbc.createConnection();
            java.sql.PreparedStatement ps = maconnexion.prepareStatement("SELECT idUser,name,prenom,email,image,password FROM user");
            ResultSet rs = ps.executeQuery();

            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                    rs.getInt("idUser"),
                    rs.getString("name"),
                    rs.getString("prenom"),
                    rs.getString("email"),

                    rs.getString("image"),
                    rs.getString("password")
                );
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUser(int id) {
        try {
            Connection maconnexion = dbc.createConnection();
            java.sql.PreparedStatement ps = maconnexion.prepareStatement("SELECT * FROM user WHERE idUser = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String prenom = rs.getString("prenom");
                String email = rs.getString("email");
                String password = rs.getString("password");
                return new User(id, name, prenom, email, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            Connection maconnexion = dbc.createConnection();
            String sql = "DELETE FROM user WHERE idUser = ?";
            PreparedStatement ps = maconnexion.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
   
    public boolean updateUser(int userId, String name, String prenom, String email, String password) {
        try (Connection connection = dbc.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET name = ?, prenom = ?, email = ?, password = ?  WHERE idUser = ?")) {

            int parameterIndex = 1;
            preparedStatement.setString(parameterIndex++, name);
            preparedStatement.setString(parameterIndex++, prenom);
            preparedStatement.setString(parameterIndex++, email);
            preparedStatement.setString(parameterIndex++, password);

            preparedStatement.setInt(parameterIndex, userId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

 
        public int getCountOfUsersWithNameStartingWithN() {
        int count = 0;
        try (Connection connection = dbc.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE name LIKE 'A%'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int getCountOfUsersWithPrenomChouchen() {
        int count = 0;
        try (Connection connection = dbc.createConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM user WHERE prenom = 'Abdelkader'")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int getTotalUserCount() {
        try {
            Connection maconnexion = dbc.createConnection();
            java.sql.PreparedStatement ps = maconnexion.prepareStatement("SELECT COUNT(*) FROM user");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(password.getBytes());

            byte[] bytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; 
        }
    }

	
	

}
   
   





