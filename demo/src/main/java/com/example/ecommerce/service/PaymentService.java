package com.example.ecommerce.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

import demo.src.main.java.com.example.ecommerce.model.Payment;

@Service
public class PaymentService {
    @Value("${stripe.apiKey}")
    private String stripeApiKey;

    public PaymentService() {
        Stripe.apiKey = stripeApiKey;
    }

    public Charge charge(Payment payment) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", payment.getAmount());
        chargeParams.put("currency", "usd");
        chargeParams.put("source", payment.getStripeToken());
        chargeParams.put("description", "Order payment");

        return Charge.create(chargeParams);
    }
}
