// User.java
package model;
public class User {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phone;


    public User(String username, String password, String fullName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }


    // Convert user details to CSV format for file storage
    public String toCSV() {
        return username + "," + password + "," + fullName + "," + email + "," + phone;
    }
}
