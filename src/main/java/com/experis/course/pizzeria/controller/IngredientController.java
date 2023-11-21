package com.experis.course.pizzeria.controller;

import com.experis.course.pizzeria.exception.IngredientNameUniqueException;
import com.experis.course.pizzeria.exception.IngredientNotFoundException;
import com.experis.course.pizzeria.model.Ingredient;
import com.experis.course.pizzeria.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredientList", ingredientService.getAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "ingredients/index";
        }
        try {
            ingredientService.save(formIngredient);
            return "redirect:/ingredients";
        } catch (IngredientNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "An ingredients with name " + e.getMessage() + " already exist!");
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Ingredient ingredientToDelete = ingredientService.getIngredient(id);
            ingredientService.deleteIngredient(ingredientToDelete);
            redirectAttributes.addFlashAttribute("message", "Ingredient deleted!");
            return "redirect:/ingredients";
        } catch (IngredientNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
