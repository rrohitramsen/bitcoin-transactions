# bitcoin-transactions
bitcoin-transactions
## Requirements

- The app should let the user:
    - Add/Remove bitcoin addresses
    - Synchronize bitcoin wallet transactions for the addresses
    - Retrieve the current balances and transactions for each bitcoin address

# Using this repository
##Prerequisites
###Tech Stack
* IntelliJ
* Java 11

## How to start ?

```
$ mvn spring-boot:run
```

##Testing

* For testing, either use test button from maven lifecycle or run `mvn test` from terminal.

### Swagger-UI
* After starting the application Click on [Swagger-home](http://localhost:8080/swagger)
* Please use the following BTC addresses for the test 
  * `bc1qm34lsc65zpw79lxes69zkqmk6ee3ewf0j77s3h`
  * `12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju`

![Swagger-Home](/src/main/resources/images/swagger.png "Swagger UI Home")

## REST API's

### Add Address 
* This endpoint will add the address transactions and current balances in the local storage
* POST `/v1/bitcoin/address`

### DELETE Address
* This endpoint will delete the address and its corresponding transactions and current balances from the local storage
* DELETE `/v1/bitcoin/address/{address}`

### Get Address Transactions
* This endpoint will retrieve the transactions for a given address and can also take in values for limit and offset so that we can page data
* GET `/v1/bitcoin/address/{address}/txs?limit=:limit&offset=:offset`

### Get Address Balances
* This endpoint will retrieve the current balances of the given address
* POST `/v1/bitcoin/address/{address}/balance`

