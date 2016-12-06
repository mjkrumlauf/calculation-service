package calculation.service;

import calculation.service.api.MathOp;
import calculation.service.api.MathResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by mjkrumlauf on 11/17/16.
 */
@Service
public class CalculatorService {

    public MathResult calculate(final MathOp mathOp) {
        System.out.println("mathOp = " + mathOp);

        final BigDecimal result;
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
