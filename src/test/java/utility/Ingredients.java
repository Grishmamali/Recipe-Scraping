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
	}
