package com.example.maestropizza;
import com.example.maestropizza.service.PizzaService;
import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PizzaServiceTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPizzas() {
        // Подготовка данных
        Pizza pizza1 = new Pizza(1,"ere", "Маргарита","r",600.0);
        Pizza pizza2 = new Pizza(1,"ere", "Пепперони", 700.0);

        when(pizzaRepository.findAll()).thenReturn(Arrays.asList(pizza1, pizza2));

        // Вызов метода
        List<Pizza> pizzas = pizzaService.getAllPizzas();

        // Проверка
        assertNotNull(pizzas);
        assertEquals(2, pizzas.size());
        verify(pizzaRepository, times(1)).findAll();
    }

    @Test
    void testGetPizzaById() {
        Pizza pizza = new Pizza(1, "Маргарита", 600.0, "Традиционная");

        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));

        Pizza result = pizzaService.getPizzaById(1L);

        assertNotNull(result);
        assertEquals("Маргарита", result.getName());
        verify(pizzaRepository, times(1)).findById(1L);
    }
}
