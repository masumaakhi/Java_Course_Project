// Contributor.java
package model;
import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Contributor {
    private static final String DATA_FILE = "data/users.txt";
    private Map<String, User> contributorDatabase = new HashMap<>();


    public Contributor() {
        loadContributors();
    }


    // Load contributor data from file
    private void loadContributors() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        String username = parts[0];
                        String password = parts[1];
                        String fullName = parts[2];
                        String email = parts[3];
                        String phone = parts[4];
                        contributorDatabase.put(username, new User(username, password, fullName, email, phone));
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading data file: " + e.getMessage());
            }
        }
    }


    // Save contributor data to file
    public void saveContributors() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (User user : contributorDatabase.values()) {
                writer.write(user.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to data file: " + e.getMessage());
        }
    }


    public boolean authenticate(String username, String password) {
        return contributorDatabase.containsKey(username) && contributorDatabase.get(username).getPassword().equals(password);
    }


    public boolean register(String username, String password, String fullName, String email, String phone) {
        if (!contributorDatabase.containsKey(username)) {
            User user = new User(username, password, fullName, email, phone);
            contributorDatabase.put(username, user);
            saveContributors();
            return true;
        }
        return false;
    }
}


