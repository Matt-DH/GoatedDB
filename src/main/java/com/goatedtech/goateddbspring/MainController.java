package com.goatedtech.goateddbspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Main controller for loading index page
 */
@Controller
public class MainController {
    @GetMapping("")
    public String showHomePage() {
        return "index";
    }
}

