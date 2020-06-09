package org.example.service;

import org.example.daomain.Order;

import java.util.List;

public interface OrderService {

    public List<Order> findAll();
    public List<Order> findAllByPage(int page,int pageSize,String search);
    public Order findById(int id);
    public void delete(Integer[] ids);
    public void open(Integer[] ids);
    public void close(Integer[] ids);
}
