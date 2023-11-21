package com.experis.course.pizzeria.api;

import com.experis.course.pizzeria.exception.NameUniqueException;
import com.experis.course.pizzeria.exception.PizzaNotFoundException;
import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.service.PizzaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/pizzas")
@CrossOrigin
public class PizzaRestController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index(@RequestParam Optional<String> search) {
        return pizzaService.getPizzaList(search);
    }

    @GetMapping("/{id}")
    public Pizza details(@PathVariable Integer id) {
        try {
            return pizzaService.getPizzaById(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Pizza create(@Valid @RequestBody Pizza pizza) {
        try {
            return pizzaService.createPizza(pizza);
        } catch (NameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Pizza update(@PathVariable Integer id, @Valid @RequestBody Pizza pizza) {
        pizza.setId(id);
        try {
            return pizzaService.editPizza(pizza);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        try {
            Pizza pizzaToDelete = pizzaService.getPizzaById(id);
            pizzaService.deletePizza(id);
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/page")
    public Page<Pizza> pagedIndex(
            @RequestParam(name = "size", defaultValue = "3") Integer size,
            @RequestParam(name = "page", defaultValue = "0") Integer page) {
        return pizzaService.getPage(PageRequest.of(page, size));
    }

    @GetMapping("/page/v2")
    public Page<Pizza> pageIndexV2(@PageableDefault(page = 0, size = 2) Pageable pageable) {
        return pizzaService.getPage(pageable);
    }
}
