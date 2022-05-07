package com.cointracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAddressTransactionsResponse {

    private int count;

    private List<Transaction> transactions;

    private Context context;

    public static GetAddressTransactionsResponse from(List<Transaction> transactions, int limit, int offset, String address) {
        return GetAddressTransactionsResponse.builder()
                .count(transactions.size())
                .transactions(transactions)
                .context(Context.from(limit, offset, address, EndPoints.GET_ADDRESS_TRANSACTIONS))
                .build();
    }
}
