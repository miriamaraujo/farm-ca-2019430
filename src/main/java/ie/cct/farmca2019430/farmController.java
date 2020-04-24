package ie.cct.farmca2019430;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class farmController {
	
	@GetMapping("farm")
	public String text() {
		return "Welcome to Miriam's Farm";
	}
	
	
	@GetMapping("animals")
	public Animals getAnimals() {
		return new Animals("Cow", "300KG", "€500");
	}

}
