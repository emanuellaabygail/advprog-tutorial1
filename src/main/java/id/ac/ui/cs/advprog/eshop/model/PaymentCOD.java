package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class PaymentCOD extends Payment {
    @Setter
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public PaymentCOD(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }

    public PaymentCOD(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, paymentData);
    }
}