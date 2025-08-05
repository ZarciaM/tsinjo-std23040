package com.example.demo.endpoint.rest.controller;

import com.example.demo.endpoint.rest.model.dto.DonationRequest;
import com.example.demo.endpoint.rest.model.dto.TransactionView;
import com.example.demo.endpoint.rest.model.entity.Donation;
import com.example.demo.endpoint.rest.model.entity.Help;
import com.example.demo.endpoint.rest.service.TsinjoService;
import com.example.demo.endpoint.rest.repository.DonationRepository;
import com.example.demo.endpoint.rest.repository.HelpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class TsinjoController {

    private final DonationRepository donationRepository;
    private final HelpRepository helpRepository;
    private final TsinjoService tsinjoService;

    @GetMapping("/")
    public String home(Model model) {
        List<Donation> donations = donationRepository.findAll();
        List<Help> helps = helpRepository.findAll();

        List<TransactionView> transactionViews = donations.stream().map(d -> {
            TransactionView v = new TransactionView();
            v.setFullName(d.getDonor().getFullName());
            v.setEmail(d.getDonor().getEmail());
            v.setAmount(d.getPayment().getAmount());
            v.setDate(d.getDate());
            v.setPaymentStatus(d.getPayment().getVerificationStatus());
            v.setDonation(true);
            return v;
        }).collect(Collectors.toList());

        transactionViews.addAll(helps.stream().map(h -> {
            TransactionView v = new TransactionView();
            v.setFullName(h.getBeneficiary().getFullName());
            v.setEmail(h.getBeneficiary().getEmail());
            v.setAmount(h.getPayment().getAmount());
            v.setDate(h.getDate());
            v.setPaymentStatus(h.getPayment().getVerificationStatus());
            v.setDescription(h.getDescription());
            v.setDonation(false);
            return v;
        }).collect(Collectors.toList()));

        // Trier anti-chronologique par date
        transactionViews.sort((a, b) -> b.getDate().compareTo(a.getDate()));

        model.addAttribute("transactions", transactionViews);
        model.addAttribute("donationRequest", new DonationRequest());
        return "index";
    }

    @PostMapping("/donate")
    public String donate(@ModelAttribute DonationRequest donationRequest) {
        tsinjoService.submitDonation(donationRequest);
        return "redirect:/";
    }
}
