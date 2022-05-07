package com.cointracker.controller;

import com.cointracker.client.BlockChairClient;
import com.cointracker.dto.CurrentBalances;
import com.cointracker.dto.GetAddressTransactionsResponse;
import com.cointracker.dto.Transaction;
import com.cointracker.repository.AddressRepository;
import com.cointracker.service.AddressService;
import com.cointracker.testing.FileUtils;
import com.cointracker.testing.TestData;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BitcoinAddressController.class)
class BitcoinAddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressService addressService;

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private BlockChairClient blockChairClient;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void test_addAddress() throws Exception {

        this.mockMvc.perform(post("/v1/bitcoin/address").with(TestData.getAddressRequest()))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    void test_deleteAddress() throws Exception {

        this.mockMvc.perform(delete("/v1/bitcoin/address/12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void test_getAddressTransactions_with_empty_response() throws Exception {

        Mockito.when(addressService.getAddressTransactions("12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju" ,10, 10)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/v1/bitcoin/address/12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju/txs")
                        .param("limit", "10")
                        .param("offset", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void test_getAddressTransactions_with_response() throws Exception {

        List<Transaction> transactions = FileUtils.readObjectFromJsonFile(new TypeReference<List<Transaction>>() {}, "transactions.json");
        String expectedResponse = FileUtils.readFileIntoJson(GetAddressTransactionsResponse.class, "get_address_transactions.json");
        Mockito.when(addressService.getAddressTransactions("12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju" ,2, 2)).thenReturn(Optional.of(transactions));

        MvcResult mvcResult = this.mockMvc.perform(get("/v1/bitcoin/address/12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju/txs")
                        .param("limit", "2")
                        .param("offset", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

    @Test
    void test_getAddressBalance_with_empty_response() throws Exception {
            Mockito.when(addressService.getAddressBalance("12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju")).thenReturn(Optional.empty());

            this.mockMvc.perform(get("/v1/bitcoin/address/12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju/balance")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    void test_getAddressBalance_with_response() throws Exception {
        CurrentBalances currentBalances = FileUtils.readObjectFromJsonFile(CurrentBalances.class, "current_balance.json");
        String expectedResponse = FileUtils.readFileIntoJson(CurrentBalances.class, "current_balance.json");

        Mockito.when(addressService.getAddressBalance("12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju")).thenReturn(Optional.of(currentBalances));

        MvcResult mvcResult = this.mockMvc.perform(get("/v1/bitcoin/address/12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju/balance")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        Assertions.assertEquals(expectedResponse, mvcResult.getResponse().getContentAsString());
    }

}