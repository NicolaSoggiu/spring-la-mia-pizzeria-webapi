package com.experis.course.pizzeria.controller;

import com.experis.course.pizzeria.exception.OfferNotFoundException;
import com.experis.course.pizzeria.exception.PizzaNotFoundException;
import com.experis.course.pizzeria.model.Offer;
import com.experis.course.pizzeria.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/offers")
public class OfferController {
    @Autowired
    OfferService offerService;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        try {
            model.addAttribute("offer", offerService.createNewOffer(pizzaId));
            return "offers/form";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("offers") Offer formOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/form";
        }
        Offer savedOffer = offerService.saveOffer(formOffer);
        return "redirect:/pizzas/show/" + formOffer.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Offer offer = offerService.getDeal(id);
            model.addAttribute("offer", offer);
            return "offers/form";
        } catch (OfferNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer pizzaId, @Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "offers/form";
        }
        Offer savedOffer = offerService.saveOffer(formOffer);
        return "redirect:/pizzas/show/" + formOffer.getPizza().getId();
    }
}
