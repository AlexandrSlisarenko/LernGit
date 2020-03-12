package ru.cinimex.aslisarenko.SpringInActiv.repository;

import ru.cinimex.aslisarenko.SpringInActiv.data.Order;

import java.util.Iterator;

public interface OrderRepository {

    Order save(Order sOrder);

    Iterator<Order> getOrdersByNameUser(String name);

    Iterator<Order> findAll();
}
