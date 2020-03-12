package ru.cinimex.aslisarenko.SpringInActiv.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.cinimex.aslisarenko.SpringInActiv.data.Ingredient;
import ru.cinimex.aslisarenko.SpringInActiv.data.Taco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

@Repository
public class jdbcTacoRepository implements TacoRepository{

    private JdbcTemplate jdbc;

    @Autowired
    public jdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(Taco deign) {
        long tacoId = saveTacoInfo(deign);
        deign.setId(tacoId);
        for (Ingredient ingredient : deign.getIngredients()){
            saveIngradientToTaco(ingredient, tacoId);
        }
        return deign;
    }

    @Override
    public Taco findTaco(Long id) {
        return jdbc.queryForObject(
                "select id, name, createdAt from Taco where id=?",
                this::mapRowToTaco, id);
    }

    @Override
    public Iterable<Taco> findAll() {
        return jdbc.query("select id, name, createdAt from Taco",
                this::mapRowToTaco);

    }

    private long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory("insert into Taco (name, createAt) values  (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(Arrays.asList(taco.getName(),new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngradientToTaco(Ingredient ingredient, long tacoId){
        this.jdbc.update("insert into Taco_Ingredients (taco, ingredient) value(?, ?)", tacoId, ingredient.getId());
    }

    private Taco mapRowToTaco(ResultSet rs, int rowNum) throws SQLException {
        return new Taco(
                rs.getLong("id"),
                rs.getDate("createdAt"),
                rs.getString("name"));
    }
}
