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
@RequestMapping(path = "/taxWinnings")
public class WinningsTaxController {

    private final TraderRepo traderRepo;

    @PostMapping("/byRate")
    public ResponseEntity<Outgoing> calculateReturnByRate(@RequestBody Incoming incoming) {
        try {
            Trader trader = traderRepo.findById(incoming.traderId);
            Double totalAmount = incoming.getPlayedAmount() * incoming.getOdd();
            Double winnings = (totalAmount - incoming.getPlayedAmount()) * (1 - trader.getTaxRate());
            return ResponseEntity.ok(
                    Outgoing.builder()
                            .possibleReturnAmount(totalAmount)
                            .possibleReturnAmountBefTax(totalAmount)
                            .possibleReturnAmountAfterTax(incoming.getPlayedAmount() + winnings)
                            .taxAmount(trader.getTaxAmount())
                            .taxRate(trader.getTaxRate())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/byAmount")
    public ResponseEntity<Outgoing> calculateReturnByAmount(@RequestBody Incoming incoming) {
        try {
            Trader trader = traderRepo.findById(incoming.traderId);
            Double totalAmount = incoming.getPlayedAmount() * incoming.getOdd();
            Double winnings = (totalAmount - incoming.getPlayedAmount()) - trader.getTaxAmount();
            return ResponseEntity.ok(
                    Outgoing.builder()
                            .possibleReturnAmount(totalAmount)
                            .possibleReturnAmountBefTax(totalAmount)
                            .possibleReturnAmountAfterTax(incoming.getPlayedAmount() + ((winnings < 0) ? 0 : winnings))
                            .taxAmount(trader.getTaxAmount())
                            .taxRate(trader.getTaxRate())
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
