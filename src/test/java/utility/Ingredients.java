package utility;

public class Ingredients {

	public enum HypoThyroidEliminate{

		Tofu("Tofu"),
		Edamame("Edamame"),
		Tempeh("Tempeh"),
		Cauliflower("Cauliflower"),
		Cabbage("Cabbage"),
		Broccoli("Broccoli"),
		Kale("Kale"),
		Spinach("Spinach"),
		Sweet_potatoes("Sweet potatoes"),
		Strawberries("Strawberries"),
		Strawbery("Strawbery"),
		Pine_nuts("Pine nuts"),
		Peanuts("Peanuts"),
		Peaches("Peaches"),
		Green_tea("Green tea"),
		Coffee("Coffee"),
		Alcohol("Alcohol"),
		Soy_milk("Soy milk"),
		White_bread("White bread"),
		Cakes("Cakes"),
		pastries("pastries"),
		pastery("Pastery"),
		Fried("Fried"),
		Fry("Fry"),
		Sugar("Sugar"),
		Frozen_food("Frozen food"),
		Gluten("Gluten"),
		Soda("Soda"),
		Chocolate ("Chocolate"),
		Package_Noodles ("Package Noodles"),
		Package_soup ("Package soup"),
		Package_dressing ("Package dressing"),
		Package_sauce ("Package sauce"),
		Pepsi ("Pepsi"),
		coke("coke"),
		sparkling_water("sparkling water");


		private String HypoValue;
		HypoThyroidEliminate(String HypoValue) {
			this.HypoValue=HypoValue;
		}

		public String getHypoValue() {
			return HypoValue;
		}
	}
	
	public enum HypoThyroidAllergies{

		Milk("Milk"),
		Soy("Soy"),
		Egg("Egg"),
		Sesame("Sesame"),
		Peanuts("Peanuts"),
		Walnut("Walnut"),
		Almond("Almond"),
		Hazelnut("Hazelnut"),
		Pecan("Pecan"),
		Cashew("Cashew"),		
		Pistachio("Pistachio");

		private String AlleryValue;
		HypoThyroidAllergies(String AlleryValue) {
			this.AlleryValue=AlleryValue;
		}

		public String getAllergyValue() {
			return AlleryValue;
		}
	}


	
public enum DiabetesEliminate{
		
	Cream_of_rice("Cream of rice"),
		rice_flour("rice flour"),
	    rice_rava("rice rava"),
		 corn("corn"),
		 suger("suger"),
		 white_rice("white rice"),
		 White_bread("White bread"),
		 Pasta("Pasta"),
		soda("soda"),
		flovoured_water("Pasta"),
		gatorade("gatorade"),
		 Apple_Juice("Apple_Juice"),
		 orange_juice("Apple_Juice"),
		 pomegranate_juice("pomegranate_juice"),
		 margarines("margarines"),
		 peanut_butter("peanut_butter"), 
		 spreads("spreads"), 
		 frozen_foods("frozen_foods"),
		 Flavoured_curd("Flavoured curd"),
		 Flavoured_yogurt("Flavoured yogurt"),
		  cereals(" cereals"),
		corn_flakes("corn_flakes"), 
		puffed_rice("puffed_rice"), 
		bran_flakes("bran_flakes"), 
	   instant_oatmeal("instant_oatmeal"),
	   Honey("Honey"),
	   Maple_syrup("Maple syrup"),
	   Jaggery("Jaggery"),
		Sweets("Sweets"),
		Candies("Candies"),
		Jelly("Jelly"),
		Pickled_mango("Pickled_mango"), 
		Pickled_cucumber("Pickled cucumber"), 
		Pickled_tomatoes("Pickled tomatoes"),
		Canned_pineapple("Canned pineapple"),
		Canned_peaches("Canned peaches"), 
		Canned_mangos("Canned mangos"), 
		Cannedpear("Canned pear"), 
		Canned_mandarine("Canned mandarine"), 
		Canned_oranges("Canned oranges"), 
		Canned_cherries("Canned_cherries"),
		Chips("Chips"),
		Mayonnaise("Mayonnaise "),
		Palmolein_oil("Palmolein_oil"),
		Doughnuts("Doughnuts"),
		cakes("cakes"),
		pastries("pastries"), 
		cookies("cookies"),
		croissants("croissants"),
		Sweetened_tea("Sweetened tea"), 
		Sweetened_coffee("Sweetened coffee"),	
		Packaged_snacks("Packaged snacks"),
		Soft_drinks("Soft drinks"),
		Banana("Banana"), 
		melon("melon"),
		Milk("Milk"), butter("butter"), cheese("cheese");
		


		private String value;

		DiabetesEliminate(String value) {
			this.value=value;
		}

		public String getValue() {
			return value;
		}
		
	}
	
	public enum Allergies{
		
		Peanuts("Peanuts"),
		milk("milk"),
		soy("soy"),
		egg("egg"),
		Sesame("Sesame"),
		walnut("walnut"),
		almond("almond"),
		hazelnut("hazelnut"),
		pecan("pecan"),
		cashew("cashew"),
		pistachio("pistachio"),
		Shell_fish("Shell fish"),
		Seafood("Seafood");
	
		
		
		private String value;

		Allergies(String value) {
			this.value=value;
		}

		public String getValue() {
			return value;
		}
		
	}




public enum PcosAllergies{

	Milk("Milk"),
	Soy("Soy"),
	Egg("Egg"),
	Sesame("Sesame"),
	Peanuts("Peanuts"),
	Walnut("Walnut"),
	Almond("Almond"),
	Hazelnut("Hazelnut"),
	Pecan("Pecan"),
	Cashew("Cashew"),		
	Pistachio("Pistachio");

	private String PcosAllergyValue;
	PcosAllergies(String PcosAllergyValue) {
		this.PcosAllergyValue=PcosAllergyValue;
	}

	public String getAllergyValue() {
		return PcosAllergyValue;
	}
}
public enum PcosEliminate{
	
	 White_bread("White bread"),
	 Pasta("Pasta"),
	soda("soda"),
	flovoured_water("flavoured_water"),	
	Soda("soda"),
	Carbonated_beverages("carbonated_beverages"),
	Burger("burger"),
	Pizza("pizza"),
	Fried_food("fried_food"),
	gatorade("gatorade"),
	 Apple_Juice("Apple_Juice"),
	 orange_juice("Orange_Juice"),
	 pomegranate_juice("pomegranate_juice"),
	 margarines("margarines"),
  Honey("Honey"),
  Maple_syrup("Maple syrup"),
  Jaggery("Jaggery"),
	Sweets("Sweets"),
	Candies("Candies"),
	Jelly("Jelly"),
	Chips("Chips"),
	Palmolein_oil("Palmolein_oil"),
	Vegetable_oil("vegetable_oil"),
	Soyabean_oil("soyabean_oil"),
	Canola_oil("Canola_oil"),
	Rapseed_oil("rapseed_oil"),
	Sunflower_oil("sunflower_oil"),
	Safflower_oil("safflower_oil"),
	Coffee("coffee"),
	Fries("Fries"),
	Gluten("Gluten"),
	Soy("soy"),
	Soy_sauce("soy_sauce"),
	Soya_sauce("soya_sauce"),
	Soy_milk("soy_milk"),
	Tofu("tofu"),
	Meat("meat"),
	Processed_meat("processed_meat"),
	Red_meat("Red_meat"),
	Doughnuts("Doughnuts"),
	cakes("cakes"),
	pastries("pastries"), 
	cookies("cookies"),
	croissants("croissants"),
	Sweetened_tea("Sweetened tea"), 
	Sweetened_coffee("Sweetened coffee"),	
	Packaged_snacks("Packaged snacks"),
	Soft_drinks("Soft drinks"),
	Milk("Milk"), butter("butter"), cheese("cheese");
	


	private String pcosvalue;

	PcosEliminate(String pcosvalue) {
		this.pcosvalue=pcosvalue;
	}

	public String pcosgetValue() {
		return pcosvalue;
	}
	
}
}






