package org.example.dao;

import org.example.entity.Product;
import org.example.mapping.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDao implements Dao<Product> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> get(long id) {
        String sqlQuery = "select * from \"product\" where \"id\" = ?";
        return jdbcTemplate.query(sqlQuery, new ProductMapper(), id).stream().findAny();
    }

    @Override
    public List<Product> getAll() {
        String sqlQuery = "select * from \"product\"";
        return jdbcTemplate.query(sqlQuery, new ProductMapper());
    }

    @Override
    public void save(Product product) {
        String sqlQuery = "insert into \"product\" (\"name\", \"cost\") values(?,?)";
        jdbcTemplate.update(sqlQuery, product.getName(), product.getCost());
    }

    @Override
    public void update(Product product, String[] params) {
        String sqlQuery = "update \"product\" set \"name\"=?, \"cost\"=? where \"id\"=?";
        jdbcTemplate.update(sqlQuery, product.getName(), product.getCost(), product.getId());
    }

    @Override
    public void delete(Product product) {
        String sqlQuery = "delete from \"product\" where \"id\"=?";
        jdbcTemplate.update(sqlQuery, product.getId());
    }
}
