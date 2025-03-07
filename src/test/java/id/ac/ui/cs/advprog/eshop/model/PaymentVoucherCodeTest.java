package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentVoucherCodeTest extends PaymentTest{
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        paymentData = new HashMap<>();
    }

    @Test
    void testValidVoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("f3978c74-088c-463f-8d3f-ffa1f4a61127", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testShortVoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234A5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("f3978c74-088c-463f-8d3f-ffa1f4a61127", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testDontStartWithESHOPVoucherCode() {
        this.paymentData.put("voucherCode", "1234ESHOPABC5678");
        PaymentVoucherCode payment = new PaymentVoucherCode("f3978c74-088c-463f-8d3f-ffa1f4a61127", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testNotEightNumericalCharsVoucherCode(){
        this.paymentData.put("voucherCode", "ESHOP1234ABCD678");
        PaymentVoucherCode payment = new PaymentVoucherCode("f3978c74-088c-463f-8d3f-ffa1f4a61127", PaymentMethod.VOUCHER.getValue(), this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}