package br.com.erpsystem.mssales.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    AGUARDANDO_APROVACAO, APROVADO, AGUARDANDO_PAGAMENTO, PAGO, PARCIAL_PAGO,
    FATURADO, CANCELADO, ENTREGUE, EM_PRODUCAO, PRONTO, EM_ROTA_DE_ENTREGA;
}
