package com.jayghz.bookhub.service;


import com.jayghz.bookhub.dto.PaymentCaptureResponse;
import com.jayghz.bookhub.dto.PaymentOrderResponse;

public interface CheckoutService {

    PaymentOrderResponse createPayment(Integer purchaseId, String returnUrl, String cancelUrl);

    PaymentCaptureResponse capturePayment(String orderId);
}
