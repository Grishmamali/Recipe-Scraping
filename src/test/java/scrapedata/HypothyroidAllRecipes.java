package scrapedata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utility.Ingredients;
import utility.XLUtils;

public class HypothyroidAllRecipes extends Baseclass{

	private static List<String> FoodCat() {
		return Arrays.asList("vegan","veg","jain");
	}

	private static List<String> RecipeCat() {
		return Arrays.asList("Breakfast","Lunch","Dinner","Snacks");
	}

	@Test
	public static void HypothyroidAllergy() throws IOException, InterruptedException {

		List<String> RecipeFilter = RecipeCat();
		List<String> FoodFilter = FoodCat();
		String nutrient = null;


		try {

			//HomePageRecipeButton
			WebElement RecipeBtn = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
			RecipeBtn.click();

			//Morbid Text
			String MorbidText = driver.findElement(By.xpath("//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht226']")).getAttribute("text");
			String MorbidCondition = MorbidText.substring(0,19);

			//Click on Alphabet Filter
			WebElement AlphabetFilter = driver.findElement(By.xpath("//*[@id='toplinks']//*[contains(text(),'Recipe A To Z')]"));
			AlphabetFilter.click();

			// xlsheet path 
			String path =".\\sheets\\Hypothyroid All Recipes.xlsx";
			XLUtils xlutils=new XLUtils(path);


			//write headers in xlsheet
			try {
				xlutils.setCellData("Recipes",0,0,"Recipe Id");
				xlutils.setCellData("Recipes",0,1,"RecipeName");
				xlutils.setCellData("Recipes", 0, 2,"Prepartion Time" );
				xlutils.setCellData("Recipes", 0, 3,"Cook Time" );
				xlutils.setCellData("Recipes", 0, 4," Method" );
				xlutils.setCellData("Recipes", 0, 5,"Ingredients" );
				xlutils.setCellData("Recipes",0,6,"Recipe URL");
				xlutils.setCellData("Recipes",0,7,"Nutrients");
				xlutils.setCellData("Recipes",0,8,"Morbid Condition");
				xlutils.setCellData("Recipes",0,9,"Food Category");
				xlutils.setCellData("Recipes",0,10,"Recipe Category");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}




			//Pagination and all recipes	
			int k=1;
			
			//Click on Alphabet Filter
			//List<WebElement> AlphabetFilterBtn = driver.findElements(By.xpath("//*[@id='toplinks']//a[contains(text(),'Recipe A To Z')]"));
			List<WebElement> AlphabetFilterBtn = driver.findElements(By.xpath("//*//table/tbody/tr/td//a"));			
			int AlphaFilterCount =  AlphabetFilterBtn.size();
			
			System.out.println("Number of AlphaFilter------" +AlphaFilterCount);
		
			for(int y=2;y<=AlphaFilterCount;y++) {
				
				y=1+y;
				
				System.out.println("Number of AlphaFilter after add------" +y);
				
				WebElement Clickbtn = driver.findElement(By.xpath("//*//table/tbody/tr/td["+y+"]//a"));
				Clickbtn.click();

			List<WebElement> Pagination = driver.findElements(By.xpath("//*[@class='rcc_recipecard']//..//../*[@style='text-align:right;padding-bottom:15px;']/a"));
			int NoOfPages = Pagination.size();

			for(int i=1;i<=NoOfPages;i++){	
				WebElement Paginator=driver.findElement(By.xpath("//*[@class='rcc_recipecard']//..//../*[@style='text-align:right;padding-bottom:15px;']/a["+i+"]"));
				Paginator.click();
				Thread.sleep(2000);

				//Recipes
				int RecipesURLsize = driver.findElements(By.xpath("//div[@class='rcc_recipecard']//div//span[@class='rcc_recipename']/a")).size();

				for(int j=1;j<RecipesURLsize;j++) {

					try {

						boolean isEliminate=false;
						boolean isAllergy=false;

						String recipeId=driver.findElement(By.xpath("//*[@class='rcc_recipecard']["+j+"]//span[contains(text(),'Recipe#')]")).getText();
						String recUrl=driver.findElement(By.xpath("//div[@class='rcc_recipecard']["+j+"]//div//span[@class='rcc_recipename']/a")).getAttribute("href");

						driver.findElements(By.xpath("//*[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a")).get(j).click();
						Thread.sleep(2000);
						String recipeName= driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']")).getText();
						String ingredients = driver.findElement(By.xpath("//div[@id='recipe_ingredients']/section")).getText();

						//Eliminate Ingredient Filter
						for (Ingredients.HypoThyroidEliminate eliminate : Ingredients.HypoThyroidEliminate.values()) {
							if(ingredients.toLowerCase().contains(eliminate.getHypoValue().toLowerCase())) {
								System.out.println("Skip Recipe : "+recipeName+ " Found "+eliminate.getHypoValue());
								isEliminate=true;
								break;
							}
						}
						if(isEliminate) {
							//skip and continue to next recipe

							driver.navigate().back();
							continue;
						}

						//Allergy Ingredient Filter
						for (Ingredients.HypoThyroidAllergies allergy : Ingredients.HypoThyroidAllergies.values()) {
							if(ingredients.toLowerCase().contains(allergy.getAllergyValue().toLowerCase())) {
								System.out.println("Skip Recipe : "+ recipeName +" Found allergic ingrediant " +allergy.getAllergyValue());
								isAllergy=true;
								break;
							}
						}
						if(isAllergy) {
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

								xlutils.setCellData("Recipes",k,9,FoodCategory);
								break;
							}
						}

						//Recipe Category Filter 
						for (int a=0; a<RecipeFilter.size(); a++) {

							String RecipeCategory=RecipeFilter.get(a).toLowerCase();

							if (Tag.toLowerCase().contains(RecipeCategory)) {

								xlutils.setCellData("Recipes",k,10,RecipeCategory);
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

						xlutils.setCellData("Recipes", k, 0, recipeId );
						xlutils.setCellData("Recipes", k, 1, recipeName);
						xlutils.setCellData("Recipes", k, 2, prepartionTime);
						xlutils.setCellData("Recipes", k, 3, cookingTime);
						xlutils.setCellData("Recipes", k, 4, prepMethod);
						xlutils.setCellData("Recipes", k, 5, ingredients);
						xlutils.setCellData("Recipes", k, 6, recUrl);
						xlutils.setCellData("Recipes", k, 7, nutrient);
						xlutils.setCellData("Recipes",k,8,MorbidCondition);


						k++;
						driver.navigate().back();
					}catch (Exception e) {
						e.printStackTrace();

					}
				}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}


}

