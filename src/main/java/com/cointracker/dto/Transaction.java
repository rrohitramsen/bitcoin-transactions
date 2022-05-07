package com.cointracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
public class Transaction {

    @JsonProperty("block_id")
    private long blocId;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("time")
    private String time;

    @JsonProperty("balance_change")
    private BigInteger balanceChange;
}
