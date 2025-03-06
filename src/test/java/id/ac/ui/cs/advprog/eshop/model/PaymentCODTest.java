package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentCODTest extends PaymentTest{
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testValidCODPayment() {
        paymentData.put("address", "Margonda");
        paymentData.put("deliveryFee", "9000");
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testAddressEmptyStringCODPayment() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "10000");
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testDeliveryFeeEmptyStringCODPayment() {
        paymentData.put("address", "Kukusan");
        paymentData.put("deliveryFee", "");
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testAddressNullCODPayment() {
        paymentData.put("deliveryFee", "10000");
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testDeliveryFeeNullCODPayment() {
        paymentData.put("address", "Kukusan");
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testBothEmptyStringCODPayment() {
        paymentData.put("address", "");
        paymentData.put("address", "");
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testBothNullCODPayment() {
        PaymentCOD payment = new PaymentCOD("506d1849-9a38-4ceb-90a3-209f6ce8dc3e", PaymentMethod.COD.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}