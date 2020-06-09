package org.example.dao;



import org.example.daomain.Product;

import java.util.List;

public interface ProductDao {
    //查询所有
    public List<Product> findAll();
    //保存
    public void save(Product product);

    public Product findById(int id);

    public void delete(int id);

    public void open(int id);
    public void close(int id);
}
