package org.esercizi_di_ripasso.java.spring.spring_ripasso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public String getBestOfYear(Model model) {

        model.addAttribute("nome", "Bruno");

        return "index";
    }
    
    //VERSIONE DIVERSA IN CUI AL LINK http://localhost:8080/ SI PUO METTERE COME PARAMETRO IL NOME ARBITRARIAMENTE E SE NON VIENE DATO DI DEFAULT COMPARIRA "Nessuno". PERO RICORDA CHE PER SCRIVERE TU IL NOME DOPO L'URL DEVI SCRIVERE SUBITO ?nome = ...
    // @GetMapping("/")
    // public String getBestOfYear(Model model, @RequestParam(name = "nome", defaultValue = "Nessuno") String name) {

    //     model.addAttribute("nome", name);

    //     return "index";
    // }
    

}
