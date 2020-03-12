package ru.cinimex.aslisarenko.SpringInActiv.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Data
@RequiredArgsConstructor
public class Ingredient {
    private  String id;
    private  String name;
    private  Type type;

    public Ingredient(String id, String name, Type type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

    public Type getType(){return this.type;}
    public String getId(){return this.id;}
    public String getName(){return this.name;}
}
