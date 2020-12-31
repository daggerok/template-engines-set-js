package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class IndexPage {

  @PostMapping("/submit-form")
  String handleSubmitForm(@RequestParam String username, Model model, RedirectAttributes redirectAttributes) { // <= get username from Model
    model.addAttribute("username", username);
    redirectAttributes.addAllAttributes(model.asMap());
    return "redirect:/";
  }

  @GetMapping("/")
  String renderIndexPage(@ModelAttribute("username") String username) { // <= get username from Model
    return "index";
  }
}

@SpringBootApplication
public class MustacheApplication {

  public static void main(String[] args) {
    SpringApplication.run(MustacheApplication.class, args);
  }
}
