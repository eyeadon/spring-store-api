package com.evan.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("name", "Evan's Store");
    return "index";
  }

}
