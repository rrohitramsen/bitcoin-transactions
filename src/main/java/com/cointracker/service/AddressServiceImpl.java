package com.cointracker.service;

import com.cointracker.repository.AddressRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    public static final String BLOCK_CHAIR_URL_FOR_TRANSACTIONS_FOR_ADDRESS = "https://api.blockchair.com/bitcoin/dashboards/address/%s?transaction_details=true&limit=%s&offset=%s";

    public static final String BLOCK_CHAIR_URL_FOR_ADDRESS_BALANCE = "https://api.blockchair.com/bitcoin/dashboards/address/%s?state=latest";

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void addAddress(String address) {
        log.info("Saving Address {}", address);
        addressRepository.saveAddress(address);
    }

    @Override
    public void removeAddress(String address) {
        log.info("Removing Address {}", address);
        addressRepository.removeAddress(address);
    }

    @Override
    public String getAddressTransactions(String address, int limit, int offset) {
        log.info("Getting Transactions of Address {}", address);
        String blockChairUrl = String.format(BLOCK_CHAIR_URL_FOR_TRANSACTIONS_FOR_ADDRESS, address, limit, offset);
        ResponseEntity<String> response = restTemplate.getForEntity(blockChairUrl, String.class);
        if (response.getStatusCode().isError()) {
            throw new ResponseStatusException(response.getStatusCode());
        }
        if (Objects.isNull(response.getBody())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Address");
        }
        return response.getBody();
    }


    @Override
    public String getAddressBalance(String address) {
        log.info("Getting Current Balances of Address {}", address);
        String blockChairUrl = String.format(BLOCK_CHAIR_URL_FOR_ADDRESS_BALANCE, address);
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(blockChairUrl, JsonNode.class);
        if (response.getStatusCode().isError() || Objects.isNull(response.getBody())) {
            throw new ResponseStatusException(response.getStatusCode());
        }
        if (Objects.isNull(response.getBody())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Address");
        }
        return response.getBody().get("data").get(address).get("address").toString();
    }
}
