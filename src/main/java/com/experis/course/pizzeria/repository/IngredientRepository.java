package com.experis.course.pizzeria.repository;

import com.experis.course.pizzeria.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByOrderByName();

    boolean existsByName(String name);
}
