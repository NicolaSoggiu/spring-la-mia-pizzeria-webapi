package com.experis.course.pizzeria.service;

import com.experis.course.pizzeria.exception.IngredientNameUniqueException;
import com.experis.course.pizzeria.exception.IngredientNotFoundException;
import com.experis.course.pizzeria.model.Ingredient;
import com.experis.course.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAll() {
        return ingredientRepository.findByOrderByName();
    }

    public Ingredient save(Ingredient ingredient) throws IngredientNameUniqueException {
        if (ingredientRepository.existsByName(ingredient.getName())) {
            throw new IngredientNameUniqueException(ingredient.getName());
        }
        ingredient.setName(ingredient.getName());
        return ingredientRepository.save(ingredient);
    }


    public Ingredient getIngredient(Integer id) throws IngredientNotFoundException {
        return ingredientRepository.findById(id).orElseThrow(() ->
                new IngredientNotFoundException("Ingredient with id " + id + " not found!"));
    }

    public void deleteIngredient(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}
