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
//	GET http://localhost:8080/greeting
//	Simple greeting
//	HTTP Status: 200 OK
	@GetMapping("index")
	public String welcome() {
		return "Welcome to Miri's Farm! To shop any of our products SIGN-IN with your NAME at http://localhost:8080/add-user (POST) on Postman ";
	}

	private ArrayList<Animals> animal;
	private ArrayList<User> users;

	public farmController() {
		animal = new ArrayList<Animals>();
		users = new ArrayList<User>();
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
		return animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice();
	}

//	Adding User
//	Same process as in animals but it helps me to practice what I have done.
//	POST http://localhost:8080/add-user
//	It returns "status": 400 on postman. (Error fixed adding an empty constructor to the User class)
//	Added the following code into the body on postman:
//	{
//	"name": "Jose"
//	}
	@PostMapping("add-user")
	public String addUser(@RequestBody User user) {
		users.add(user);
		return user.getName();
	}

//	http://localhost:8080/add-animal-success
//	Displays the message on the body in JSON with success.
//	POST http://localhost:8080/add-animal
//	HTTP Status: 200 OK
//	--
//	@PostMapping("add-animal-success")
//	public SuccessResponse addAnimals(@RequestBody Animals animals) {
//		animal.add(animals);
//		return new SuccessResponse(
//				animals.getAnimalType() + " added! Weight: " + animals.getWeight() + " Price " + animals.getPrice());
//	}

//	The result is "NaN" on the browser
//	But once I add animals into the server through Postman it returns the average.
	@GetMapping("average-price")
	public Float averagePrice() {
		if (animal.size() == 0) {
			throw new NotFoundException("No animals to sell right now. Try some veggies");
		}
		Float price = 0.0f;
		for (Animals animals : animal) {
			price += animals.getPrice();
		}

		price = price / animal.size();
		return price;

	}

//	same process from average price
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

//	http://localhost:8080/basket
	@PostMapping("basket")
	public String basket(User user, Animals animals) {
		return user.getName() + " " + animals.getAnimalType();
	}

//	Multiple parameters:
//	http://localhost:8080/confirmation?name=Miriam&animalType=Cow
	@GetMapping("confirmation")
	public String confirmation(@RequestParam(required = true) String name,
			@RequestParam(required = true) String animalType) {
		return name + " you have bought a " + animalType + ". Thanks for choosing Miri's Farm!";

	}

//	No Content Declaration
//	http://localhost:8080/no-content
//	returns 404 on the browser and Postman. 
	@GetMapping("no-content")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void emptyContent() {

	}

//	http://localhost:8080/scale
//	@GetMapping("scale")
//	public String scale() {
//		String weight = "";
//		for (Animals animals : animal) {
//		
//		}
//		return weight;
//	}

//	Get the total weight of all the animals.
//	@GetMapping("weight-total")
//	public Float weightTotal() {
//		Float weight = 0f;
//		for(Float i=0f; i<animal.size(); i++){
//	        weight += i;
//	      }
//		return weight;
//	It does not return total value.
//	}

//	Here I found the right way to get the total weight which I was trying to retrieve in the previous function.
//	http://localhost:8080/weight-total
	@GetMapping("weight-total")
	public Float weightTotal() {
		Float weight = 0f;
		for (Animals animals : animal) {
			weight += animals.getWeight();
		}

		return weight;
	}

//	http://localhost:8080/price-total
	@GetMapping("price-total")
	public Float priceTotal() {
		Float price = 0f;
		for (Animals animals : animal) {
			price += animals.getPrice();
		}

		return price;
	}

//	How many animals of each type can be sold (weight requirements above) right now

	@GetMapping("cow")
	public String heyCow() {
		

		for (Animals animals : animal) {
			if (animals.getAnimalType().equals("Cow") && animals.getWeight() > 3090f) {
				
				return animals.getAnimalType() + " Can be sold";
			}
		}

		return "Animal bellow the necessary weight" ;
	}

}