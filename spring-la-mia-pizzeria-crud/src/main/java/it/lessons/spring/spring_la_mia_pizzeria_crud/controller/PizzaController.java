package it.lessons.spring.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import it.lessons.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;

@Controller
@RequestMapping("/pizzeria")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping()
    public String index(/*Model model*/) {
        return "pizzeria/index";
    }

    @GetMapping("/elencoPizze")
    public String elenco(Model model) {
        List<Pizza> listaPizze = pizzaRepository.findAll();
        model.addAttribute("list", listaPizze);
        return "pizzeria/elencoPizze";
    }

    @GetMapping("/addPizza")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzeria/addPizza";
    }
    @PostMapping("/addPizza")
    public String newPizza(@ModelAttribute("pizza") Pizza formPizza, Model model) {
        /*
         * 
         * 
         */
        pizzaRepository.save(formPizza);
        return "redirect:/pizzeria";
    }    
}