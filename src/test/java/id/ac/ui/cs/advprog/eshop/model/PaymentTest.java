package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentDefaultStatus(){
        Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "VOUCHER", this.paymentData);
        assertEquals("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatusSuccess(){
        Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "VOUCHER", "SUCCESS", this.paymentData);
        assertEquals("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatusRejected(){
        Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "VOUCHER", "REJECTED", this.paymentData);
        assertEquals("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "VOUCHER", "RAWR", this.paymentData);        });
    }

    @Test
    void testCreatePaymentEmptyMethod(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "", this.paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "RAWR", this.paymentData);
        });
    }

    @Test
    void testSetStatusSuccess() {
        Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "VOUCHER", this.paymentData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusInvalid() {
        Payment payment = new Payment("4bc1a27a-e1df-482f-a2e5-a03e49290bc4", "VOUCHER", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("RAWR"));
    }
}