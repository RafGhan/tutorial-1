package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {
    Map<String, String> paymentData;
    Order order;
    List<Product> products;

    @BeforeEach
    void setUp(){
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        this.products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product2);

        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", this.products, 1708560000L, "Bambang Sugeni");
    }

    @Test
    void testContainsWithValidParam() {
        assertTrue(PaymentMethod.contains("VOUCHER_CODE"));
        assertTrue(PaymentMethod.contains("BANK_TRANSFER"));
    }

    @Test
    void testContainsWithInvalidParam() {
        assertFalse(PaymentMethod.contains("INVALID_CODE"));
    }

    @Test
    void testCreatePaymentWithInvalidOrder(){
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), null, paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherPendingStatus() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertSame(payment.getOrder(), order);
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("3150295e-3448-414e-88a2-0457016ff9bd", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus(), payment.getStatus());
        paymentData.clear();
    }  

    @Test
    void testCreatePaymentVoucherSuccessStatus() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("3150295e-3448-414e-88a2-0457016ff9bd", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }    

    @Test
    void testCreatePaymentVoucherRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("3150295e-3448-414e-88a2-0457016ff9bd", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, "HADEH");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferPendingStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertSame(payment.getOrder(), order);
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("3150295e-3448-414e-88a2-0457016ff9bd", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferSuccessStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertSame(payment.getOrder(), order);
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("3150295e-3448-414e-88a2-0457016ff9bd", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferRejectedStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, PaymentStatus.REJECTED.getValue());
        assertSame(payment.getOrder(), order);
        assertSame(paymentData, payment.getPaymentData());
        assertEquals("3150295e-3448-414e-88a2-0457016ff9bd", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferInvalidStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, "HADEH");
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentBankTransferNullStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData, null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToSuccess() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToRejected() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("HADEH");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentVoucherToNullStatus() {
        paymentData.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToSuccess() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToRejected() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToInvalidStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("HADEH");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusPaymentBankTransferToNullStatus() {
        paymentData.put("bankName", "BSI");
        paymentData.put("referenceCode", "69707172");

        Payment payment = new Payment("3150295e-3448-414e-88a2-0457016ff9bd", PaymentMethod.VOUCHER.getValue(), order, paymentData);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }  

}