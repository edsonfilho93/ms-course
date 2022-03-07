package com.devsuperior.hrpayroll.services;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${hr-worker.host}")
    private String workerHost;

    @Autowired
    private RestTemplate restTemplate;

    public Payment getPayment(Long workId, int days) {
        Map<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(workId));

        Worker worker = restTemplate.getForObject(workerHost.concat("/workers/{id}"), Worker.class, params);

        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
