package info.cheremisin.mvc.rest.services;

import info.cheremisin.mvc.rest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO getCustomerByLastName(String lastName);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long id);
}
