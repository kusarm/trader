package com.example.job.api;

import com.example.job.json.Incoming;
import com.example.job.json.Outgoing;
import com.example.job.json.Trader;
import com.example.job.repository.TraderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/taxGeneral")
public class GeneralTaxController {

    private final TraderRepo traderRepo;

    @PostMapping("/byRate")
    public ResponseEntity<Outgoing> calculateReturnByAmount(@RequestBody Incoming incoming) {
        try {
            Trader trader = traderRepo.findById(incoming.traderId);
            Double totalAmount = incoming.getPlayedAmount() * incoming.getOdd();
            return ResponseEntity.ok(
                    Outgoing.builder()
                            .possibleReturnAmount(totalAmount)
                            .possibleReturnAmountBefTax(totalAmount)
                            .possibleReturnAmountAfterTax(totalAmount * (1 - trader.getTaxRate()))
                            .taxAmount(trader.getTaxAmount())
                            .taxRate(trader.getTaxRate())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/byAmount")
    public ResponseEntity<Outgoing> calculateReturnByRate(@RequestBody Incoming incoming) {
        try {
            Trader trader = traderRepo.findById(incoming.traderId);
            Double totalAmount = incoming.getPlayedAmount() * incoming.getOdd();
            return ResponseEntity.ok(
                    Outgoing.builder()
                            .possibleReturnAmount(totalAmount)
                            .possibleReturnAmountBefTax(totalAmount)
                            .possibleReturnAmountAfterTax(totalAmount  - trader.getTaxAmount())
                            .taxAmount(trader.getTaxAmount())
                            .taxRate(trader.getTaxRate())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

}
