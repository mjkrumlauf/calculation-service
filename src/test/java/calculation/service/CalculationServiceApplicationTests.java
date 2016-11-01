package calculation.service;

import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

@RunWith(VertxUnitRunner.class)
public class CalculationServiceApplicationTests {

	private Vertx vertx;

	@Before
	public void setUp(final TestContext context) {
		vertx = Vertx.vertx();
		vertx.deployVerticle(CalculationController.class.getName(),
				context.asyncAssertSuccess());
	}

	@After
	public void tearDown(final TestContext context) {
		vertx.close(context.asyncAssertSuccess());
	}

	@Test
	public void testMyApplication(final TestContext context) {
		final Async async = context.async();

        final String json = Json.encodePrettily(new MathOp(new BigDecimal("5.01"), new BigDecimal("5.01"), "*"));
        final String length = Integer.toString(json.length());

        vertx.createHttpClient().post(8080, "localhost", "/evaluate")
                .putHeader("content-type", "application/json")
                .putHeader("content-length", length)
                .handler(response -> {
                    context.assertEquals(response.statusCode(), 201);
                    context.assertTrue(response.headers().get("content-type").contains("application/json"));
					response.bodyHandler(body -> {
                        final MathResult mathResult = Json.decodeValue(body.toString(), MathResult.class);
						context.assertEquals(mathResult.getResult(), new BigDecimal("25.1001"));
						async.complete();
					});
				}).write(json)
                .end();
	}

}
