package org.example.service;


import com.github.pagehelper.PageHelper;
import org.example.dao.ProductDao;
import org.example.daomain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll(int page,int pageSize) {
        PageHelper.startPage(page,pageSize);
        return productDao.findAll();
    }

    @Override
    public void delete(Integer[] ids) {
        for (int id:ids){
            productDao.delete(id);
        }
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void open(Integer[] ids) {
        for (int id:ids){
            productDao.open(id);
        }
    }

    @Override
    public void close(Integer[] ids) {
        for (int id:ids){
            productDao.close(id);
        }
    }
}
