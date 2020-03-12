package ru.cinimex.aslisarenko.SpringInActiv.repository;

import ru.cinimex.aslisarenko.SpringInActiv.data.Taco;

public interface TacoRepository {

    Taco save(Taco deign);

    Taco findTaco(Long id);

    Iterable<Taco> findAll();
}
