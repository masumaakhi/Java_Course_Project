// // ViewerPanel.java
package ui;


import service.AuthService;
import service.RecipeService;


import java.util.List;
import java.util.Scanner;


import model.Recipe;
import ui.AdminPanel;
public class ViewerPanel {
    private static AuthService authService = new AuthService(); // Reference to AuthService


    public static void show(Scanner scanner) {
        while (true) {
            System.out.println("\n===== Viewer Basic Interface =====");
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Browse Recipes");
            System.out.println("4. Search Recipes");
            System.out.println("5. View Recipe Details");
            System.out.println("6. Sign Out");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    signUp(scanner);
                    break;
                case 2:
                    signIn(scanner);
                    break;
                case 3:
                    viewRecipes();
                    break;
                case 4:
                    searchRecipes(scanner);
                    break;
                case 5:
                    System.out.println("Viewing recipe details...");
                    break;
                case 6:
                    System.out.println("Signing out...");
                    return;
                case 7:
                    System.out.println("Exiting the application...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Sign Up functionality
    private static void signUp(Scanner scanner) {
        System.out.println("\n===== Sign Up =====");
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();


        boolean success = authService.registerContributor(username, password);
        if (success) {
            System.out.println("Registration successful! You are now a contributor. Please sign in to continue.");
        } else {
            System.out.println("Registration failed. Username already exists. Please try again.");
        }
    }


    // Sign In functionality
    private static void signIn(Scanner scanner) {
        System.out.println("\n===== Sign In =====");
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();


        boolean isAdmin = username.equals("admin") && password.equals("admin123");
        boolean isContributor = authService.login(username, password);


        if (isAdmin) {
            System.out.println("Login successful! Welcome to your Admin Panel.");
            showAdminOptions(scanner);
        } else if (isContributor) {
            System.out.println("Login successful! Welcome to your Contributor Panel.");
            showContributorOptions(scanner);
        } else {
            System.out.println("Login failed. Invalid username or password. Please try again.");
        }
    }


    private static void viewRecipes() {
    List<Recipe> recipes = RecipeService.getAllRecipes();
    if (recipes.isEmpty()) {
        System.out.println("No recipes available.");
    } else {
        System.out.println("======================================================================");
        System.out.println("                 Recipe Details           ");
        System.out.println("======================================================================");
        System.out.printf("%-10s %-15s %-20s %-30s%n", "ID", "Name", "Ingredients", "Steps");
        System.out.println("----------------------------------------------------------------------");


        int id = 1;
        for (Recipe recipe : recipes) {
            System.out.printf("%-10d %-15s %-20s %-30s%n", id++, recipe.getName(), recipe.getIngredients(), recipe.getSteps());
        }


        System.out.println("======================================================================");
    }
}


private static void searchRecipes(Scanner scanner) {
    System.out.print("Enter a keyword to search in recipe names: ");
    String keyword = scanner.nextLine();

    boolean found = false;
    System.out.println("==========================================");
    System.out.println("              Search Results              ");
    System.out.println("==========================================");

    List<Recipe> matchingRecipes = RecipeService.searchRecipes(keyword); // Use RecipeService
    for (Recipe recipe : matchingRecipes) {
        System.out.println(recipe);
        found = true;
    }

    if (!found) {
        System.out.println("No recipes found with the given keyword.");
    }
    System.out.println("==========================================");
}

    // Show Admin-specific options
    private static void showAdminOptions(Scanner scanner) {
        while (true) {
            System.out.println("\n===== Admin Options =====");
            System.out.println("1. View Contributor Profiles");
            System.out.println("2. Add Recipes");
            System.out.println("3. View All Recipes");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    System.out.println("Displaying contributor profiles...");
                    // Logic to view contributor profiles
                    break;
                case 2:
                    System.out.println("Adding recipes...");
                    // Logic to add recipes
                    break;
                case 3:
                    System.out.println("Viewing all recipes...");
                    // Logic to view all recipes
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // Show Contributor-specific options
    private static void showContributorOptions(Scanner scanner) {
        while (true) {
            System.out.println("\n===== Contributor Options =====");
            System.out.println("1. Add Your Recipes");
            System.out.println("2. View Your Recipes");
            System.out.println("3. Edit Your Recipes");
            System.out.println("4. Delete Your Recipes");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    System.out.println("Adding your recipes...");
                    // Logic to add contributor's recipes
                    break;
                case 2:
                    System.out.println("Viewing your recipes...");
                    // Logic to view contributor's recipes
                    break;
                case 3:
                    System.out.println("Editing your recipes...");
                    // Logic to edit contributor's recipes
                    break;
                case 4:
                    System.out.println("Deleting your recipes...");
                    // Logic to delete contributor's recipes
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


