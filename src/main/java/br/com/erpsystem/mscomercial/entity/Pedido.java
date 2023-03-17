package br.com.erpsystem.mscomercial.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "TB_02_PEDIDO")
public class Pedido{


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "valor_total", unique = true)
    private BigDecimal valorTotal;

    @Column(name = "cliente_id", unique = true)
    private UUID clienteId;
}
