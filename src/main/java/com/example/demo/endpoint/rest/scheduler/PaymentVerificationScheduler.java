package com.example.demo.endpoint.rest.scheduler;

import com.example.demo.endpoint.rest.service.TsinjoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentVerificationScheduler {

  private final TsinjoService tsinjoService;

  @Scheduled(fixedDelayString = "${payment.verification.delay}")
  public void scheduledPaymentVerification() {
    log.info("Début de la vérification des paiements en attente");
    tsinjoService.verifyPayments();
    log.info("Fin de la vérification des paiements");
  }
}
