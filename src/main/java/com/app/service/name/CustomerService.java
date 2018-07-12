package com.app.service.name;

public interface CustomerService {
    String setCustomerName(String name);

    String setCustomerSurname(String surname);

    Integer setCustomerAge(String age);

    Long OneCustomerFromOneCountry(String customerName, String customerSurname, Long customerId);
}
