package com.example.job.repository;

import com.example.job.json.Trader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TraderRepo {
    private List<Trader> traders = new ArrayList<>(Arrays.asList(
            Trader.builder().ID(1).name("First").taxAmount(2.0).taxRate(0.1).build(),
            Trader.builder().ID(2).name("Second").taxAmount(4.0).taxRate(0.2).build(),
            Trader.builder().ID(3).name("Third").taxAmount(6.0).taxRate(0.3).build()
    ));

    public Trader findById(int ID) throws Exception {
        return traders.stream().filter(trader -> trader.getID() == ID).findFirst().orElseThrow(Exception::new);
    }

    public List<Trader> findAll(){
        return traders;
    }
}
