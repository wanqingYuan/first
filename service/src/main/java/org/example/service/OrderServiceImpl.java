package org.example.service;

import com.github.pagehelper.PageHelper;
import org.example.dao.OrderDao;
import org.example.daomain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public List<Order> findAllByPage(int page, int pageSize,String search) {
        if (search.length()>0){
            search="%"+search+"%";
            PageHelper.startPage(page,pageSize);
            return orderDao.findByOrderId(search);
        }else {
            PageHelper.startPage(page,pageSize);
            return orderDao.findAll();
        }
    }

    @Override
    public Order findById(int id) {
        return orderDao.findById(id);
    }

    @Override
    public void delete(Integer[] ids) {
        for (Integer id :ids) {
            orderDao.delete(id);
        }
    }

    @Override
    public void open(Integer[] ids) {
        for (Integer id :ids) {
            orderDao.open(id);
        }
    }

    @Override
    public void close(Integer[] ids) {
        for (Integer id :ids) {
            orderDao.close(id);
        }
    }
}
