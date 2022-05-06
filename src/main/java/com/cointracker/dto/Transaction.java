package com.cointracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

    private long blocId;

    private String hash;

    private String time;

    private Double balanceChange;
}
