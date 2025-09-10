package com.example.demo.controller;

import com.example.demo.model.Voucher;
import com.example.demo.service.VoucherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vouchers")
@Tag(name = "Voucher", description = "Voucher management APIs")
public class VoucherController {
    private static final String SUCCESS_CODE = "200";
    private static final String NOT_FOUND_CODE = "404";
    private static final String CODE_PATH = "/{code}";
    private static final String NOT_FOUND_MESSAGE = "Voucher not found or expired";

    private final VoucherService voucherService;

    @Operation(
            summary = "Get voucher by code",
            description = "Returns voucher information if the code exists and is not expired. Information includes discount percent, expiration date, type (PERCENTUAL or VALOR_FIXO), and minimum purchase value."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = SUCCESS_CODE, description = "Voucher found and valid"),
            @ApiResponse(responseCode = NOT_FOUND_CODE, description = NOT_FOUND_MESSAGE)
    })
    @GetMapping(CODE_PATH)
    public ResponseEntity<Voucher>  getVoucher(@PathVariable String code) {
        Voucher voucher = voucherService.findByCode(code);

        if (voucher == null || voucher.isExpired()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Voucher.builder()
                .discountPercent(voucher.getDiscountPercent())
                .validUntil(voucher.getValidUntil())
                .type(voucher.getType())
                .minPurchaseValue(voucher.getMinPurchaseValue())
                .build());
    }
}