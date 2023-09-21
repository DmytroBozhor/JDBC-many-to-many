package org.example;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class Main {

    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static final Set<Order> ORDERS = new HashSet<>();
    private static final Set<Product> PRODUCTS = new HashSet<>();

    static {
        URL = Environment.getProperty("url").get();
        USERNAME = Environment.getProperty("username").get();
        PASSWORD = Environment.getProperty("password").get();
    }

    public static void main(String[] args) throws SQLException {

        String getOrdersAndProductSql = """
                select *
                from "order_product" as op
                inner join "order" as o on op."order_id" = o."id"
                inner join "product" as "p" on op."product_id" = "p"."id";""";

        PreparedStatement preparedStatement = getConnection().prepareStatement(getOrdersAndProductSql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt(3));
            order.setDate(resultSet.getTimestamp(4));
            order.setCost(resultSet.getInt(5));
            ORDERS.add(order);

            Product product = new Product();
            product.setId(resultSet.getInt(6));
            product.setName(resultSet.getString(7));
            product.setCost(resultSet.getInt(8));
            PRODUCTS.add(product);
        }

        preparedStatement.close();

        String getProductsSql = """
                select *
                from "order_product" as op
                join "product" as "p" on "p"."id" = "op"."product_id"
                where "order_id" = ?;""";

        PreparedStatement preparedStatement2 = getConnection().prepareStatement(getProductsSql);

        ORDERS.forEach(order -> {
            try {
                preparedStatement2.setInt(1, order.getId());
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                while (resultSet2.next()) {
                    Product product = new Product();
                    product.setId(resultSet2
                            .getInt(3));
                    product.setName(resultSet2.getString(4));
                    product.setCost(resultSet2.getInt(5));
                    order.addProduct(product);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        preparedStatement2.close();

        ORDERS.forEach(System.out::println);

        String getOrderSql = """
                select *
                from "order_product" as op
                join "order" as "o" on "o"."id" = "op"."order_id"
                where "product_id" = ?;""";

        PreparedStatement preparedStatement3 = getConnection().prepareStatement(getOrderSql);

        PRODUCTS.forEach(product -> {
            try {
                preparedStatement3.setInt(1, product.getId());
                ResultSet resultSet3 = preparedStatement3.executeQuery();
                while (resultSet3.next()) {
                    Order order = new Order();
                    order.setId(resultSet3.getInt(3));
                    order.setDate(resultSet3.getTimestamp(4));
                    order.setCost(resultSet3.getInt(5));
                    product.addOrder(order);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        preparedStatement3.close();

        PRODUCTS.forEach(System.out::println);

    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}