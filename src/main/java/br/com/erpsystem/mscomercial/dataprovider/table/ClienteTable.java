package br.com.erpsystem.mscomercial.dataprovider.table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class ClienteTable {

    @Id
    private UUID id;
}
