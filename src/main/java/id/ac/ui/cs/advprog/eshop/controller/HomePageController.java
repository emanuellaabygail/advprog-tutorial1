package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Home Page!");
        return "HomePage"; // This refers to home.html in src/main/resources/templates/
    }
}