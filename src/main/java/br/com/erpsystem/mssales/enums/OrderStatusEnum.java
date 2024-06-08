package br.com.erpsystem.mssales.enums;

import lombok.Getter;

@Getter
public enum OrderStatusEnum {

    AGUARDANDO_APROVACAO, APROVADO, AGUARDANDO_PGTO, ENTRADA_PAGA, PAGO,
    FATURADO, CANCELADO, EM_PRODUCAO, PRONTO, ROTA_DE_ENTREGA, ENTREGUE;
    ;
}
