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

//		animal.add(new Animals("Cow", 300f, 10f));
//		animal.add(new Animals("Chicken", 0.5f, 10f));
//		animal.add(new Animals("Pig", 100f, 10f));
//		animal.add(new Animals("Cow", 100f, 250f));
//		animal.add(new Animals("Chicken", 0.2f, 2f));
//		animal.add(new Animals("Pig", 50f, 125f));

	}

	/*
	 * 
	 * 
	 * CA's Endpoints:
	 * 
	 * 
	 */

	/*
	 * Add a new animal. http://localhost:8080/add-animal Here in this one you can
	 * Add the three Animal Types but if you try to add some other animal you get a
	 * Status 400 for bad request. Once you add it you get the Success Response
	 */
	@PostMapping("add-animal")
	public SuccessResponse addAnimals(@RequestBody Animals animals) {
		if (!animals.getAnimalType().equals("Cow") && !animals.getAnimalType().equals("Pig")
				&& !animals.getAnimalType().equals("Chicken")) {
			throw new InvalidRequest("You must stick to the following animals: Cow, Chicken and Pig.");
		}

		animal.add(animals);
		return new SuccessResponse(
				animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice());
	}

//	Calculate the average weight of each type of animal. 
//	Here I am using the same function for each animal where you just need to change the attribute of the animal type. 
//	I added the Not Found Exception to handle an empty array that returns Not a Number when it tries to calculate average.
//	http://localhost:8080/average-type
	@GetMapping("average-type")
	public Float averageType() {

		List<Animals> averageType = new ArrayList<Animals>();

		Float weight = 0.0f;
		for (Animals animals : animal) {
//			if (animals.getAnimalType().equals("Cow")) {
//				averageType.add(animals);
//				weight += animals.getWeight();
//			}

			if (animals.getAnimalType().equals("Chicken")) {
				averageType.add(animals);
				weight += animals.getWeight();
			}

//			if (animals.getAnimalType().equals("Pig")) {
//				averageType.add(animals);
//				weight += animals.getWeight();
//			}

		}
		weight = weight / averageType.size();
		if (averageType.size() == 0) {
			throw new NotFoundException("Sorry, try adding some animals in.");
		}

		return weight;

	}

//	How many animals of each type can be sold
//	http://localhost:8080/weight-control
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
			throw new NotFoundException("No animals above necessary weight");
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
		if (canBeSold.size() == 0) {
			throw new NotFoundException("No animals above necessary weight");
		}

		return price;

	}

//	What is the current value of the farm assuming the price of each animal is set by a
//	parameter in the HTTP request. 
//	This multiple parameter returns the sum of the given values like in this URL below.
//	http://localhost:8080/current-value?cow=350&pig=120&chicken=1
	@GetMapping("current-value")
	public String currentValue(@RequestParam(required = true) Float cow, @RequestParam(required = true) Float chicken,
			@RequestParam(required = true) Float pig) {
		return ("Total : " + (cow + chicken + pig));

	}

	/*
	 * 
	 * 
	 * Other Endpoints
	 * 
	 * 
	 */

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

//	http://localhost:8080/remove-all
	@PostMapping("remove-all")
	public boolean removeAll() {
		return animal.removeAll(getAllAnimals());

	}

//	Returns all the Animals in JSON
//	http://localhost:8080/all-animals
	@GetMapping("all-animals")
	public List<Animals> getAllAnimals() {
		if (animal.size() == 0) {
			throw new NotFoundException("Sorry no animals in this Array yet.");
		}
		return animal;

	}

//	Average of All the Animals
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
			throw new NotFoundException("Oopsie! No animals. Try some veggies :)");
		}
		Float price = 0.0f;
		for (Animals animals : animal) {
			price += animals.getPrice();
		}

		price = price / animal.size();
		return price;

	}

//	The total weight of all the animals.
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

//	Total worth of the Farm regardless of the weight of the animals.
//	http://localhost:8080/total-worth
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

}
