package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository{
    
    private JdbcTemplate jdbc;

    private static final String 
    findAllSql = "select id, name, type from Ingredient",
    findByIdSql = findAllSql + " where id=?",
    saveSql = "insert into Ingredient (id, name, type) values (?, ?, ?)";

    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return jdbc.query(findAllSql, this::mapRowToIngredient);
    }

    @Override
    public Ingredient findById(String id) {
        return jdbc.queryForObject(findByIdSql, this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbc.update(
            saveSql, 
            ingredient.getId(), 
            ingredient.getName(),
            ingredient.getType().toString()
        );
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException{
        return new Ingredient(
            rs.getString("id"), 
            rs.getString("name"), 
            Ingredient.Type.valueOf(rs.getString("type"))
        );
    }
    
}
