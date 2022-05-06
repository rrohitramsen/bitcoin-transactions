package com.cointracker.repository;

import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private static final ConcurrentHashMap<String, Double> ADDRESS_STORE = new ConcurrentHashMap<>();

    @Override
    public void saveAddress(String address) {
        ADDRESS_STORE.putIfAbsent(address, 0.0);
    }

    @Override
    public void removeAddress(String address) {
        ADDRESS_STORE.remove(address);
    }
}
