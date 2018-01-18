package uk.me.krupa.fam;

public interface ConditionalProcessor<InputType,ResultType> {

    ResultType reduceWith(ResultType accumulator, InputType sourceValue);
    ConditionalProcessor<InputType,ResultType> clone();

}
