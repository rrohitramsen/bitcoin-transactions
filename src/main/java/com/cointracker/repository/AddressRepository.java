package com.cointracker.repository;

import com.cointracker.dto.AddressDto;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository {

    void saveAddress(String address);

    void removeAddress(String address);
}
