package uk.me.krupa.fam;

import java.util.function.Function;

public class ConditionalProcessorImpl<InputType, ResultType, ValueType> implements ConditionalProcessor<InputType,ResultType> {
    private final Function<InputType, ValueType> accessor;
    private final FirstAttributeMapper.Function2<ResultType, ResultType, ValueType> folder;
    private boolean matched = false;

    public ConditionalProcessorImpl(
            Function<InputType, ValueType> accessor,
            FirstAttributeMapper.Function2<ResultType, ResultType, ValueType> folder) {
        this.accessor = accessor;
        this.folder = folder;
    }

    public ResultType reduceWith(ResultType accumulator, InputType sourceValue) {
        ValueType value = accessor.apply(sourceValue);
        if (value != null && !matched) {
            matched = true;
            return folder.apply(accumulator, value);
        } else {
            return accumulator;
        }
    }

    @Override
    public ConditionalProcessor<InputType, ResultType> clone() {
        return new ConditionalProcessorImpl<>(accessor, folder);
    }
}