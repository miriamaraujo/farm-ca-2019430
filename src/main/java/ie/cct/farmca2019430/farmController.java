package ie.cct.farmca2019430;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class farmController {

	private ArrayList<Animals> animal;

	public farmController() {
		animal = new ArrayList<Animals>();
		
		animal.add(new Animals("Cow", 300f, 10f));
		animal.add(new Animals("Chicken", 0.5f, 10f));
		animal.add(new Animals("Pig", 100f, 10f));
		animal.add(new Animals("Cow", 100f, 250f));
		animal.add(new Animals("Chicken", 0.2f, 2f));
		animal.add(new Animals("Pig", 50f, 125f));
		

	}
	
	

//	GET http://localhost:8080/greeting
//	Simple greeting
	@GetMapping("index")
	public String welcome() {
		return "Welcome to Miri's Farm!";
	}

//	No Content Declaration
//	http://localhost:8080/no-content
//	returns 404 on the browser and Postman. 
	@GetMapping("no-content")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void emptyContent() {

	}

	/*
	 * POST http://localhost:8080/add-animal Shows added item in the body with the
	 * returning statements. Added header Content-Type : Application JSON Added
	 * animal in JSON syntax on postman >> BODY >> RAW. HTTP Status: 200 OK
	 */
	@PostMapping("add-animal")
	public String addAnimals(@RequestBody Animals animals) {
		animal.add(animals);
		return animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice();
	}

	/*
	 * http://localhost:8080/add-animal-success Displays the message on the body in
	 * JSON with success. POST http://localhost:8080/add-animal-success HTTP Status:
	 * 200 OK
	 */
	@PostMapping("add-animal-success")
	public SuccessResponse addAnimalsMessage(@RequestBody Animals animals) {
		animal.add(animals);
		return new SuccessResponse(
				animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice());
	}

//	returning all the Animals in JSON
//	http://localhost:8080/all-animals
	@GetMapping("all-animals")
	public List<Animals> getAllAnimals() {
		return animal;
				
	}

//	http://localhost:8080/average-weight
	@GetMapping("average-weight")
	public Float averageWeight() {
		if (animal.size() == 0) {
			throw new NotFoundException("Sorry, to get the average try adding some animals in.");
		}
		Float weight = 0.0f;
		for (Animals animals : animal) {
			weight += animals.getWeight();
		}

		weight = weight / animal.size();
		return weight;

	}

//	http://localhost:8080/average-price
	@GetMapping("average-price")
	public Float averagePrice() {
		if (animal.size() == 0) {
			throw new NotFoundException("Oopsie! No animals to sell right now. Try some veggies :)");
		}
		Float price = 0.0f;
		for (Animals animals : animal) {
			price += animals.getPrice();
		}

		price = price / animal.size();
		return price;

	}

//	Here I found the right way to get the total weight which I was trying to retrieve in the previous function.
//	http://localhost:8080/total-weight
	@GetMapping("total-weight")
	public Float weightTotal() {
		if (animal.size() == 0) {
			throw new NotFoundException("Sorry, try adding some animals in.");
		}
		Float weight = 0f;
		for (Animals animals : animal) {
			weight += animals.getWeight();
		}

		return weight;
	}

//	http://localhost:8080/total-worth
//	Total worth of the Farm regardless of weight.
	@GetMapping("total-worth")
	public Float priceTotal() {
		if (animal.size() == 0) {
			throw new NotFoundException("Sorry, try adding some animals in.");
		}
		Float price = 0f;
		for (Animals animals : animal) {
			price += animals.getPrice();
		}

		return price;
	}

//	http://localhost:8080/weight-control
//	How many animals of each type can be sold (weight requirements above) right now
//	It return the JSON list of animals that can be sold according to their weight
//	I used the code from the class and made a few changes
	@GetMapping("weight-control")
	public List<Animals> animalsAboveWeight() {

		List<Animals> aboveWeight = new ArrayList<Animals>();

		for (Animals animals : animal) {
			if (animals.getAnimalType().equals("Cow") && animals.getWeight().compareTo(299.0f) > 0) {
				aboveWeight.add(animals);
			}
			if (animals.getAnimalType().equals("Chicken") && animals.getWeight().compareTo(0.4f) > 0) {
				aboveWeight.add(animals);
			}

			if (animals.getAnimalType().equals("Pig") && animals.getWeight().compareTo(99f) > 0) {
				aboveWeight.add(animals);
			}

		}

		if (aboveWeight.size() == 0) {
			throw new NotFoundException("No items found");
		}
		
		

		return aboveWeight;
	}

//	What is the current value of the full farm stock: That is, the price of all the animals
//	that can be sold right now.
//	http://localhost:8080/selling-total
	@GetMapping("selling-total")
	public Float canBeSoldTotal() {
		List<Animals> canBeSold = new ArrayList<Animals>();
		Float price = 0f;
		for (Animals animals : animal) {
			if (animals.getAnimalType().equals("Cow") && animals.getWeight().compareTo(299.0f) > 0) {
				canBeSold.add(animals);
				price += animals.getPrice();
			}
			if (animals.getAnimalType().equals("Chicken") && animals.getWeight().compareTo(0.4f) > 0) {
				canBeSold.add(animals);
				price += animals.getPrice();
			}

			if (animals.getAnimalType().equals("Pig") && animals.getWeight().compareTo(99f) > 0) {
				canBeSold.add(animals);
				price += animals.getPrice();
			}

		}

		return price;

	}

//	Multiple parameters[ I need to get the sum o
//	http://localhost:8080/confirmation?name=Miriam&animalType=Cow
	@GetMapping("confirmation")
	public String confirmation(@RequestParam(required = true) String name,
			@RequestParam(required = true) String animalType) {
		return name + " you have bought a " + animalType + ". Thanks for choosing Miri's Farm!";

	}

}
