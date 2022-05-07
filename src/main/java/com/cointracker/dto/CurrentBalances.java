package com.cointracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
public class CurrentBalances {

    @JsonProperty("balance")
    private BigInteger balance;

    @JsonProperty("balance_usd")
    private BigDecimal balanceUsd;

    @JsonProperty("received")
    private BigInteger received;

    @JsonProperty("received_usd")
    private BigDecimal receivedUsd;

    @JsonProperty("spent")
    private BigInteger spent;

    @JsonProperty("spent_usd")
    private BigDecimal spentUsd;

}
