package calculator.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.lightbend.lagom.serialization.Jsonable;

import javax.annotation.concurrent.Immutable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by mjkrumlauf on 10/11/16.
 */
@Immutable
@JsonDeserialize
public class MathOp implements Jsonable {
    private final BigDecimal n1;
    private final BigDecimal n2;
    private final String op;
    private final String id;

    @JsonCreator
    public MathOp(
            @JsonProperty("n1") BigDecimal n1,
            @JsonProperty("n2") BigDecimal n2,
            @JsonProperty("op") String op) {
        this.n1 = n1;
        this.n2 = n2;
        this.op = op;
        this.id = UUID.randomUUID().toString();
    }

    public BigDecimal getN1() {
        return n1;
    }

    public BigDecimal getN2() {
        return n2;
    }

    public String getOp() {
        return op;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "MathOp{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                ", op='" + op + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
