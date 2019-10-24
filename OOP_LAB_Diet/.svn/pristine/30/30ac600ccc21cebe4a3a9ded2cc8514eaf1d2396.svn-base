package diet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;


/**
 * Facade class for the diet management.
 * It allows defining and retrieving raw materials and products.
 *
 */
public class Food {

//	private Collection<NutritionalElement> RawMat = new TreeSet<NutritionalElement>(new Comparator<NutritionalElement>(){
//		public int compare(NutritionalElement o1, NutritionalElement o2) {
//			return	o1.getName().compareTo(o2.getName());
//		}
//	});
	
	private Map<String, NutritionalElement> RawMat = new TreeMap<>();
	private Map<String, NutritionalElement> ProdMap = new TreeMap<>();
	private Map<String, NutritionalElement> RecipeMap = new TreeMap<>();
	private LinkedList<NutritionalElement> MenuList = new LinkedList<>();
	
	/**
	 * Define a new raw material.
	 * The nutritional values are specified for a conventional 100g quantity
	 * @param name unique name of the raw material
	 * @param calories calories per 100g
	 * @param proteins proteins per 100g
	 * @param carbs carbs per 100g
	 * @param fat fats per 100g
	 */
	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat){
		NutritionalElement mat = new RawMaterial(name, calories, proteins, carbs, fat);
		//RawMat.add(mat);
		this.RawMat.put(mat.getName(), mat);
	}
	
	/**
	 * Retrieves the collection of all defined raw materials
	 * @return collection of raw materials though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> rawMaterials(){
		//return RawMat;
		return this.RawMat.values();
	}
	
	/**
	 * Retrieves a specific raw material, given its name
	 * @param name  name of the raw material
	 * @return  a raw material though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRawMaterial(String name){
		return this.RawMat.get(name);
	}

	/**
	 * Define a new packaged product.
	 * The nutritional values are specified for a unit of the product
	 * @param name unique name of the product
	 * @param calories calories for a product unit
	 * @param proteins proteins for a product unit
	 * @param carbs carbs for a product unit
	 * @param fat fats for a product unit
	 */
	public void defineProduct(String name, double calories, double proteins, double carbs, double fat){
		NutritionalElement prod = new Product(name, calories, proteins, carbs, fat);
		this.ProdMap.put(prod.getName(), prod);
	}
	
	/**
	 * Retrieves the collection of all defined products
	 * @return collection of products though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> products(){
		return this.ProdMap.values();
	}
	
	/**
	 * Retrieves a specific product, given its name
	 * @param name  name of the product
	 * @return  a product though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getProduct(String name){
		return this.ProdMap.get(name);
	}
	
	/**
	 * Creates a new recipe stored in this Food container.
	 *  
	 * @param name name of the recipe
	 * @return the newly created Recipe object
	 */
	public Recipe createRecipe(String name) {
		Recipe r = new Recipe(name, this);
		this.RecipeMap.put(r.getName(), r);
		return r;
	}
	
	/**
	 * Retrieves the collection of all defined recipes
	 * @return collection of recipes though the {@link NutritionalElement} interface
	 */
	public Collection<NutritionalElement> recipes(){
		return this.RecipeMap.values();
	}
	
	/**
	 * Retrieves a specific recipe, given its name
	 * @param name  name of the recipe
	 * @return  a recipe though the {@link NutritionalElement} interface
	 */
	public NutritionalElement getRecipe(String name){		
		return this.RecipeMap.get(name);
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu m = new Menu(name, this);
		this.MenuList.add(m);
		return m;
	}
	
	
	/**
	 * Save collection of RawMaterial and Product on a text file
	 * 
	 * raw material: 'M:name:calories,proteins,carbs,fats
	 * product     : 'P:name:calories,proteins,carbs,fats
	 * 
	 * @param filename path of destination file
	 * @throws IOException 
	 */
	public void save(String filename) throws IOException {
		
		//apro uno stream di output legato al filename fornito
		
		FileWriter out =  new FileWriter(filename);  //posso avere un0eccezione se il file non esiste
		
		//per ogni raw materal, lo scirvo su file
		
		for(NutritionalElement m : this.RawMat.values()) {
			out.write("M:");
			out.write(m.getName()+",");
			out.write(new Double(m.getCalories()).toString()+",");   //se facessi un cast (int) mi stamperbbe il carattere con il codice che gli ho passato
			//out.write(String.format("%6.2f", m.getClories()));
			out.write(String.format("%6.2f,", m.getCarbs()));
			out.write(String.format("%6.2f", m.getFat()));
			out.write("\n");
		}
		
		//per ogni prodetto lo scirvo su file
		
		for(NutritionalElement p : this.ProdMap.values()) {
			out.write(p.toFileString());
			out.write("\n");
		}
		
		//chiudo il file
		
		out.close();
	}
	
	/**
	 * legge i contenuti da un file con la sintassi del
	 * metodo save
	 * 
	 * @param filename
	 * @throws IOException 
	 */
	public void load(String filename) throws IOException {
		//apro uno stream di lettura
		
		FileReader in = new FileReader(filename);
		BufferedReader bin = new BufferedReader(in);
				
		//per ogni linea
		// - identifico il  tipo di elemento
		// - creo l'elemento corrispondente
		//(l'elemento delve essere inserito nella mappa)
		
		String linea;
		while((linea = bin.readLine()) != null) {
			char tag = linea.charAt(0);
			
			String[] tokens = linea.split("[:,]");
			
			switch(tag) {
			case 'M':
				break;
			case 'P':
				break;
			default:
				break;
			}
		}
		//chiudo il file
	}
	
}
