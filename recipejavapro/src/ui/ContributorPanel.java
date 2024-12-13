//ContributorPanel.java
package ui;
import model.Contributor;
import model.Recipe;
import service.RecipeService;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ContributorPanel {
    private static Contributor contributor = new Contributor();
    private static List<Recipe> recipes = new ArrayList<>();


    public static void show(Scanner scanner) {
        System.out.println("\n===== Contributor Panel =====");
        System.out.print("Do you want to go to the basic website (Y/N)? ");
        String choice = scanner.nextLine();


        if (choice.equalsIgnoreCase("Y")) {
            ViewerPanel.show(scanner);
        } else {
            managePersonalWebsite(scanner);
        }
    }


    private static void managePersonalWebsite(Scanner scanner) {
        boolean loggedIn = false;


        while (!loggedIn) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();


            if (contributor.authenticate(username, password)) {
                System.out.println("Welcome back, " + username + "!");
                showContributorOptions(scanner);
                loggedIn = true;
            } else {
                System.out.println("Account not found. Do you want to register? (Y/N)");
                String registerChoice = scanner.nextLine();
                if (registerChoice.equalsIgnoreCase("Y")) {
                    if (registerUser(scanner)) {
                        System.out.println("Registration successful. Please log in.");
                    } else {
                        System.out.println("Registration failed. Username may already exist.");
                    }
                }
            }
        }
    }


    private static boolean registerUser(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        System.out.print("Enter your full name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter your email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();


        return contributor.register(username, password, fullName, email, phone);
    }


    private static void showContributorOptions(Scanner scanner) {
        boolean active = true;


        while (active) {
            System.out.println("\n===== Your Personal Website =====");
            System.out.println("1. Add Recipes");
            System.out.println("2. View Recipes");
            System.out.println("3. Update Recipes");
            System.out.println("4. Delete Recipes");
            System.out.println("5. Search Recipes");
            System.out.println("6. Sign Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    addRecipe(scanner);
                    break;
                case 2:
                    viewRecipes();
                    break;
                case 3:
                    updateRecipe(scanner);
                    break;
                case 4:
                    deleteRecipe(scanner);
                    break;
                case 5:
                    searchRecipes(scanner);
                    break;
                case 6:
                    active = false;
                    System.out.println("Signed out successfully.");
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


