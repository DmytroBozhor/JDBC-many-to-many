package org.example.dao;

import org.example.entity.Order;
import org.example.mapping.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderDao implements Dao<Order> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Order> get(long id) {
        String sqlQuery = "select * from \"order\" where \"id\" = ?";
        return jdbcTemplate.query(sqlQuery, new OrderMapper(), id).stream().findAny();
    }

    @Override
    public List<Order> getAll() {
        String sqlQuery = "select * from \"order\"";
        return jdbcTemplate.query(sqlQuery, new OrderMapper());
    }

    @Override
    public void save(Order order) {
        String sqlQuery = "insert into \"order\" (\"date\", \"cost\") values(?,?)";
        jdbcTemplate.update(sqlQuery, order.getDate(), order.getCost());
    }

    @Override
    public void update(Order order, String[] params) {
        String sqlQuery = "update \"order\" set \"date\"=?, \"cost\"=? where \"id\"=?";
        jdbcTemplate.update(sqlQuery, order.getDate(), order.getCost(), order.getId());
    }

    @Override
    public void delete(Order order) {
        String sqlQuery = "delete from \"order\" where \"id\"=?";
        jdbcTemplate.update(sqlQuery, order.getId());
    }
}
