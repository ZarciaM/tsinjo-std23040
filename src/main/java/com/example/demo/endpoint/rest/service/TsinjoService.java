package com.example.demo.endpoint.rest.service;

import com.example.demo.endpoint.rest.model.dto.DonationRequest;
import com.example.demo.endpoint.rest.model.entity.Donation;
import com.example.demo.endpoint.rest.model.entity.Donor;
import com.example.demo.endpoint.rest.model.entity.Payment;
import com.example.demo.endpoint.rest.model.enums.PaymentStatus;
import com.example.demo.endpoint.rest.repository.DonationRepository;
import com.example.demo.endpoint.rest.repository.PaymentRepository;
import java.time.Instant;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TsinjoService {

  private final DonationRepository donationRepository;
  private final PaymentRepository paymentRepository;
  private final VolaApiClient volaApiClient;

  public void submitDonation(DonationRequest request) {
    Payment payment = new Payment();
    payment.setId(request.getPspPaymentId());
    payment.setPspPaymentId(request.getPspPaymentId());
    payment.setAmount(request.getAmount());
    payment.setPspType("ORANGE_MONEY");
    payment.setVerificationStatus(PaymentStatus.VERIFYING);
    payment.setCreationInstant(Instant.now());
    payment.setLastPspVerificationInstant(Instant.now());
    payment.setVerificationAttemptNb(0);
    payment.setPayerEmail(request.getEmail());

    paymentRepository.save(payment);

    Donor donor = new Donor();
    donor.setFullName(request.getFullName());
    donor.setEmail(request.getEmail());

    Donation donation = new Donation();
    donation.setDonor(donor);
    donation.setDate(LocalDate.now());
    donation.setPayment(payment);

    donationRepository.save(donation);
  }

  // Méthode appelée périodiquement pour vérifier les paiements en statut VERIFYING
  public void verifyPayments() {
    paymentRepository.findAll().stream()
        .filter(p -> p.getVerificationStatus() == PaymentStatus.VERIFYING)
        .forEach(
            payment -> {
              PaymentStatus newStatus = volaApiClient.checkPaymentStatus(payment);
              if (newStatus != payment.getVerificationStatus()) {
                payment.setVerificationStatus(newStatus);
                payment.setLastPspVerificationInstant(Instant.now());
                payment.setVerificationAttemptNb(payment.getVerificationAttemptNb() + 1);
                paymentRepository.save(payment);
              }
            });
  }
}
