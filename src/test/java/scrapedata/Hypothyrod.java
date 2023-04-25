package scrapedata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utility.XLUtils;
import utility.Ingredients;

public class Hypothyrod extends Baseclass{

	private static List<String> FoodCat() {
		return Arrays.asList("vegan","veg","jain");
	}

	private static List<String> RecipeCat() {
		return Arrays.asList("Breakfast","Lunch","Dinner","Snacks");
	}

	@Test
	public static void HyperthyrodismSearch() throws IOException, InterruptedException {

		List<String> RecipeFilter = RecipeCat();
		List<String> FoodFilter = FoodCat();
		String nutrient = null;

		try {
			//HomePageRecipeButton
			WebElement RecipeBtn = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
			RecipeBtn.click();


			//HyperthyroidBtn
			WebElement HypothyroidBtn = driver.findElement(By.xpath("//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht226']"));
			HypothyroidBtn.click();


			// xlsheet path 
			String path =".\\sheets\\Hypothyroid Recipes.xlsx";
			XLUtils xlutils=new XLUtils(path);


			//write headers in xlsheet
			try {
				xlutils.setCellData("Hypothyroid Recipes",0,0,"Recipe Id");
				xlutils.setCellData("Hypothyroid Recipes",0,1,"RecipeName");
				xlutils.setCellData("Hypothyroid Recipes", 0, 2,"Prepartion Time" );
				xlutils.setCellData("Hypothyroid Recipes", 0, 3,"Cook Time" );
				xlutils.setCellData("Hypothyroid Recipes", 0, 4," Method" );
				xlutils.setCellData("Hypothyroid Recipes", 0, 5,"Ingredients" );
				xlutils.setCellData("Hypothyroid Recipes",0,6,"Recipe URL");
				xlutils.setCellData("Hypothyroid Recipes",0,7,"Nutrients");
				xlutils.setCellData("Hypothyroid Recipes",0,8,"Morbid Condition");
				xlutils.setCellData("Hypothyroid Recipes",0,9,"Food Category");
				xlutils.setCellData("Hypothyroid Recipes",0,10,"Recipe Category");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}




			//Pagination
			int k=1;
			List<WebElement> Pagination = driver.findElements(By.xpath("//*[@id='pagination']/a"));
			int NoOfPages = Pagination.size();

			for(int i=1;i<=NoOfPages;i++){	

				WebElement Paginator=driver.findElement(By.xpath("//*[@id='pagination']/a["+i+"]"));
				Paginator.click();
				Thread.sleep(2000);

				//Recipes
				int RecipeURLsize = driver.findElements(By.xpath("//*[@class='recipelist']//div//span[@class='rcc_recipename']//a")).size();

				for(int j=1;j<RecipeURLsize;j++) {

					try {
						boolean isEliminate=false;
						String recipeId=driver.findElement(By.xpath("//*[@class='rcc_recipecard']["+j+"]")).getAttribute("id");
						String recUrl=driver.findElement(By.xpath("//*[@class='rcc_recipecard']["+j+"]//div//span[@class='rcc_recipename']//a")).getAttribute("href");
						String MorbidCondition = driver.findElement(By.xpath("//*[@id='ctl00_cntleftpanel_lblSearchTerm']//h1")).getText();

						driver.findElements(By.xpath("//*[@class='recipelist']//div//span[@class='rcc_recipename']//a")).get(j).click();
						Thread.sleep(2000);
						String recipeName= driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']")).getText();
						String ingredients = driver.findElement(By.xpath("//div[@id='recipe_ingredients']/section")).getText();

						//Eliminate Ingredient Filter
						for (Ingredients.HypoThyroidEliminate eliminate : Ingredients.HypoThyroidEliminate.values()) {
							if(ingredients.toLowerCase().contains(eliminate.getHypoValue().toLowerCase())) {
								System.out.println("Skip Recipe : "+recipeName +" Found "+eliminate.getHypoValue());
								isEliminate=true;
								break;
							}
						}
						if(isEliminate) {
							//skip and continue to next recipe

							driver.navigate().back();
							continue;
						}

						String prepartionTime=driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText();
						String cookingTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText();
						String prepMethod = driver.findElement(By.xpath("//div[@id='recipe_small_steps']/span//ol")).getText();	
						String Tag = driver.findElement(By.xpath("//*[@id='recipe_tags']")).getText();

						//Food Category Filter
						for (int f=0; f<FoodFilter.size(); f++) {

							String FoodCategory=FoodFilter.get(f).toLowerCase();
							if (Tag.toLowerCase().contains(FoodCategory)) {

								xlutils.setCellData("Hypothyroid Recipes",k,9,FoodCategory);
								break;
							}
						}

						//Recipe Category Filter 
						for (int r=0; r<RecipeFilter.size(); r++) {

							String RecipeCategory=RecipeFilter.get(r).toLowerCase();
							if (Tag.toLowerCase().contains(RecipeCategory)) {

								xlutils.setCellData("Hypothyroid Recipes",k,10,RecipeCategory);
								break;
							}
						}				

						try {
							if(driver.findElement(By.xpath("//*[@id='rcpnutrients']")).isDisplayed()) {

								nutrient= driver.findElement(By.xpath("//*[@id='rcpnutrients']")).getText();							
							}
							else {
								System.out.println("Nutrient value is not available");
							}
						}catch (Exception e) {
							// TODO: handle exception {
							e.printStackTrace();
						}

						//printing on console
						Thread.sleep(2000);
						System.out.println("Recipe Name:----- "+recipeName);
						System.out.println("Preapartion Time:----- "+prepartionTime);
						System.out.println("Cooking Time:-----  "+cookingTime);
						System.out.println("Prepartion Method: ----- "+prepMethod);
						System.out.println("Ingredients :-----  "+ingredients);
						System.out.println("Nutrients :-----  "+nutrient);
						System.out.println("Morbid Condition :-----  "+MorbidCondition);

						System.out.println("******************************************************************************");

						//inserting data into xlsheet cell
						xlutils.setCellData("Hypothyroid Recipes", k, 0, recipeId );
						xlutils.setCellData("Hypothyroid Recipes", k, 1, recipeName);
						xlutils.setCellData("Hypothyroid Recipes", k, 2, prepartionTime);
						xlutils.setCellData("Hypothyroid Recipes", k, 3, cookingTime);
						xlutils.setCellData("Hypothyroid Recipes", k, 4, prepMethod);
						xlutils.setCellData("Hypothyroid Recipes", k, 5, ingredients);
						xlutils.setCellData("Hypothyroid Recipes", k, 6, recUrl);
						xlutils.setCellData("Hypothyroid Recipes", k, 7, nutrient);
						xlutils.setCellData("Hypothyroid Recipes",k,8,MorbidCondition);


						k++;
						driver.navigate().back();
					}catch (Exception e) {
						e.printStackTrace();

					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}