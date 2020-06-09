package org.example.dao;

import org.example.daomain.Order;

import java.util.List;

public interface OrderDao {

    public List<Order> findAll();

    public Order findById(int id);

    public void delete(int id);
    public void open(int id);
    public void close(int id);
    public List<Order> findByOrderId(String orderId);
}
