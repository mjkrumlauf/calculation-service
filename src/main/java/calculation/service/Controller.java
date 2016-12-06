package calculation.service;

import calculation.service.api.MathOp;
import calculation.service.api.MathResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjkrumlauf on 12/6/16.
 */
@RestController
public class Controller {

    private final CalculatorService calculatorService;

    @Autowired
    public Controller(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping(path = "/evaluate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MathResult> calculate(@RequestBody final MathOp mathOp) {
        final MathResult result = this.calculatorService.calculate(mathOp);
        return ResponseEntity.ok(result);
    }
}
