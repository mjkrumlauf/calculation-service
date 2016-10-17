package calculation.service;

import java.util.UUID;

/**
 * Created by mjkrumlauf on 10/16/16.
 */
public class MathResult {
    private MathOp mathOp;
    private double result;
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

    public double getResult() {
        return result;
    }

    public MathResult setResult(double result) {
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