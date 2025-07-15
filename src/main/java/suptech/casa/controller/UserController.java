package suptech.casa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Setter;
import suptech.casa.entities.User;
import suptech.casa.service.UserService;

@RestController
@AllArgsConstructor @Setter
public class UserController {
	final UserService userService;
	@PostMapping("users")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
}
