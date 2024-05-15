package beans;

public class User {
    private int idUser;
    private String name;
    private String prenom;
    private String email;
    private String password;
    private String image;

    public User(int idUser, String name, String prenom, String email, String password) {
        this.idUser = idUser;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

    public User(String name, String prenom, String email, String password) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }

  
    public User(int idUser,String name, String prenom, String email,String image, String password ) {
        this.idUser = idUser;
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.image = image;
        this.password = password;
    }
    public User(String name, String prenom, String email, String password, String image) {
        this.name = name;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.image = image;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}