package com.cointracker;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "bitcoin-transactions",
                description = "This micro-service will expose rest api's for bitcoin transactions",
                contact = @Contact(
                        name = "Rohit Kumar",
                        email = "rrohit.ramsen@gmail.com",
                        url = "https://https://github.com/rrohitramsen/bitcoin-transactions")
        ))
public class OpenAPIConfiguration {
}
