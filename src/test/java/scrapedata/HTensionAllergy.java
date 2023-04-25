package scrapedata;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utility.XLUtils;

public class HTensionAllergy extends Baseclass{
	
	private List<String> IngredientFilter() {
		   return Arrays.asList("Alchohol","Salty food",
				   "Fried food","White rice","White bread",
				   "Sauses","mayonnaise","Processed food",
				   "Canned food","Pickled","Frozen food");
		}
	private List<String> IngredientFilter2() {
		   return Arrays.asList("Milk","Soy",
				   "Egg","Sesame","Peanuts",
				   "walnut","almond hazelnut","pecan",
				   "cashew","pistachio","Shell fish","Seafood");
		}
	
	private static List<String> FoodCat() {
		return Arrays.asList("vegan","veg","jain");
	}

	private static List<String> RecipeCat() {
		return Arrays.asList("Breakfast","Lunch","Dinner","Snacks");
	}
	
	
	@Test
	public void recipeTODO () throws Exception {
		
		try {
		int cell=1;
		
		WebElement recepipesbtn = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
		recepipesbtn.click();
		
		WebElement hbpbtn  = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht167']"));
		hbpbtn.click();
		
		String morbidcon = driver.findElement(By.xpath("//h1[text()='High Blood Pressure recipes']")).getText();
		
		List<String> nameFilter = IngredientFilter();
		List<String> nameTODO = IngredientFilter2();
		System.out.println("nameTODO:----"+nameTODO);
		List<String> RecipeFilter = RecipeCat();
		List<String> foodFilter = FoodCat();
		
		int noofrecipepages = driver.findElements(By.xpath("//div[@id='pagination']/a")).size();
		System.out.println(noofrecipepages);
		
		String path =".\\sheets\\HTensionAllergy.xlsx";
		XLUtils xlutils=new XLUtils(path);
		
		//write headers in xlsheet
				try {
					xlutils.setCellData("Sheet1",0,0,"Recipe Id");
					xlutils.setCellData("Sheet1",0,1,"RecipeName");
					xlutils.setCellData("Sheet1",0,2,"Prepartion Time" );
					xlutils.setCellData("Sheet1",0,3,"Cook Time" );
					xlutils.setCellData("Sheet1",0,4," Method" );
					xlutils.setCellData("Sheet1",0,5,"Ingredients" );
					xlutils.setCellData("Sheet1",0,6,"Recipe URL");
					xlutils.setCellData("Sheet1",0,7,"Nutrient Values");
					xlutils.setCellData("Sheet1", 0, 8, "Morbid Condition");
					xlutils.setCellData("Sheet1", 0, 9, "Food Category");
					xlutils.setCellData("Sheet1", 0, 10, "Receipe Category");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
for (int i=1; i<=noofrecipepages; i++){
					
					WebElement pagenumber = driver.findElement(By.xpath("//div[@id='pagination']/a[" + i + "]"));
					pagenumber.click();
					
					List<WebElement> recipelinks=driver.findElements(By.xpath("//div//span[@class='rcc_recipename']"));
					System.out.println(recipelinks.size());
					
					for (int j=1; j<recipelinks.size(); j++){
						
						System.out.println("i am in j loop");
						recipelinks=driver.findElements(By.xpath("//div//span[@class='rcc_recipename']"));
					
						String recipeID=driver.findElement(By.xpath("(//div[@class='rcc_rcpno']//span[contains(text(),'Recipe#')])["+j+"]")).getText();
						String recUrl=driver.findElement(By.xpath("(//div[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a)["+j+"]")).getAttribute("href");
						
						Thread.sleep(1000);
						System.out.println(recipelinks.get(j).getText());
						recipelinks.get(j).click();
						String recipeName= driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblrecipeNameH2']")).getText();
						Thread.sleep(1000);
						String prepartionTime=driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText();
						Thread.sleep(1000);
						String cookingTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText();
						String prepMethod = driver.findElement(By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']//div[@id='recipe_small_steps']")).getText();
						Thread.sleep(1000);
						String ingredients = driver.findElement(By.xpath("//div[@id='recipe_ingredients']")).getText();
						Thread.sleep(3000);
						String Tag = driver.findElement(By.xpath("//*[@id='recipe_tags']")).getText();
						System.out.println("Tags:---"+Tag.toLowerCase());

						for (int f=0; f<foodFilter.size(); f++) {
							System.out.println("name:---"+foodFilter.get(f));
							String fname=foodFilter.get(f).toLowerCase();
							if (Tag.toLowerCase().contains(fname)) {
								xlutils.setCellData("Sheet1",cell,9,fname);
								break;
							}
						}

						for (int r=0; r<RecipeFilter.size(); r++) {
							System.out.println("name:---"+RecipeFilter.get(r));
							String Rname=RecipeFilter.get(r).toLowerCase();
							if (Tag.toLowerCase().contains(Rname)) {
								xlutils.setCellData("Sheet1",cell,10,Rname);
								break;
							}
						}
						String nutrient = driver.findElement(By.xpath("//table[@id='rcpnutrients']")).getText();
						try {
							if(driver.findElement(By.xpath("//table[@id='rcpnutrients']")).isDisplayed()) {
						
					         nutrient = driver.findElement(By.xpath("//table[@id='rcpnutrients']")).getText();
							}
							else {
								System.out.println("Nutrient value is not available");
								}
							}catch (Exception e) {
								e.printStackTrace();
				 			}
						
						//Thread.sleep(2000);
						
						Thread.sleep(5000);
						int p=0; int c=0;
						for (int k=0; k<nameFilter.size(); k++) {
							//System.out.println("name:---"+nameFilter.get(k));
							String name=nameFilter.get(k).toLowerCase();
							System.out.println("i am in k loop");
					
				            if (ingredients.toLowerCase().contains(name)) {
				            	 p=1;
				            	 System.out.println("i am in k if1");
				                 break;
				            }
						}  
				            
				           if (p==0) {
				        	   
				            	for (int q=0; q<nameTODO.size(); q++) {
				            		
									String name2=nameTODO.get(q).toLowerCase();
									
									System.out.println("Value of q is :" +q);
									System.out.println("name:---"+nameTODO.get(q).toLowerCase());
									System.out.println("ingrediants:----"+ingredients.toLowerCase());
									System.out.println("NameTODO Size is :--"+nameTODO.size());
									System.out.println("i am in q loop");
									
									if (ingredients.toLowerCase().contains(name2)) {
										c=1;
						         	System.out.println("i am in q if2");
						         	break;
									               }
									else {
										c=0;
										continue;
									}
				            	}
							  }
				           
				            if (c==0) {
//				            	System.out.println("Recipe ID"+recipeID);
//				            	System.out.println("Recipe URL"+recUrl);
//								System.out.println("Recipe Name:----- "+recipeName);
//								Thread.sleep(1000);
//								System.out.println("Preapartion Time:----- "+prepartionTime);
//								System.out.println("Cooking Time:-----  "+cookingTime);
//								System.out.println("Prepartion Method: ----- "+prepMethod);
//								Thread.sleep(1000);
//								System.out.println("Ingredients :-----  "+ingredients);
//								System.out.println("Nutrient Values:-----"+nutrient);
								xlutils.setCellData("Sheet1",cell, 0,recipeID );
								xlutils.setCellData("Sheet1",cell, 1, recipeName);
								xlutils.setCellData("Sheet1",cell, 2, prepartionTime);
								xlutils.setCellData("Sheet1",cell, 3, cookingTime);
								xlutils.setCellData("Sheet1",cell, 4, prepMethod);
								xlutils.setCellData("Sheet1",cell, 5, ingredients);
								xlutils.setCellData("Sheet1",cell, 6, recUrl);
								xlutils.setCellData("Sheet1",cell, 7, nutrient);
								xlutils.setCellData("Sheet1", cell, 8, morbidcon);
								cell++;
								driver.navigate().back();
								Thread.sleep(5000);
				            }
				            if (c==1) {
				            	driver.navigate().back();
				            	Thread.sleep(2000);
				            }
						
				            
				        if (p==1) { 
				          	driver.navigate().back();
				          	Thread.sleep(2000);
				          }
						
					}

				
				}

		}catch (Exception e) {
			e.printStackTrace();
			}
	}

}
