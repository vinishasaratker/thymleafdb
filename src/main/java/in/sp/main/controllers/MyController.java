package in.sp.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.sp.main.entities.User;
import in.sp.main.services.UserService;

@Controller
public class MyController {

	@Autowired
	private UserService userService;

	@GetMapping("/regPage")
	public String openRegPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/regForm")
	public String submitRegForm(@ModelAttribute("user") User user, Model model) {

		boolean status = userService.registerUser(user);

		if (status) {
			model.addAttribute("successing", "user registered successfully");
		} else {
			model.addAttribute("errormessage", "User not registered");
		}
		return "register";
	}

	@GetMapping("/loginPage")
	public String openLoginPage(Model model) {
		model.addAttribute("user", new User());
		return "loginPage";   // fixed: was "login"
	}

	@PostMapping("/loginForm")
	public String submitLoginForm(@ModelAttribute("user") User user, Model model) {

		boolean status = userService.loginUser(user.getEmail(), user.getPassword());

		if (status) {
			model.addAttribute("successing", "login successful");
		} else {
			model.addAttribute("errormessage", "invalid email or password");
		}
		return "loginPage";
	}
}
