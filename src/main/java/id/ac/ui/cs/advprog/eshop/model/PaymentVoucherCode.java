package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Builder
@Getter
public class PaymentVoucherCode extends Payment{
    @Setter
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public PaymentVoucherCode(String id, String method, Map<String, String> paymentData, String voucherCode) {
        super(id, method, paymentData);
    }
    public PaymentVoucherCode(String id, String method, String status, Map<String, String> paymentData, String voucherCode) {
        super(id, method, status, paymentData);
    }
}