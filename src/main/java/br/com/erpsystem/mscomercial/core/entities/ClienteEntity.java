package br.com.erpsystem.mscomercial.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteEntity {
    private UUID id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String rg;
    private EnderecoEntity endereco;
    private DepartamentoEntity departamento;


}
