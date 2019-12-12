package info.cheremisin.mvc.rest.api.v1.mapper;

import info.cheremisin.mvc.rest.api.v1.model.CustomerDTO;
import info.cheremisin.mvc.rest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTOMapper(Customer customer);

    Customer customerDTOToCustomerMapper(CustomerDTO customerDTO);
}
