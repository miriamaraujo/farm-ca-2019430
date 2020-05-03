package ie.cct.farmca2019430;

public class Animals {

	private String animalType;
	private Float weight;
	private Float price;

	// Constructor
	public Animals() {

	}

	// Default generated constructors
	public Animals(String animalType, Float weight, Float price) {

		this.animalType = animalType;
		this.weight = weight;
		this.price = price;
	}
	
	enum anymalType{
		Cow, Chicken, Pig
	}

	// Default getters and setters
	public String getAnimalType() {
		return animalType;
	}

	public void setAnimalType(String animalType) {
		this.animalType = animalType;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}
