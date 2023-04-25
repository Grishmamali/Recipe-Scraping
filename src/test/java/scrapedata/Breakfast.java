package scrapedata;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import utility.XLUtils;

public class Breakfast extends Baseclass{
	
	@Test
	public void breakfast() throws InterruptedException
	{
		
		WebElement recipeTab=driver.findElement(By.xpath("//div[@class='menucontainer_div']"));
		recipeTab.click();
		WebElement breakFastTab=driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_cattreecourse_tvCourset10']"));
		breakFastTab.click();
		Thread.sleep(2000); 
		//Pagination //

				List<WebElement>  Pagination =driver.findElements(By.xpath("//*[@id='pagination']/a"));
				int pagesize= Pagination.size();
				//Counting the page size
				System.out.println("pages "+pagesize);
				
				// xlsheet path 
						String path ="C:\\Users\\rahul\\eclipse-workspace\\RecipeScraping\\sheets\\recipe.xlsx";
						XLUtils xlutils=new XLUtils(path);

						//write headers in xlsheet
						try {
							xlutils.setCellData("Breakfast",0,0,"Recipe Id");
							xlutils.setCellData("Breakfast",0,1,"RecipeName");
							xlutils.setCellData("Breakfast", 0, 2,"Prepartion Time" );
							xlutils.setCellData("Breakfast", 0, 3,"Cook Time" );
							xlutils.setCellData("Breakfast", 0, 4," Method" );
							xlutils.setCellData("Breakfast", 0, 5,"Ingredients" );
							xlutils.setCellData("Breakfast",0,6,"Recipe URL");
							xlutils.setCellData("Breakfast",0,7,"Nutrient Values");
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						//iteration of page
						int k=1;

						for (int j = 1; j <=2; j++) {
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
								
								System.out.println("1##############"+i);
								try {
									Thread.sleep(1000);
								String recipeId=driver.findElement(By.xpath("(//div[@class='rcc_rcpno']//span[contains(text(),'Recipe#')])["+i+"]")).getText();
								Thread.sleep(1000);
								String recUrl=driver.findElement(By.xpath("(//div[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a)["+i+"]")).getAttribute("href");
								WebElement recipeLinks = driver.findElement(By.xpath("(//div[@class='rcc_rcpcore']//span[@class='rcc_recipename']//a[@itemprop='url'])["+i+"]"));
								recipeLinks.click();
								Thread.sleep(2000);
								String recipeName= driver.findElement(By.xpath("//span[@id='ctl00_cntrightpanel_lblrecipeNameH2']")).getText();
								String ingredients = driver.findElement(By.xpath("//div[@id='recipe_ingredients']")).getText();
								
								String prepartionTime=driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText();
								String cookingTime = driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText();
								String prepMethod = driver.findElement(By.xpath("//div[@id='ctl00_cntrightpanel_pnlRcpMethod']//div[@id='recipe_small_steps']")).getText();
								String nutrient = driver.findElement(By.xpath("//*[@id=\"rcpnutrients\"]")).getText();
								//printing on console
								System.out.println("Recipe Name:----- "+recipeName);
								System.out.println("Preapartion Time:----- "+prepartionTime);
								System.out.println("Cooking Time:-----  "+cookingTime);
								System.out.println("Prepartion Method: ----- "+prepMethod);
								System.out.println("Ingredients :-----  "+ingredients);
								System.out.println("******************************************************************************");
								//inserting data into xlsheet cell
								xlutils.setCellData("Breakfast", k, 0,recipeId );
								xlutils.setCellData("Breakfast", k, 1, recipeName);
								xlutils.setCellData("Breakfast", k, 2, prepartionTime);
								xlutils.setCellData("Breakfast", k, 3, cookingTime);
								xlutils.setCellData("Breakfast", k, 4, prepMethod);
								xlutils.setCellData("Breakfast", k, 5, ingredients);
								xlutils.setCellData("Breakfast", k, 6, recUrl);
								xlutils.setCellData("Breakfast", k, 6, nutrient);

								k++;
								driver.navigate().back();
								Thread.sleep(4000);

							} catch (Exception e) {
								e.printStackTrace();

							}
								
								
								
							}

				//String recipeId=driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+i+"]//div[@class='rcc_rcpno']//span[contains(text(),'Recipe#')]")).getText();
			}
			}
	

}
