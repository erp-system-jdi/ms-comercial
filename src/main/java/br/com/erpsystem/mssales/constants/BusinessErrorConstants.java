package br.com.erpsystem.mssales.constants;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BusinessErrorConstants {
    public static final String PRODUCT_OUT_OF_STOCK = "[Business Exception] Product Out Of Stock";

    public static final String CUSTOMER_NOT_REGISTERED = "[Business Exception] Customer not registered. Please register customer!";

    public static final String EXPIRED_ESTIMATE = "[Business Exception] Expired Estimate. Please request a new Estimate!";
}
