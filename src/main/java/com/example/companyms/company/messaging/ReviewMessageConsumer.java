package com.example.companyms.company.messaging;

import com.example.companyms.company.dto.ReviewMessage;
import com.example.companyms.company.service.CompanyService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RabbitListener(queues = "com_job_rating_queue")
    public void receiveMessage(ReviewMessage reviewMessage) {
        System.out.println("Message received " + reviewMessage);
        companyService.updateCompanyRating(reviewMessage);
    }
}
