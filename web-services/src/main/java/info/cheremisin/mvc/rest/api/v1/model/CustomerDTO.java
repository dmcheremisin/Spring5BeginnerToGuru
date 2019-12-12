package info.cheremisin.mvc.rest.api.v1.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Long id;

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;

    @ApiModelProperty(value = "This is the last name", required = true)
    private String lastName;
}
