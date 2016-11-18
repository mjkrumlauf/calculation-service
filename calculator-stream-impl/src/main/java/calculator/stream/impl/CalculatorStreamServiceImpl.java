package calculator.stream.impl;

import calculator.api.MathOp;
import calculator.api.MathResult;
import calculator.stream.api.CalculatorStreamService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import static java.util.concurrent.CompletableFuture.completedFuture;

/**
 * Created by mjkrumlauf on 11/17/16.
 */
public class CalculatorStreamServiceImpl implements CalculatorStreamService {

    @Override
    public ServiceCall<MathOp, MathResult> getCalculationResult() {
        return request -> completedFuture(calculate(request));
    }

    private MathResult calculate(final MathOp mathOp) {
        System.out.println("mathOp = " + mathOp);

        BigDecimal result = new BigDecimal(0.0);
        if ("+".equals(mathOp.getOp())) {
            result = mathOp.getN1().add(mathOp.getN2());
        } else if ("-".equals(mathOp.getOp())) {
            result = mathOp.getN1().subtract(mathOp.getN2());
        } else if ("*".equals(mathOp.getOp())) {
            result = mathOp.getN1().multiply(mathOp.getN2());
        } else if ("/".equals(mathOp.getOp())) {
            final MathContext mc = new MathContext(8, RoundingMode.HALF_EVEN);
            result = mathOp.getN1().divide(mathOp.getN2(), mc);
        } else {
            throw new IllegalArgumentException("Math operator " + mathOp.getOp() + " is invalid");
        }

        final MathResult mathResult = new MathResult()
                .setMathOp(mathOp)
                .setResult(result);
        System.out.println("mathResult = " + mathResult);
        return mathResult;
    }
}
