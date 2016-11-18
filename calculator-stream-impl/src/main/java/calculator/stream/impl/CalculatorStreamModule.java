package calculator.stream.impl;

import calculator.stream.api.CalculatorStreamService;
import com.google.inject.AbstractModule;
import com.lightbend.lagom.javadsl.server.ServiceGuiceSupport;

/**
 * Created by mjkrumlauf on 11/17/16.
 */
public class CalculatorStreamModule extends AbstractModule implements ServiceGuiceSupport {
    @Override
    protected void configure() {
        bindServices(serviceBinding(CalculatorStreamService.class, CalculatorStreamServiceImpl.class));
    }
}
