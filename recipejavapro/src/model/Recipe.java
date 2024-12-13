// Recipe.java
package model;
import java.io.Serializable;


public class Recipe implements Serializable {
    private String name;
    private String ingredients;
    private String steps;


    public Recipe(String name, String ingredients, String steps) {
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }


    public String getName() {
        return name;
    }


    public String getIngredients() {
        return ingredients;
    }


    public String getSteps() {
        return steps;
    }


    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }


    public void setSteps(String steps) {
        this.steps = steps;
    }


    @Override
    public String toString() {
        return "Recipe{name='" + name + "', ingredients='" + ingredients + "', steps='" + steps + "'}";
    }
}
