package scrapedata;

public class Recipe {
	private String recipeName;
	private String prepTime ;
	private String cookingTime;
	private String prepartionMethod;
	private String ingredients;
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}
	public String getCookingTime() {
		return cookingTime;
	}
	public void setCookingTime(String cookingTime) {
		this.cookingTime = cookingTime;
	}
	public String getPrepartionMethod() {
		return prepartionMethod;
	}
	public void setPrepartionMethod(String prepartionMethod) {
		this.prepartionMethod = prepartionMethod;
	}
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	@Override
	public String toString() {
		return "Recipe [recipeName=" + recipeName + ", prepTime=" + prepTime + ", cookingTime=" + cookingTime
				+ ", prepartionMethod=" + prepartionMethod + ", ingredients=" + ingredients + "]";
	}
		
	
	

}
