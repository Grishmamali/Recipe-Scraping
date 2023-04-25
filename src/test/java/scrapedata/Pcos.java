package scrapedata;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

//import baseClass.TestBase;
import utility.Ingredients;
import utility.Ingredients.PcosAllergies;
import utility.XLUtils;

public class Pcos extends Baseclass{
	private static List<String> FoodCat() {
		return Arrays.asList("vegan","veg","jain");
	}
	
	
	private static List<String> Recipefilter(){
		return Arrays.asList("Brakfast","Snack","Dinner","Lunch");
	}

	@Test 
	public void diabetesrecipe() throws InterruptedException
	{
		
		List<String> nameFilter = FoodCat();
		List<String> recipefilter = Recipefilter();
		
		//recipe tab on home page 
		WebElement recipetab = driver.findElement(By.xpath("//div[@class='menucontainer_div']"));
		recipetab.click();
		WebElement PcosRecipe = driver.findElement(By.xpath("//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335']"));
		PcosRecipe.click();
//		WebElement helthtab=driver.findElement(By.xpath("//a[contains(@href,'focus=health')]"));
//		WebElement diareacpi=driver.findElement(By.xpath("//a[contains(text(),'Delicious Diabetic Recipes')]"));
//		Actions action =new Actions(driver);
//		action.moveToElement(recipetab).perform(); 
//		action.moveToElement(helthtab).build().perform();
//		Thread.sleep(2000); 
//		action.moveToElement(diareacpi).build().perform();
//		diareacpi.click();
		Thread.sleep(2000); 
		
		String morbodities =driver.findElement(By.xpath("//*[@style='font:inherit;display:inline;margin:0px;padding:0px;']")).getText();

		//Pagination //

		List<WebElement>  Pagination =driver.findElements(By.xpath("//*[@id='pagination']/a"));
		int pagesize= Pagination.size();
		//Counting the page size
		System.out.println("pages "+pagesize);


		// xlsheet path 
		String path =".\\sheets\\Pcos_Eliminate_Allergy.xlsx";
		XLUtils xlutils=new XLUtils(path);

		//write headers in xlsheet
		try {
			xlutils.setCellData("PcosEliminate",0,0,"Recipe Id");
			xlutils.setCellData("PcosEliminate",0,1,"RecipeName");
			xlutils.setCellData("PcosEliminate", 0, 2,"Prepartion Time" );
			xlutils.setCellData("PcosEliminate", 0, 3,"Cook Time" );
			xlutils.setCellData("PcosEliminate", 0, 4," Method" );
			xlutils.setCellData("PcosEliminate", 0, 5,"Ingredients" );
			xlutils.setCellData("PcosEliminate",0,6,"Recipe URL");
			xlutils.setCellData("PcosEliminate",0,7,"Nutrient Values");
			xlutils.setCellData("PcosEliminate",0,8,"Food category");
			xlutils.setCellData("PcosEliminate",0,9,"morbodities");
			xlutils.setCellData("PcosEliminate", 0, 10, "Recipe Category");
			
			//write headers in xlsheet for allergies ingredient
			
			xlutils.setCellData("PcosAllergies",0,0,"Recipe Id");
			xlutils.setCellData("PcosAllergies",0,1,"RecipeName");
			xlutils.setCellData("PcosAllergies", 0, 2,"Prepartion Time" );
			xlutils.setCellData("PcosAllergies", 0, 3,"Cook Time" );
			xlutils.setCellData("PcosAllergies", 0, 4," Method" );
			xlutils.setCellData("PcosAllergies", 0, 5,"Ingredients" );
			xlutils.setCellData("PcosAllergies",0,6,"Recipe URL");
			xlutils.setCellData("PcosAllergies",0,7,"Nutrient Values");
			xlutils.setCellData("PcosAllergies",0,8,"Food category");
			xlutils.setCellData("PcosAllergies",0,9,"morbodities");
			xlutils.setCellData("PcosAllergies", 0, 10, "Recipe Category");
			
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//iteration of page
		int k=1;

		for (int j = 1; j <=pagesize; j++) {
			System.out.println("PAGE "+j);

			Thread.sleep(1000);
			WebElement Paginator=driver.findElement(By.xpath("//*[@id='pagination']/a["+j+"]"));
			Paginator.click();
			Thread.sleep(1000);
			List<WebElement> recipecards=driver.findElements(By.xpath("//div[@class='recipelist']//article[@class='rcc_recipecard']"));
			int recipecardno=recipecards.size();
			System.out.println(recipecardno);

			//	//iteration of recipe cards
			for(int i=1;i<=recipecardno;i++)
			{    
				try {

					boolean isEliminate=false;
					boolean isAllergy=false;
					String recipeId=driver.findElement(By.xpath("(//div[@class='rcc_rcpno']//span[contains(text(),'Recipe#')])["+i+"]")).getText();
					String recUrl=driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+i+"]//div[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a")).getAttribute("href");

					WebElement recipeLinks = driver.findElement(By.xpath("//div[@class='recipelist']//article["+i+"]//span[@itemprop='name']//a[@itemprop='url']"));
					recipeLinks.click();
					Thread.sleep(1000);
					String recipeName= driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblrecipeNameH2']")).getText();
					String ingredients = driver.findElement(By.xpath("//div[@id='recipe_ingredients']")).getText();
					
					for (Ingredients.PcosEliminate eliminate : Ingredients.PcosEliminate.values()) {
						if(ingredients.toLowerCase().contains(eliminate.pcosgetValue().toLowerCase())) {
							System.out.println("Skip Recipe : "+recipeName +" Found "+eliminate.pcosgetValue());
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
					String prepMethod = driver.findElement(By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']//div[@id='recipe_small_steps']")).getText();
					String nutrient = driver.findElement(By.xpath("//*[@id=\"rcpnutrients\"]")).getText();
					String tags=driver.findElement(By.xpath("//div[@id='recipe_tags']")).getText();
					//printing on console
					Thread.sleep(3000);
					System.out.println("Recipe Name:----- "+recipeName);
					System.out.println("Preapartion Time:----- "+prepartionTime);
					System.out.println("Cooking Time:-----  "+cookingTime);
					System.out.println("Prepartion Method: ----- "+prepMethod);
					System.out.println("Ingredients :-----  "+ingredients);
					System.out.println("******************************************************************************");
					//inserting data into xlsheet cell
					xlutils.setCellData("PcosEliminate", k, 0,recipeId );
					xlutils.setCellData("PcosEliminate", k, 1, recipeName);
					xlutils.setCellData("PcosEliminate", k, 2, prepartionTime);
					xlutils.setCellData("PcosEliminate", k, 3, cookingTime);
					xlutils.setCellData("PcosEliminate", k, 4, prepMethod);
					xlutils.setCellData("PcosEliminate", k, 5, ingredients);
					xlutils.setCellData("PcosEliminate", k, 6, recUrl);
					xlutils.setCellData("PcosEliminate", k, 7, nutrient);
					
					//checking Food category 
					for (int f=0; f<nameFilter.size(); f++) {
						
						String name=nameFilter.get(f);
						if (tags.toLowerCase().contains(name)) {
							System.out.println("name:---"+name);
							xlutils.setCellData("PcosEliminate",k,8,name);
							break;
						}
					}
					xlutils.setCellData("PcosEliminate", k, 9, morbodities);
					

					for (int f=0; f<recipefilter.size(); f++) {
						System.out.println("Recipe Category:---"+recipefilter.get(f));
						String recpi_cat=recipefilter.get(f);
						if (tags.toLowerCase().contains((recpi_cat).toLowerCase())) {
							System.out.println("Inside tag true condition:"+recpi_cat);
							xlutils.setCellData("PcosEliminate",k,10,recpi_cat);
							break;
						}
					}
					
					// Allergy
					for (PcosAllergies allergy : Ingredients.PcosAllergies.values()) {
						if(ingredients.toLowerCase().contains(allergy.getAllergyValue().toLowerCase())) {
							System.out.println("Skip Recipe : "+recipeName +" Found Allergy: "+allergy.getAllergyValue());
							isAllergy=true;
							break;
						}
					}
					
					
					
					if(isAllergy) {
						//skip and continue to next recipe
						
						driver.navigate().back();
						continue;
					}
					
					//Adding data into allergy sheet
					
					xlutils.setCellData("PcosAllergies", k, 0,recipeId );
					xlutils.setCellData("PcosAllergies", k, 1, recipeName);
					xlutils.setCellData("PcosAllergies", k, 2, prepartionTime);
					xlutils.setCellData("PcosAllergies", k, 3, cookingTime);
					xlutils.setCellData("PcosAllergies", k, 4, prepMethod);
					xlutils.setCellData("PcosAllergies", k, 5, ingredients);
					xlutils.setCellData("PcosAllergies", k, 6, recUrl);
					xlutils.setCellData("PcosAllergies", k, 7, nutrient);
					
					for (int f=0; f<nameFilter.size(); f++) {
						System.out.println("name:---"+nameFilter.get(f));
						String name=nameFilter.get(f);
						if (tags.toLowerCase().contains(name)) {
							xlutils.setCellData("PcosAllergies",k,8,name);
							System.out.println(name);
							break;
						}
					}
					xlutils.setCellData("PcosAllergies", k, 9, morbodities);
					
					
					for (int f=0; f<recipefilter.size(); f++) {
						System.out.println("name:---"+recipefilter.get(f));
						String recpi_cat=recipefilter.get(f);
						if (tags.toLowerCase().contains((recpi_cat).toLowerCase())) {
							xlutils.setCellData("PcosAllergies",k,10,recpi_cat);
							System.out.println(recpi_cat);
							break;
						}
					}
					


	
				} catch (Exception e) {
					e.printStackTrace();

				}

				k++;
				driver.navigate().back();



			}




			////article[@class='rcc_recipecard']//div[@class='rcc_rcpno']//span  for id 
			////article[@class='rcc_recipecard']//span[@class='rcc_recipename']//for url





		}



	}}