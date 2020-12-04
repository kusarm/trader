
package com.example.job.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class Outgoing {

    public Double possibleReturnAmount;
    public Double possibleReturnAmountBefTax;
    public Double possibleReturnAmountAfterTax;
    public Double taxRate;
    public Double taxAmount;

}
