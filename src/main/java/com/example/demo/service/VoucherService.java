package com.example.demo.service;

import com.example.demo.VoucherType;
import com.example.demo.model.Voucher;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoucherService {

    // Simulação de base de dados
    private Map<String, Voucher> vouchers = new ConcurrentHashMap<>();

    public VoucherService() {
        // Exemplo de vouchers
        Voucher v1 = new Voucher();
        v1.setCode("PROMO10");
        v1.setDiscountPercent(10.0);
        v1.setValidUntil(java.time.LocalDate.now().plusDays(10));
        v1.setType(VoucherType.PERCENTUAL);
        v1.setMinPurchaseValue(50.0);

        Voucher v2 = new Voucher();
        v2.setCode("FIXED20");
        v2.setDiscountPercent(20.0);
        v2.setValidUntil(java.time.LocalDate.now().plusDays(5));
        v2.setType(VoucherType.VALOR_FIXO);
        v2.setMinPurchaseValue(100.0);

        vouchers.put(v1.getCode(), v1);
        vouchers.put(v2.getCode(), v2);
    }

    public Voucher findByCode(String code) {
        Voucher voucher = vouchers.get(code);

        return voucher;
    }
}