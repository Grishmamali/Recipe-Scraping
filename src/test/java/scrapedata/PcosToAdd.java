package scrapedata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import utility.Ingredients;
import utility.XLUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class PcosToAdd extends Baseclass{

	private static List<String> FoodCat() {
		return Arrays.asList("vegan","veg","jain");
	}

	private static List<String> Recipefilter() {
		return Arrays.asList("Breakfast","Lunch","Dinner","Snacks");
	}

	@Test
	public static void DiabeticSearch() throws IOException, InterruptedException {

		List<String> nameFilter = FoodCat();
		List<String> recipefilter = Recipefilter();

		// String nutrient = null;

		// HomePageRecipeButton
		WebElement recipetab = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
		recipetab.click();
		WebElement PcosRecipe = driver.findElement(By.xpath("//*[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335']"));
		PcosRecipe.click();
//		WebElement recipetab = driver.findElement(By.xpath("//div[@class='menucontainer_div']"));
//		WebElement helthtab = driver.findElement(By.xpath("//a[contains(@href,'focus=health')]"));
//		WebElement diareacpi = driver.findElement(By.xpath("//a[contains(text(),'Delicious Diabetic Recipes')]"));
//		Actions action = new Actions(driver);
//		action.moveToElement(recipetab).perform();
//		action.moveToElement(helthtab).build().perform();
//		Thread.sleep(2000);
//		action.moveToElement(diareacpi).build().perform();
//		diareacpi.click();
		Thread.sleep(2000);

		String morbodities = driver
				.findElement(By.xpath("//span[@id='ctl00_cntleftpanel_lblSearchTerm']/span/h1")).getText();

		try {
			// xlsheet Reader path
			String path1 = ".\\sheets\\TestData.xlsx";
			XLUtils xlutilsRead = new XLUtils(path1);

			int totalRows = xlutilsRead.getRowCount("PCOSToAdd");
			int totalcols = xlutilsRead.getCellCount("PCOSToAdd", 2);
			for (int r = 1; r <= totalRows; r++) {

				for (int c = 0; c < totalcols; c++) {

					// write headers in xlsheet
					try {
						String searchData = xlutilsRead.getCellData("PCOSToAdd", r, c);
						System.out.println("Now searching recipes for ------------->" + searchData);

						// Enter Value in Search
						WebElement SearchRecipe = driver.findElement(By.xpath("//*[@id='ctl00_txtsearch']"));
						SearchRecipe.sendKeys(searchData);

						// Search Button
						WebElement SearchBtn = driver.findElement(By.xpath("//*[@id='ctl00_imgsearch']"));
						SearchBtn.click();
						Thread.sleep(2000);

						// xlsheet Write path
						String path = ".\\sheets\\To_Add_PCOS.xlsx";
						XLUtils xlutils = new XLUtils(path);

						xlutils.setCellData(searchData, 0, 0, "Recipe Id");
						xlutils.setCellData(searchData, 0, 1, "RecipeName");
						xlutils.setCellData(searchData, 0, 2, "Prepartion Time");
						xlutils.setCellData(searchData, 0, 3, "Cook Time");
						xlutils.setCellData(searchData, 0, 4, " Method");
						xlutils.setCellData(searchData, 0, 5, "Ingredients");
						xlutils.setCellData(searchData, 0, 6, "Recipe URL");
						xlutils.setCellData(searchData, 0, 7, "Nutrients");
						xlutils.setCellData(searchData, 0, 8, "Food category");
						xlutils.setCellData(searchData, 0, 9, "morbodities");
						xlutils.setCellData(searchData, 0, 10, "Recipe Category");

						// Pagination and all recipes
						int k = 1;
						List<WebElement> Pagination = driver.findElements(By.xpath(
								"//*[@class='rcc_recipecard']//..//../*[@style='text-align:right;padding-bottom:15px;']/a"));
						int NoOfPages = Pagination.size();
						System.out.println("SIZE :" + NoOfPages);
						for (int i = 1; i <= NoOfPages; i++) {

							WebElement Paginator = driver.findElement(By.xpath(
									"//*[@class='rcc_recipecard']//..//../*[@style='text-align:right;padding-bottom:15px;']/a["
											+ i + "]"));
							Paginator.click();
							Thread.sleep(2000);

							int RecipesURLsize = driver
									.findElements(
											By.xpath("//*[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a"))
									.size();
							System.out.println("URL SIZE:" + RecipesURLsize);
							for (int j = 1; j < RecipesURLsize; j++) {

								try {

									boolean isEliminate = false;

									String recipeId = driver.findElement(By.xpath("//div[@class='rcc_recipecard'][" + j
											+ "]//div//span[contains(text(),'Recipe#')]")).getText();
									String recUrl = driver.findElement(By.xpath("//div[@class='rcc_recipecard'][" + j
											+ "]//div//span[@class='rcc_recipename']/a")).getAttribute("href");

									driver.findElements(
											By.xpath("//*[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a"))
											.get(j).click();
									Thread.sleep(2000);
									String recipeName = driver
											.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblRecipeName']"))
											.getText();
									String ingredients = driver
											.findElement(By.xpath("//div[@id='recipe_ingredients']/section")).getText();
									System.out.println("RECIPE NAME " + recipeName);
									for (Ingredients.PcosEliminate eliminate : Ingredients.PcosEliminate
											.values()) {
										if (ingredients.toLowerCase().contains(eliminate.pcosgetValue().toLowerCase())) {
											System.out.println(
													"Skip Recipe : " + recipeName + " Found " + eliminate.pcosgetValue());
											isEliminate = true;
											break;
										}
									}

									if (isEliminate) {
										// skip and continue to next recipe

										driver.navigate().back();
										Thread.sleep(2000);
										continue;
									}

									String Tag = driver.findElement(By.xpath("//*[@id='recipe_tags']")).getText();

									for (int f = 0; f < nameFilter.size(); f++) {

										String name = nameFilter.get(f);
										if (Tag.toLowerCase().contains(name)) {
											xlutils.setCellData(searchData, k, 9, name);
											break;
										}
									}

									for (int a = 0; a < recipefilter.size(); a++) {

										String Rname = recipefilter.get(a);
										if (Tag.toLowerCase().contains(Rname)) {
											xlutils.setCellData(searchData, k, 10, Rname);
											break;
										}
									}
									String prepartionTime = "";

									if (isElementPresent(By.xpath("//time[@itemprop='prepTime']"))) {
										prepartionTime = driver.findElement(By.xpath("//time[@itemprop='prepTime']"))
												.getText();
									}
									String cookingTime = "";
									if (isElementPresent(By.xpath("//time[@itemprop='cookTime']"))) {
										cookingTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']"))
												.getText();
									}
									String prepMethod = "";
									if (isElementPresent(By.xpath("//div[@id='recipe_small_steps']/span//ol"))) {
										prepMethod = driver
												.findElement(By.xpath("//div[@id='recipe_small_steps']/span//ol"))
												.getText();
									}

									String nutrient = "";

									if (isElementPresent(By.xpath("//*[@id='rcpnutrients']"))) {
										nutrient = driver.findElement(By.xpath("//*[@id='rcpnutrients']")).getText();
									} else {
										System.out.println("Nutrient value is not available");
									}

									// printing on console
									System.out.println("Recipe Name:----- " + recipeName);
									System.out.println("Preapartion Time:----- " + prepartionTime);
									System.out.println("Cooking Time:-----  " + cookingTime);
									System.out.println("Prepartion Method: ----- " + prepMethod);
									System.out.println("Ingredients :-----  " + ingredients);
									System.out.println("Nutrients :-----  " + nutrient);
									System.out.println("Morbid Condition :-----  " + morbodities);

									System.out.println(
											"******************************************************************************");
									// inserting data into xlsheet cell
									xlutils.setCellData(searchData, k, 0, recipeId);
									xlutils.setCellData(searchData, k, 1, recipeName);
									xlutils.setCellData(searchData, k, 2, prepartionTime);
									xlutils.setCellData(searchData, k, 3, cookingTime);
									xlutils.setCellData(searchData, k, 4, prepMethod);
									xlutils.setCellData(searchData, k, 5, ingredients);
									xlutils.setCellData(searchData, k, 6, recUrl);
									xlutils.setCellData(searchData, k, 7, nutrient);
									for (int f = 0; f < nameFilter.size(); f++) {

										String name = nameFilter.get(f);
										if (Tag.toLowerCase().contains(name)) {
											System.out.println("name:---" + name);
											xlutils.setCellData(searchData, k, 8, name);
											break;
										}
									}
									xlutils.setCellData(searchData, k, 9, morbodities);

									for (int f = 0; f < recipefilter.size(); f++) {
										System.out.println("Recipe Category:---" + recipefilter.get(f));
										String recpi_cat = recipefilter.get(f);
										if (Tag.toLowerCase().contains((recpi_cat).toLowerCase())) {
											System.out.println("Inside tag true condition:" + recpi_cat);
											xlutils.setCellData(searchData, k, 10, recpi_cat);
											break;
										}
									}

									k++;
									driver.navigate().back();
									Thread.sleep(2000);
								} catch (Exception e) {
									e.printStackTrace();

								}

							}
						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static boolean isElementPresent(By locatorKey) {
	    try {
	        driver.findElement(locatorKey);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	
	

}
