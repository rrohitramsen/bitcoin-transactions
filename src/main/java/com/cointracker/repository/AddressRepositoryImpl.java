package com.cointracker.repository;

import com.cointracker.dto.CurrentBalances;
import com.cointracker.dto.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private static final ConcurrentHashMap<String, List<Transaction>> ADDRESS_TRANSACTION_STORE = new ConcurrentHashMap<>();

    private static final ConcurrentHashMap<String, CurrentBalances> ADDRESS_CURRENT_BALANCES_STORE = new ConcurrentHashMap<>();


    @Override
    public void saveAddressTransactions(String address, List<Transaction> transactions) {
        ADDRESS_TRANSACTION_STORE.putIfAbsent(address, transactions);
    }

    @Override
    public void saveAddressCurrentBalances(String address, CurrentBalances currentBalances) {
        ADDRESS_CURRENT_BALANCES_STORE.putIfAbsent(address, currentBalances);
    }

    @Override
    public void removeAddress(String address) {
         ADDRESS_TRANSACTION_STORE.remove(address);
        ADDRESS_CURRENT_BALANCES_STORE.remove(address);
    }

    @Override
    public Optional<List<Transaction>> getAddressTransactions(String address, int limit, int offset) {
        List<Transaction> transactions = ADDRESS_TRANSACTION_STORE.get(address);
        if (Objects.isNull(transactions) || transactions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(transactions.subList(
                Math.min(transactions.size(), offset),
                Math.min(transactions.size(), offset + limit)
        ));
    }

    @Override
    public Optional<CurrentBalances> getAddressCurrentBalances(String address) {
        CurrentBalances currentBalances = ADDRESS_CURRENT_BALANCES_STORE.get(address);
        if (Objects.isNull(currentBalances)) {
            return Optional.empty();
        }
        return Optional.of(currentBalances);
    }
}
