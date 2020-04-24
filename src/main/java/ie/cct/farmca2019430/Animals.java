package ie.cct.farmca2019430;

public class Animals {
	
	private String animalType;
	private String weight;
	private String price;
	
	
	
	
	// generated constructors
		public Animals(String animalType, String weight, String price) {
		super();
		this.animalType = animalType;
		this.weight = weight ;
		this.price = price;
	}
	

	//getters and setters 
	public String getAnimalType() {
		return animalType;
	}
	
	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}
	
	public String isWeight() {
		return weight;
	}
	
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String isPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}
	
	public String getAnimalDetails() {
		return weight + " " +price;
	}
	

	
	
	
}


