package br.com.erpsystem.mscomercial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDTO extends BaseDTO{

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birthdate")
    private Date birthdate;
    @JsonProperty("cpf")
    private String cpf;
}
