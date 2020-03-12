package ru.cinimex.aslisarenko.SpringInActiv.repository;

import ru.cinimex.aslisarenko.SpringInActiv.data.Ingredient;

public interface IngradientRepository {
    Iterable<Ingredient> findAll();

    Ingredient findOne(String id);

    Ingredient save(Ingredient ingredient);
}
