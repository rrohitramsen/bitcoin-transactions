package com.cointracker.client;

import com.cointracker.dto.CurrentBalances;
import com.cointracker.dto.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class BlockChairClient {

    private static final String BLOCK_CHAIR_URL_FOR_TRANSACTIONS_FOR_ADDRESS = "https://api.blockchair.com/bitcoin/dashboards/address/%s?transaction_details=true";

    private static final String BLOCK_CHAIR_URL_FOR_ADDRESS_BALANCE = "https://api.blockchair.com/bitcoin/dashboards/address/%s?state=latest";

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Autowired
    private RestTemplate restTemplate;

    public List<Transaction> getAddressTransactions(String address) {
        log.info("Getting Transactions of Address {}", address);
        String blockChairUrl = String.format(BLOCK_CHAIR_URL_FOR_TRANSACTIONS_FOR_ADDRESS, address);
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(blockChairUrl, JsonNode.class);
        if (response.getStatusCode().isError()) {
            throw new ResponseStatusException(response.getStatusCode());
        }
        if (Objects.isNull(response.getBody())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Address");
        }
        try {
            return OBJECT_MAPPER.readValue(response.getBody().get("data").get(address).get("transactions").toString(), new TypeReference<List<Transaction>>() {});
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }


    public CurrentBalances getAddressBalance(String address) {
        log.info("Getting Current Balances of Address {}", address);
        String blockChairUrl = String.format(BLOCK_CHAIR_URL_FOR_ADDRESS_BALANCE, address);
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(blockChairUrl, JsonNode.class);
        if (response.getStatusCode().isError() || Objects.isNull(response.getBody())) {
            throw new ResponseStatusException(response.getStatusCode());
        }
        if (Objects.isNull(response.getBody())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Address");
        }
        try {
            return OBJECT_MAPPER.readValue(response.getBody().get("data").get(address).get("address").toString(), CurrentBalances.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
    }
}
