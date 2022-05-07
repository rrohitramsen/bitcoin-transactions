package com.cointracker.service;

import com.cointracker.dto.CurrentBalances;
import com.cointracker.dto.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AddressService {

    void addAddress(String address);

    void removeAddress(String address);

    Optional<List<Transaction>> getAddressTransactions(String address, int limit, int offset);

    Optional<CurrentBalances> getAddressBalance(String address);
}
