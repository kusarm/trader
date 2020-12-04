
package com.example.job.json;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Incoming {

    public Integer traderId;
    public Integer playedAmount;
    public Double odd;

}
