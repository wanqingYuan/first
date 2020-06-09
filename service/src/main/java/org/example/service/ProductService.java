package org.example.service;




import org.example.daomain.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll(int page,int pageSize);
    public void save(Product product);
    public void delete(Integer[] ids);
    public void open(Integer[] ids);
    public void close(Integer[] ids);
}
