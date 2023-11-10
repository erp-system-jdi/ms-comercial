package br.com.erpsystem.mssales.entity;

import br.com.erpsystem.mssales.enums.PaymentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TB_03_ESTIMATE")
public class Estimate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "customer_cpf")
    private String customerCpf;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "estimate_id")
    private Set<EstimateItem> productsEstimate;
    @Column(name = "payment_type")
    private PaymentTypeEnum paymentType;
    @Column(name = "validate_estimate")
    private LocalDate validateEstimate;
    @Column(name = "matriculation")
    private String matriculation;



}
