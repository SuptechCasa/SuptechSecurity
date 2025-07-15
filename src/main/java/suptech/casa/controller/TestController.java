package suptech.casa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping("accueil")
	public String accueil() {
		return "Accueil";
	}
	
	@GetMapping("user")
	public String user() {
		return "Espace User";
	}
	
	@GetMapping("admin")
	public String admin() {
		return "Espace Admin";
	}
}
