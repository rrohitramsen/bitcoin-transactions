package com.cointracker.service;

import com.cointracker.client.BlockChairClient;
import com.cointracker.dto.CurrentBalances;
import com.cointracker.dto.Transaction;
import com.cointracker.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BlockChairClient blockChairClient;

    @Override
    public void addAddress(String address) {
        log.info("Saving Address {}", address);
        CurrentBalances addressBalance = blockChairClient.getAddressBalance(address);
        List<Transaction> addressTransactions = blockChairClient.getAddressTransactions(address);
        addressRepository.saveAddressCurrentBalances(address, addressBalance);
        addressRepository.saveAddressTransactions(address, addressTransactions);
    }

    @Override
    public void removeAddress(String address) {
        log.info("Removing Address {}", address);
        addressRepository.removeAddress(address);
    }

    @Override
    public Optional<List<Transaction>> getAddressTransactions(String address, int limit, int offset) {
        log.info("Getting Transactions of Address {}", address);
        return addressRepository.getAddressTransactions(address, limit, offset);
    }


    @Override
    public Optional<CurrentBalances> getAddressBalance(String address) {
        log.info("Getting Current Balances of Address {}", address);
        return addressRepository.getAddressCurrentBalances(address);
    }
}
