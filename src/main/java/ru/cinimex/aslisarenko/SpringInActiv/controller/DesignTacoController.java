package ru.cinimex.aslisarenko.SpringInActiv.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import ru.cinimex.aslisarenko.SpringInActiv.data.Taco;
import ru.cinimex.aslisarenko.SpringInActiv.data.Design;
import ru.cinimex.aslisarenko.SpringInActiv.data.Ingredient;
import ru.cinimex.aslisarenko.SpringInActiv.data.Ingredient.Type;
import ru.cinimex.aslisarenko.SpringInActiv.repository.IngradientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    private static Logger logger = LoggerFactory.getLogger(Logger.class);
    private final IngradientRepository ingradientRepo;

    @Autowired
    public DesignTacoController(IngradientRepository ingradientRepository) {
        this.ingradientRepo = ingradientRepository;
    }


    @GetMapping("/list")
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingradientRepo.findAll().forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "tacoView";
    }

    private Ingredient filterByType(List<Ingredient> ingredients, Type type) {
        Iterator<Ingredient> iterator = ingredients.iterator();
        while(iterator.hasNext()){
            if(iterator.next().getType() == type) return iterator.next();
        }
        return null;
    }

    @PostMapping("/list")
    public String processDesign(@Valid Design design, Errors errors) {
        if(errors.hasErrors()){
            return "tacoView";
        }
        // Save the taco design...
        // We'll do this in chapter 3
        logger.info("Processing design: " + design);
        return "redirect:/orders/current";
    }


}

