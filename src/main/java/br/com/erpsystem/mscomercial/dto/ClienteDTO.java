package br.com.erpsystem.mscomercial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClienteDTO {

    private UUID id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("birthdate")
    private Date birthdate;
    @JsonProperty("cpf")
    private String cpf;
}
