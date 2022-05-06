package com.cointracker.service;

import com.cointracker.dto.AddressBalance;
import com.cointracker.dto.AddressDto;
import com.cointracker.dto.AddressTransactions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    void addAddress(String address);

    void removeAddress(String address);

    String getAddressTransactions(String address, int limit, int offset);

    String getAddressBalance(String address);
}
