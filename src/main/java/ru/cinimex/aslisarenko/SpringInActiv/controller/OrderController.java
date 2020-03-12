package ru.cinimex.aslisarenko.SpringInActiv.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import ru.cinimex.aslisarenko.SpringInActiv.data.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private static Logger logger = LoggerFactory.getLogger(Logger.class);
    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        return "orderView";
    }
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors) {
        if(errors.hasErrors()){
            return "orderView";
        }
        logger.info("Order submitted: " + order);
        return "redirect:/";
    }
}

