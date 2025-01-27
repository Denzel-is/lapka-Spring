package com.example.maestropizza.service;



import com.example.maestropizza.model.Order;
import com.example.maestropizza.model.PaymentInfo;
import com.example.maestropizza.repository.PaymentInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    public PaymentInfo processPayment(Order order, PaymentInfo paymentDetails) {
        // В реальном проекте тут интеграция с платёжным сервисом
        paymentDetails.setOrder(order);
        return paymentInfoRepository.save(paymentDetails);
    }
}
