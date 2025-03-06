package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class PaymentVoucherCode extends Payment{
    @Setter
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public PaymentVoucherCode(String id, String method, Map<String, String> paymentData) {
        super(id, method, paymentData);
        String voucherCode = paymentData.get("voucherCode");

        if(voucherCode.length()!=16){
            this.status = PaymentStatus.REJECTED.getValue();
        } else if(!voucherCode.startsWith("ESHOP")){
            this.status = PaymentStatus.REJECTED.getValue();
        } else if(countNum(voucherCode)!=8){
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }
    public PaymentVoucherCode(String id, String method, String status, Map<String, String> paymentData) {
        super(id, method, status, paymentData);
        String voucherCode = paymentData.get("voucherCode");

        if(voucherCode.length()!=16){
            this.status = PaymentStatus.REJECTED.getValue();
        } else if(!voucherCode.startsWith("ESHOP")){
            this.status = PaymentStatus.REJECTED.getValue();
        } else if(countNum(voucherCode)!=8){
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }
    private int countNum(String str){
        int count = 0;
        for(int i = 0; i < str.length(); i++){
            if(Character.isDigit(str.charAt(i))){
                count++;
            }
        }
        return count;
    }
}