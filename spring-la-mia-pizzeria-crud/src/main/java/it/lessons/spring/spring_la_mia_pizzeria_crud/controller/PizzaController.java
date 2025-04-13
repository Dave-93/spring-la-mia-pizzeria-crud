package it.lessons.spring.spring_la_mia_pizzeria_crud.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    /*Aggiungi */
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
    /* */

    /*Dettaglio*/
    @GetMapping("/dettaglio/{id}")
    public String dettaglio(@PathVariable("id") Integer id, Model model) {
        Optional<Pizza> optPizza = pizzaRepository.findById(id);
        if (optPizza.isPresent()) {
            model.addAttribute("pizza", pizzaRepository.findById(id).get());
            return "/pizzeria/dettaglio";
        }
        return "redirect:/pizzeria";
    }
    /* */

    /*Modifica*/
    @GetMapping("/modifica/{id}")
    public String modifica(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "/pizzeria/modifica";
    }
    @PostMapping("/modifica/{id}")
    public String aggiornamento(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/pizzeria/modifica";
        }
        pizzaRepository.save(formPizza);
        return "redirect:/pizzeria";
    }
    /* */

}