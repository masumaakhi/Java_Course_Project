// AuthService.java
package service;
import java.util.HashMap;
import java.util.Map;


public class AuthService {
    private final Map<String, String> contributors = new HashMap<>();


    // Admin credentials (hardcoded for simplicity)
    private final String adminUsername = "admin";
    private final String adminPassword = "admin123";


    public boolean registerContributor(String username, String password) {
        if (contributors.containsKey(username)) {
            return false;
        }
        contributors.put(username, password);
        return true;
    }


    public boolean login(String username, String password) {
        // Check if the user is admin
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return true;
        }


        // Check if the user is a registered contributor
        return contributors.containsKey(username) && contributors.get(username).equals(password);
    }


    // Display all contributors (for admin)
    public void viewContributors() {
        if (contributors.isEmpty()) {
            System.out.println("No contributors registered.");
        } else {
            System.out.println("===== Contributor Profiles =====");
            for (Map.Entry<String, String> entry : contributors.entrySet()) {
                System.out.println("Username: " + entry.getKey() + ", Password: " + entry.getValue());
            }
        }
    }
}


