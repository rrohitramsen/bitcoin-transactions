package com.cointracker.repository;

import com.cointracker.dto.CurrentBalances;
import com.cointracker.dto.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository {

    void saveAddressTransactions(String address, List<Transaction> transactions);

    void saveAddressCurrentBalances(String address, CurrentBalances currentBalances);

    void removeAddress(String address);

    Optional<List<Transaction>> getAddressTransactions(String address, int limit, int offset);

    Optional<CurrentBalances> getAddressCurrentBalances(String address);

}
