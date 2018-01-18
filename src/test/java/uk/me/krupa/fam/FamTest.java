package uk.me.krupa.fam;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FamTest {

    @Test
    public void simpleTest() {
        List<SourceType> data = Arrays.asList(
                new SourceType(null, null, null),
                new SourceType("fred", null, null),
                new SourceType("bert", "28", null),
                new SourceType(null, null, null),
                new SourceType(null, null, "167.89"),
                new SourceType("never", "see", "these")
        );

        FirstAttributeMapper<SourceType, DestType> mapper = FirstAttributeMapper
                .newInstance(SourceType.class, DestType.class)
                .with(new ConditionalProcessorImpl<>(SourceType::getName, (res, val) -> res.withName(val.toUpperCase())))
                .with(new ConditionalProcessorImpl<>(SourceType::getAge, (res, val) -> res.withAge(Integer.parseInt(val) * 2)))
                .with(new ConditionalProcessorImpl<>(SourceType::getHeight, (res, val) -> res.withHeight(Double.parseDouble(val))));

        DestType result = mapper.map(data, new DestType());

        assertThat(result.getName(), is(equalTo("FRED")));
        assertThat(result.getAge(), is(equalTo(56)));
        assertThat(result.getHeight(), is(equalTo(167.89)));
    }

}
