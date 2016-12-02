package calculator.stream.api;

import calculator.api.MathOp;
import calculator.api.MathResult;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;

import static com.lightbend.lagom.javadsl.api.Service.*;

/**
 * Created by mjkrumlauf on 11/17/16.
 */
public interface CalculatorStreamService extends Service {

    ServiceCall<MathOp, MathResult> getCalculationResult();

    @Override
    default Descriptor descriptor() {
        // @formatter:off
        return named("calculator-stream-service").withCalls(
                namedCall("/api/calculator", this::getCalculationResult)
        ).withAutoAcl(true);
        // @formatter:on
    }
}
