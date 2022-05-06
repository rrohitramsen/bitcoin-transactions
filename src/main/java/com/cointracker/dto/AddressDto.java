package com.cointracker.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddressDto {

    @ApiModelProperty(value = "BTC address", required = true, example = "12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju")
    private String address;

}
