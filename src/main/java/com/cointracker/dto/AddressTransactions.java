package com.cointracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddressTransactions {

    private List<Transaction> transactions;
}
