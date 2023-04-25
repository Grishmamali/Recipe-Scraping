package utility;

public class Ingredients {

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

}



