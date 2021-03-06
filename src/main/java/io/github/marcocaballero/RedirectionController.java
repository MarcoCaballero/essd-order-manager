package io.github.marcocaballero;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectionController {

	@GetMapping("/")
	public String redirectHome() {
		return "redirect:/order-manager";
	}

	@GetMapping("/order-manager/order")
	public String redirectHomeFromWrong() {
		return redirectHome();
	}
}
