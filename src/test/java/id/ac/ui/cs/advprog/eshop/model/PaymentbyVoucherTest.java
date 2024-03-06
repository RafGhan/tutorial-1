package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentByVoucherTest {
    List<Product> products;
    List<Order> orders;
    List<Payment> payments;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductQuantity(2);
        product1.setProductName("Sampo Cap Bambang");
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductQuantity(1);
        product2.setProductName("Sampo Cap Usep");
        products.add(product2);

        orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Bambang Sugeni");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
            products, 1708570000L, "Tatang Sutarman");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
            products, 1708570000L, "Bambang Sugeni");
        orders.add(order3);
    }

    @Test
    void testCreatePaymentSuccessfulVoucher() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP697A71B273C");

        Payment payment = new PaymentByVoucher("42b160ec-0f98-4f18-b950-cbd22872f7af", orders.get(1), PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        assertSame(orders.get(1), payment.getOrder());
        assertEquals("42b160ec-0f98-4f18-b950-cbd22872f7af", payment.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payment.getMethod());
        assertEquals(paymentDataVoucher, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentVoucherWithStatus() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP697A71B273C");

        PaymentByVoucher paymentVoucherCode = new PaymentByVoucher("ec482653-a0df-48c3-a6bc-e5d352476642", orders.get(0), 
        PaymentMethod.VOUCHER.getValue(), paymentDataVoucher, PaymentStatus.SUCCESS.getValue());
        assertSame(orders.get(0), paymentVoucherCode.getOrder());
        assertEquals("ec482653-a0df-48c3-a6bc-e5d352476642", paymentVoucherCode.getId());
        assertEquals(PaymentMethod.VOUCHER.getValue(), paymentVoucherCode.getMethod());
        assertEquals(paymentDataVoucher, paymentVoucherCode.getPaymentData());
        assertEquals(PaymentStatus.SUCCESS.getValue(), paymentVoucherCode.getStatus());
    }

    @Test
    void testCreatePaymentVoucherFailed16Length() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP697A71B273C69");

        assertThrows(IllegalArgumentException.class, ()-> { 
            new PaymentByVoucher("9266bc12-dbd9-4a06-bdcc-a02ea8d702d2",orders.get(1),
            PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucherFailedESHOPStart() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "EHALOSHOP69727142731");

        assertThrows(IllegalArgumentException.class, ()-> { 
            new PaymentByVoucher("9266bc12-dbd9-4a06-bdcc-a02ea8d702d2",orders.get(1),
            PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }

    @Test
    void testCreatePaymentVoucherFailed8Numerical() {
        Map<String, String> paymentDataVoucher = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP69AAAABAAAC");

        assertThrows(IllegalArgumentException.class, ()-> {
            new PaymentByVoucher("9266bc12-dbd9-4a06-bdcc-a02ea8d702d2",orders.get(1),
            PaymentMethod.VOUCHER.getValue(), paymentDataVoucher);
        });
    }
}
