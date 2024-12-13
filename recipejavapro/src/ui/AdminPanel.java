// AdminPanel.java
package ui;
import model.Recipe;
import service.AuthService;
import service.RecipeService;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class AdminPanel {
    private static AuthService authService = new AuthService();
    private static List<Recipe> recipes = new ArrayList<>();


    public static void show(Scanner scanner) {
        System.out.println("\n===== Admin Panel =====");
        System.out.print("Do you want to go to the basic website (Y/N)? ");
        String choice = scanner.nextLine();


        if (choice.equalsIgnoreCase("Y")) {
            ViewerPanel.show(scanner); // Show basic website options
        } else {
            System.out.println("Going to your admin website...");
            System.out.print("Enter your admin username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your admin password: ");
            String password = scanner.nextLine();


            // Check if admin credentials are correct
            if (authService.login(username, password)) {
                System.out.println("Welcome, Admin!");
                showAdminOptions(scanner);
            } else {
                System.out.println("Invalid admin credentials. Access denied.");
            }
        }
    }


    // Admin options menu
    private static void showAdminOptions(Scanner scanner) {
        boolean stayInAdminPanel = true;


        while (stayInAdminPanel) {
            System.out.println("\n===== Admin Panel Options =====");
            System.out.println("1. View Recipes");
            System.out.println("2. Add Recipes");
            System.out.println("3. Delete Recipes");
            System.out.println("4. Update Recipes");
            System.out.println("5. Search Recipes");
            System.out.println("6. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    viewRecipes();
                    break;
                case 2:
                    addRecipe(scanner);
                    break;
                case 3:
                    deleteRecipe(scanner);
                    break;
                case 4:
                    updateRecipe(scanner);
                    break;
                case 5:
                    searchRecipes(scanner);
                    break;
                case 6:
                    System.out.println("Logging out from the Admin Panel...");
                    stayInAdminPanel = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


private static void viewRecipes() {
    List<Recipe> recipes = RecipeService.getAllRecipes();
    if (recipes.isEmpty()) {
        System.out.println("No recipes available.");
    } else {
        System.out.println("==========================================");
        System.out.println("                 Recipe Details           ");
        System.out.println("==========================================");
        System.out.printf("%-10s %-15s %-20s %-30s%n", "ID", "Name", "Ingredients", "Steps");
        System.out.println("------------------------------------------");


        int id = 1;
        for (Recipe recipe : recipes) {
            System.out.printf("%-10d %-15s %-20s %-30s%n", id++, recipe.getName(), recipe.getIngredients(), recipe.getSteps());
        }


        System.out.println("==========================================");
    }
}


private static void addRecipe(Scanner scanner) {
    System.out.print("Enter recipe name: ");
    String name = scanner.nextLine();
    System.out.print("Enter ingredients: ");
    String ingredients = scanner.nextLine();
    System.out.print("Enter steps: ");
    String steps = scanner.nextLine();


    RecipeService.addRecipe(new Recipe(name, ingredients, steps));
    System.out.println("Recipe added successfully!");
}


// Similarly, update the deleteRecipe and updateRecipe methods using RecipeService
private static void deleteRecipe(Scanner scanner) {
    viewRecipes();
    System.out.println("===== Delete Recipe =====");
    System.out.print("Enter the name of the recipe to delete: ");
    String name = scanner.nextLine();


    if (RecipeService.deleteRecipe(name)) {
        System.out.println("Recipe deleted successfully!");
    } else {
        System.out.println("Recipe not found.");
    }
}


private static void updateRecipe(Scanner scanner) {
    viewRecipes();
    System.out.println("===== Update Recipe =====");
    System.out.print("Enter the name of the recipe to update: ");
    String name = scanner.nextLine();


    System.out.println("What would you like to update?");
    System.out.println("1. Update Ingredients");
    System.out.println("2. Update Steps");
    System.out.println("3. Update Both");
    System.out.println("4. Exit");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character


    boolean isUpdated = false;


    switch (choice) {
        case 1:
            System.out.print("Enter new ingredients: ");
            String newIngredients = scanner.nextLine();
            isUpdated = RecipeService.updateRecipe(name, newIngredients, null);
            break;
        case 2:
            System.out.print("Enter new steps: ");
            String newSteps = scanner.nextLine();
            isUpdated = RecipeService.updateRecipe(name, null, newSteps);
            break;
        case 3:
            System.out.print("Enter new ingredients: ");
            newIngredients = scanner.nextLine();
            System.out.print("Enter new steps: ");
            newSteps = scanner.nextLine();
            isUpdated = RecipeService.updateRecipe(name, newIngredients, newSteps);
            break;
        case 4:
            System.out.println("Exiting update menu.");
            return;
        default:
            System.out.println("Invalid choice. Please try again.");
            return;
    }


    if (isUpdated) {
        System.out.println("Recipe updated successfully!");
    } else {
        System.out.println("Recipe not found.");
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

}


