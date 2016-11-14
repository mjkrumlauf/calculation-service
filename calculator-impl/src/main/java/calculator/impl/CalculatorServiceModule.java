package calculator.impl;

import calculator.api.CalculatorService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * The module that binds the CalculatorService so that it can be served.
 */
public class CalculatorServiceModule extends AbstractModule implements ServiceGuiceSupport {
  @Override
  protected void configure() {
    bindServices(serviceBinding(CalculatorService.class, CalculatorServiceImpl.class));
  }
}
