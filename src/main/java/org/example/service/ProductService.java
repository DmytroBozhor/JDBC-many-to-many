package org.example.service;

import org.example.dao.ProductDao;
import org.example.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Optional<Product> get(long id) {
        return productDao.get(id);
    }

    public List<Product> getAll() {
        return productDao.getAll();
    }

    public void save(Product product) {
        productDao.save(product);
    }

    public void update(Product product, String[] params) {
        productDao.update(product, params);
    }

    public void delete(Product product) {
        productDao.delete(product);
    }
}
