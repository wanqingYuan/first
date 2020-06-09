package org.example.dao;

import org.example.daomain.Traveller;

import java.util.List;

public interface TravellerDao {

    public List<Traveller> findByOrderId(int orderId);
}
