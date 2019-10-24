package diet;

import java.util.ArrayList;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
    

	private String Name;
	private double Calories;
	private double Proteins;
	private double Carbs;
	private double Fat;
	private double Quantity;
	private Food F;
	
	private ArrayList<NutritionalElement> Ingredients = new ArrayList<>();
	
	public Recipe(String name, Food f) {
		this.Name = name;
		this.F = f;
	}
	
	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		NutritionalElement el = this.F.getRawMaterial(material);
		this.Calories += (el.getCalories()/100)*quantity;
		this.Proteins += (el.getProteins()/100)*quantity;
		this.Carbs += (el.getCarbs()/100)*quantity;
		this.Fat += (el.getFat()/100)*quantity;
		this.Quantity += quantity;
		this.Ingredients.add(el);
		return this;
	}

	@Override
	public String getName() {
		return this.Name;
	}

	@Override
	public double getCalories() {
		return (this.Calories*100)/this.Quantity;
	}

	@Override
	public double getProteins() {
		return (this.Proteins*100)/this.Quantity;
	}

	@Override
	public double getCarbs() {
		return (this.Carbs*100)/this.Quantity;
	}

	@Override
	public double getFat() {
		return (this.Fat*100)/this.Quantity;
	}
	
	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expressed nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
	    // a recipe expressed nutritional values per 100g
		return true;
	}
	
	@Override
	public char getTypeTag() {
		//return '?';
		throw new IllegalArgumentException("No tab defined");
	}

}
