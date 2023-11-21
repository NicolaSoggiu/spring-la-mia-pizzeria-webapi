package com.experis.course.pizzeria.service;

import com.experis.course.pizzeria.exception.OfferNotFoundException;
import com.experis.course.pizzeria.exception.PizzaNotFoundException;
import com.experis.course.pizzeria.model.Offer;
import com.experis.course.pizzeria.model.Pizza;
import com.experis.course.pizzeria.repository.OfferRepository;
import com.experis.course.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OfferService {

    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private OfferRepository offerRepository;

    public Offer createNewOffer(Integer pizzaId) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new PizzaNotFoundException("Pizza with id " + pizzaId + " not found"));
        Offer offer = new Offer();
        offer.setStartDate(LocalDate.now());
        offer.setEndDate(LocalDate.now().plusMonths(1));
        offer.setPizza(pizza);
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer getDeal(Integer id) throws OfferNotFoundException {
        return offerRepository.findById(id)
                .orElseThrow(() -> new OfferNotFoundException("Deal with id " + id + " not found"));
    }
}
