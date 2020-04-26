package ie.cct.farmca2019430;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class farmController {
//	GET http://localhost:8080/greeting
//	Simple greeting
//	HTTP Status: 200 OK
	@GetMapping("greeting")
	public String welcome() {
		return "Welcome to Miriam's Farm!";
	}

	private ArrayList<Animals> animal;

	public farmController() {
		animal = new ArrayList<Animals>();
	}

//	POST http://localhost:8080/add-animal
//	Shows added item in the body with the returning statements.
//	Added header Content-Type : Application JSON
//	Added animal in JSON syntax on postman >> BODY >> RAW.
//	HTTP Status: 200 OK
//	--
	@PostMapping("add-animal")
	public String addAnimals(@RequestBody Animals animals) {
		animal.add(animals);
		return animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice() ;
	}

//	http://localhost:8080/add-animal-success
//	Displays the message on the body in JSON with success.
//	HTTP Status: 200 OK
//	--
//	@PostMapping("add-animal-success")
//	public SuccessResponse addAnimals(@RequestBody Animals animals) {
//		animal.add(animals);
//		return new SuccessResponse(
//				animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice());
//	}

//	The result is "NaN" on the browser
//	@GetMapping("average-price")
//	public Float averagePrice() {
//		Float price = 0.0f;
//		for (Animals animals : animal) {
//			price += animals.getPrice();
//		}
//
//		price = price / animal.size();
//		return price;
//
//	}
	
	
//	The result is "NaN" on the browser
	@GetMapping("average-price")
	public Float averagePrice() {
		if (animal.size() == 0) {
			throw new RuntimeException("No animals to sell right now. Try some veggies");
		}
		Float price = 0.0f;
		for (Animals animals : animal) {
			price += animals.getPrice();
		}

		price = price / animal.size();
		return price;

	}
	
	

}
