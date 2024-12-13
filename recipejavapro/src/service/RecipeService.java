// RecipeService.java
package service;
import model.Recipe;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class RecipeService {
   
    private static final String FILE_NAME = "data/recipes.txt"; // File to persist recipes
    private static List<Recipe> recipes = new ArrayList<>();


    // Load recipes from file during initialization
    static {
        loadRecipesFromFile();
    }


    // Add a new recipe
    public static void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipesToFile(); // Save changes to file
    }


    // Get all recipes
    public static List<Recipe> getAllRecipes() {
        return recipes;
    }


    // Delete a recipe by name
    public static boolean deleteRecipe(String name) {
        boolean removed = recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(name));
        if (removed) {
            saveRecipesToFile(); // Save changes to file
        }
        return removed;
    }


    // Update a recipe by name
    public static boolean updateRecipe(String name, String newIngredients, String newSteps) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipe.setIngredients(newIngredients);
                recipe.setSteps(newSteps);
                saveRecipesToFile(); // Save changes to file
                return true;
            }
        }
        return false;
    }


    // Search for recipes by keyword
    public static List<Recipe> searchRecipes(String keyword) {
        List<Recipe> result = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(recipe);
            }
        }
        return result;
    }


    // Save recipes to file
    private static void saveRecipesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   


    // Load recipes from file
    private static void loadRecipesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            recipes = (List<Recipe>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            recipes = new ArrayList<>(); // Initialize with an empty list if file not found
        }
    }
}
