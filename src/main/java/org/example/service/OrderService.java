package org.example.service;

import org.example.dao.OrderDao;
import org.example.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public Optional<Order> get(long id) {
        return orderDao.get(id);
    }

    public List<Order> getAll() {
        return orderDao.getAll();
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    public void update(Order order, String[] params) {
        orderDao.update(order, params);
    }

    public void delete(Order order) {
        orderDao.delete(order);
    }
}
