package br.com.erpsystem.mscomercial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public abstract class BaseDTO {

    private UUID id;
    private Date createdDate;

}
