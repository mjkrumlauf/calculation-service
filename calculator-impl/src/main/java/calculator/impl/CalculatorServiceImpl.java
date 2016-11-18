package calculator.impl;

import calculator.api.CalculatorService;
import calculator.api.MathOp;
import calculator.api.MathResult;
import calculator.stream.api.CalculatorStreamService;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import javax.inject.Inject;

public class CalculatorServiceImpl implements CalculatorService {

    private final CalculatorStreamService calculatorStreamService;

    @Inject
    public CalculatorServiceImpl(final CalculatorStreamService calculatorStreamService) {
        this.calculatorStreamService = calculatorStreamService;
    }

    @Override
    public ServiceCall<MathOp, MathResult> evaluate() {
        return request -> this.calculatorStreamService.getCalculationResult().invoke(request);
    }
}
