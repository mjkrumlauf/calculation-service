package calculation.service.api;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by mjkrumlauf on 10/16/16.
 */
@JsonDeserialize
public class MathResult {

    @JsonUnwrapped
    private MathOp mathOp;

    private BigDecimal result;
    private String id;

    public MathResult() {
        this.id = UUID.randomUUID().toString();
    }

    public MathOp getMathOp() {
        return mathOp;
    }

    public MathResult setMathOp(MathOp mathOp) {
        this.mathOp = mathOp;
        return this;
    }

    public BigDecimal getResult() {
        return result;
    }

    public MathResult setResult(BigDecimal result) {
        this.result = result;
        return this;
    }

    public String getCorrelationId() {
        return this.mathOp.getId();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MathResult{" +
                "mathOp=" + mathOp +
                ", result=" + result +
                ", correlationId='" + mathOp.getId() + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
