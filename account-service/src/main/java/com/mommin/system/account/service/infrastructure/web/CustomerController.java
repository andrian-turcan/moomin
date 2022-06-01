package com.mommin.system.account.service.infrastructure.web;

import com.mommin.system.account.service.service.CustomerService;
import com.mommin.system.account.service.service.dto.CreateCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.of;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
final class CustomerController {

    private final CustomerService customerService;

    @GetMapping(path = "/{customerId}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getCustomer(@PathVariable("customerId") UUID customerId) {
        return of(customerService.getCustomer(customerId));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<?> createCustomer(@RequestBody @Validated CreateCustomerDto createCustomerDto) {
        var customerId = customerService.create(createCustomerDto);

        return created(fromCurrentRequest().path("/{customerId}").build(customerId)).build();
    }
}
