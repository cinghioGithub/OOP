package diet;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	
	private String Name;
	private double Calories;
	private double Proteins;
	private double Carbs;
	private double Fat;
	private Food F;
	
	public Menu(String name, Food f) {
		this.Name=name;
		this.F=f;
	}
	
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		NutritionalElement r = this.F.getRecipe(recipe);
		this.Calories += (r.getCalories()*quantity)/100;
		this.Proteins += (r.getProteins()*quantity)/100;
		this.Carbs += (r.getCarbs()*quantity)/100;
		this.Fat += (r.getFat()*quantity)/100;
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		NutritionalElement p = this.F.getProduct(product);
		this.Calories += p.getCalories();
		this.Proteins += p.getProteins();
		this.Carbs += p.getCarbs();
		this.Fat += p.getFat();
		return this;
	}

	@Override
	public String getName() {
		return this.Name;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return this.Calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return this.Proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return this.Carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return this.Fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
	
	@Override
	public char getTypeTag() {
		//return '?';
		throw new IllegalArgumentException("No tab defined");
	}
}
