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

        final String json = Json.encodePrettily(new MathOp(2, 2, "+"));
        final String length = Integer.toString(json.length());

        vertx.createHttpClient().post(8080, "localhost", "/evaluate")
                .putHeader("content-type", "application/json")
                .putHeader("content-length", length)
                .handler(response -> {
                    context.assertEquals(response.statusCode(), 201);
                    context.assertTrue(response.headers().get("content-type").contains("application/json"));
					response.bodyHandler(body -> {
                        final MathResult mathResult = Json.decodeValue(body.toString(), MathResult.class);
						context.assertEquals(mathResult.getResult(), 4.0);
						async.complete();
					});
				}).write(json)
                .end();
	}

}
