package com.example.demo.model;

import com.example.demo.VoucherType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Voucher {
    private String code;
    private Double discountPercent;
    private LocalDate validUntil;
    private VoucherType type; // "PERCENTUAL" ou "VALOR_FIXO"
    private Double minPurchaseValue;

    public Voucher() {}

    public Voucher(String code, Double discountPercent, LocalDate validUntil, VoucherType type, Double minPurchaseValue) {
        this.code = code;
        this.discountPercent = discountPercent;
        this.validUntil = validUntil;
        this.type = type;
        this.minPurchaseValue = minPurchaseValue;
    }

    public boolean isExpired() {
        return validUntil.isBefore(LocalDate.now());
    }

}