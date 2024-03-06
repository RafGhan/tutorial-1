package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Builder
@Getter
public class Payment {

    String id;
    String method;
    Order order;
    Map<String, String> paymentData;

    @Setter
    String status;

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {}

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {}
}