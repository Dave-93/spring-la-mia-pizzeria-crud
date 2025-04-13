package it.lessons.spring.spring_la_mia_pizzeria_crud.controller;

import java.text.NumberFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import it.lessons.spring.spring_la_mia_pizzeria_crud.model.Pizza;
import it.lessons.spring.spring_la_mia_pizzeria_crud.repository.PizzaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzeria")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Double.class,
            new CustomNumberEditor(Double.class, NumberFormat.getInstance(), true));
    }

    @ModelAttribute("pizza")
    public Pizza pizzaForm() {
        return new Pizza();  // Assicurati che venga sempre inizializzato un oggetto Pizza vuoto
    }

    @GetMapping()
    public String elenco(Model model) {
        List<Pizza> listaPizze = pizzaRepository.findAll();
        model.addAttribute("list", listaPizze);
        return "pizzeria/index";
    }

    @GetMapping("/addPizza")
    public String addPizza(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzeria/addPizza";
    }
    @PostMapping("/addPizza")
    public String newPizza(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()){
            model.addAttribute("bindingResult", bindingResult);
            return "pizzeria/addPizza";
        }
        pizzaRepository.save(formPizza);
        redirectAttributes.addFlashAttribute("successMessage", "Pizza creata");
        return "redirect:/pizzeria";
    }    
}