package com.example.demo.endpoint.rest.service;

import com.example.demo.endpoint.rest.model.entity.Payment;
import com.example.demo.endpoint.rest.model.enums.PaymentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
@RequiredArgsConstructor
public class VolaApiClient {

  @Value("${vola.api-key}")
  private String apiKey;

  private final RestTemplate restTemplate = new RestTemplate();

  public PaymentStatus checkPaymentStatus(Payment payment) {
    String url = "https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws/payment";

    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl(url)
            .queryParam("apiKey", apiKey)
            .queryParam("payerEmail", payment.getPayerEmail())
            .queryParam("pspType", payment.getPspType())
            .queryParam("pspPaymentId", payment.getPspPaymentId());

    try {
      ResponseEntity<VolaPaymentResponse> response =
          restTemplate.getForEntity(builder.toUriString(), VolaPaymentResponse.class);
      PaymentStatus status = response.getBody().getVerificationStatus();
      log.info("Vola payment status for {}: {}", payment.getPspPaymentId(), status);
      return status;
    } catch (Exception e) {
      log.error("Failed to get payment status from Vola API", e);
      return PaymentStatus.VERIFYING; // On garde l'état jusqu'à nouvelle vérif
    }
  }

  // Classe interne pour désérialiser la réponse JSON (adapter selon le JSON réel)
  private static class VolaPaymentResponse {
    private PaymentStatus verificationStatus;

    public PaymentStatus getVerificationStatus() {
      return verificationStatus;
    }

    public void setVerificationStatus(PaymentStatus verificationStatus) {
      this.verificationStatus = verificationStatus;
    }
  }
}
