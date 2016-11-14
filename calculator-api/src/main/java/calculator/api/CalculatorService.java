package calculator.api;

import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import static com.lightbend.lagom.javadsl.api.Service.*;
import static com.lightbend.lagom.javadsl.api.Service.restCall;

/**
 * The calculator service interface.
 * <p>
 * This describes everything that Lagom needs to know about how to serve and
 * consume the CalculatorService.
 */
public interface CalculatorService extends Service {

  ServiceCall<MathOp, MathResult> evaluate();

  @Override
  default Descriptor descriptor() {
    // @formatter:off
    return named("calculator-service").withCalls(
        restCall(Method.POST, "/evaluate",  this::evaluate))
            .withAutoAcl(true);
    // @formatter:on
  }
}
