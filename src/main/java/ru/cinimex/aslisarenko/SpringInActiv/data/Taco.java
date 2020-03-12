package ru.cinimex.aslisarenko.SpringInActiv.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Taco {

    @NotNull
    private long id;

    @NotNull
    private Date createdAt;

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    public Taco(Long id, Date dateCreate, String name){
        this.id = id;
        this.name = name;
        this.createdAt = dateCreate;
    }

}
