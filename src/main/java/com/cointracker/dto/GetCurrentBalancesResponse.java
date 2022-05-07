package com.cointracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetCurrentBalancesResponse {

    private int count;

    private CurrentBalances currentBalances;

    private Context context;
}
