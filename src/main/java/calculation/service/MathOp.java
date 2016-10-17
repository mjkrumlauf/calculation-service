package calculation.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Created by mjkrumlauf on 10/11/16.
 */
public class MathOp {
    private final int n1;
    private final int n2;
    private final String op;
    private final String id;

    @JsonCreator
    public MathOp(
            @JsonProperty("n1") int n1,
            @JsonProperty("n2") int n2,
            @JsonProperty("op") String op) {
        this.n1 = n1;
        this.n2 = n2;
        this.op = op;
        this.id = UUID.randomUUID().toString();
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
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
