package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;

import java.util.Optional;
import java.util.UUID;

class Form {
	private String username;
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
}

@Controller
class IndexPage {

	@PostMapping("/submit-form")
	Rendering handlePostSubmitForm(@ModelAttribute("formData") Form form) { // => username goes to Model
		return Rendering.redirectTo(String.format("/?username=%s", form.getUsername()))
										.build();
	}

	@GetMapping("/")
	Rendering renderIndexPage(String username) { // <= get username from Model
		return Rendering.view("index")
										.modelAttribute("username", Optional.ofNullable(username).orElse("no username found."))
										.modelAttribute("formData", new Form())
										.build();
	}

	@GetMapping // just fallback, ignore...
	Rendering fallbackHandlerRedirectToIndexPageWithIdParam() {
		return Rendering.redirectTo("/?id=" + UUID.randomUUID())
										.build();
	}
}

@SpringBootApplication
public class ThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThymeleafApplication.class, args);
	}
}
