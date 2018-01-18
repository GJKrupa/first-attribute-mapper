package uk.me.krupa.fam;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class FirstAttributeMapper<InputType,ResultType> {

    List<ConditionalProcessor<InputType, ResultType>> processors = new LinkedList<>();

    public static <InputType, ResultType> FirstAttributeMapper<InputType, ResultType> newInstance(Class<InputType> inputTypeClass, Class<ResultType> resultTypeClass) {
        return new FirstAttributeMapper<>();
    }

    public FirstAttributeMapper<InputType,ResultType> with(ConditionalProcessor<InputType,ResultType> processor) {
        processors.add(processor);
        return this;
    }

    public ResultType map(List<InputType> input, ResultType template) {
        List<ConditionalProcessor<InputType, ResultType>> procs = processors.stream().map(ConditionalProcessor::clone).collect(Collectors.toList());
        return input.stream()
                .reduce(template, (a,b) -> this.reduceFunction(a,b,procs), (a,b) -> b);
    }

    private ResultType reduceFunction(ResultType accumulator, InputType input, List<ConditionalProcessor<InputType, ResultType>> procs) {
        return procs.stream().reduce(
                accumulator,
                (acc, processor) -> processor.reduceWith(acc, input),
                (a, b) -> b);
    }

    interface Function2<Result,Arg1,Arg2> {
        Result apply(Arg1 arg1, Arg2 arg2);
    }

}
