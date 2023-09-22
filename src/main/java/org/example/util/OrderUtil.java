package org.example.util;

import org.example.entity.Order;
import org.example.entity.Product;
import org.example.mapping.ProductMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderUtil {
    public static List<Product> findAllProductsById(Order order) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("org.example");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        String sqlQuery = "select * from order_product as op join product p on p.id = op.product_id where op.order_id = ?";
        return jdbcTemplate.query(sqlQuery, new ProductMapper(), order.getId());
    }
}
