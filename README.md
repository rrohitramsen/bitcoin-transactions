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

### Swagger-UI
* After starting the application Click on [Swagger-home](http://localhost:8080/api/swagger-ui.html)
* Please use the following BTC addresses for the test 
  * `bc1qm34lsc65zpw79lxes69zkqmk6ee3ewf0j77s3h`
  * `12xQ9k5ousS8MqNsMBqHKtjAtCuKezm2Ju`

![Swagger-Home](/src/main/resources/images/swagger.png "Swagger UI Home")

##Testing
For testing, either use test button from maven lifecycle or run `mvn test` from terminal.
