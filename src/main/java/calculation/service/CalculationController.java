package calculation.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Created by mjkrumlauf on 10/16/16.
 */
public class CalculationController extends AbstractVerticle {
    @Override
    public void start(final Future<Void> fut) throws Exception {
        final Router router = Router.router(this.vertx);

        router.route(HttpMethod.POST, "/evaluate").handler(BodyHandler.create());
        router.post("/evaluate").handler(this::calculate);

        // Create the HTTP server and pass the "accept" method to the request handler.
        this.vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );

    }

    private void calculate(final RoutingContext routingContext) {
        final MathOp mathOp = Json.decodeValue(routingContext.getBodyAsString(), MathOp.class);
        final MathResult mathResult = doCalculate(mathOp);

        routingContext.response()
                .setStatusCode(201)
                .setChunked(true)
                .putHeader("content-type", "application/json; charset=utf-8")
                .write(Json.encodePrettily(mathResult))
                .end();

    }

    private MathResult doCalculate(final MathOp mathOp) {
        System.out.println("mathOp = " + mathOp);

        double result = 0.0;
        if ("+".equals(mathOp.getOp())) {
            result = Math.addExact(mathOp.getN1(), mathOp.getN2());
        } else if ("-".equals(mathOp.getOp())) {
            result = Math.subtractExact(mathOp.getN1(), mathOp.getN2());
        } else if ("*".equals(mathOp.getOp())) {
            result = Math.multiplyExact(mathOp.getN1(), mathOp.getN2());
        } else if ("/".equals(mathOp.getOp())) {
            result = Math.floorDiv(mathOp.getN1(), mathOp.getN2());
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
