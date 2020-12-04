package com.example.job.api;


import com.example.job.json.Trader;
import com.example.job.repository.TraderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/traders")
public class TraderController {

    private final TraderRepo traderRepo;

    @GetMapping
    public ResponseEntity<List<Trader>> findAllTraders(){
        return ResponseEntity.ok(traderRepo.findAll());
    }
}
