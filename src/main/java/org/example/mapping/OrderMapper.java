package org.example.mapping;

import org.example.entity.Order;
import org.example.entity.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.example.util.OrderUtil.findAllProductsById;


@Component
public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setDate(rs.getTimestamp("date"));
        order.setCost(rs.getInt("cost"));

        List<Product> productList = findAllProductsById(order);
        productList.forEach(order::addProduct);

        return order;
    }
}
