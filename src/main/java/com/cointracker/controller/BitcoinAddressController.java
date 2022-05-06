package com.cointracker.controller;

import com.cointracker.dto.AddressDto;
import com.cointracker.service.AddressService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;

@RestController
@RequestMapping("/v1/bitcoin/address")
@Tag(name = "API for Address")
@Slf4j
public class BitcoinAddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "Add Address", response = ResponseEntity.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto) {
        log.info("Address Added");
        addressService.addAddress(addressDto.getAddress());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete entity with the given id.", response = ResponseEntity.class)
    @RequestMapping(value = "{address}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<?> deleteAddress(@PathVariable(value = "address") String address) {
        log.info("Address Deleted");
        addressService.removeAddress(address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "This endpoint will retrieve the transactions for a given address and can also take in values for limit and offset so that we can page data", response = ResponseEntity.class)
    @RequestMapping(value = "{address}/txs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<String> addressTransactions(
            @PathVariable(value = "address") String address,
            @RequestParam(value = "limit", required = false, defaultValue = "100")
            @Parameter(description = "limits the number of returned latest transactions (in the transactions array) for an address. Default is 100. Maximum is 10000")
            @Max(10000)
            @Valid int limit,
            @RequestParam(value = "offset", required = false, defaultValue = "0")
            @Parameter(description = "allows to paginate transaction hashes. The behaviour is similar to the ?limit= section. Default is 0, and the maximum is 1000000")
            @Max(1000000)
            @Valid int offset) {
        return new ResponseEntity<>(addressService.getAddressTransactions(address, limit, offset), HttpStatus.OK);
    }

    @ApiOperation(value = "This endpoint will retrieve the current balance of the given address in BTC", response = ResponseEntity.class)
    @RequestMapping(value = "{address}/balance", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public ResponseEntity<?> addressBalance(
            @PathVariable(value = "address") String address) {
        return new ResponseEntity<>(addressService.getAddressBalance(address), HttpStatus.OK);
    }
}
