package hello;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RestClientTest {

    @Test
    public void test() {
        final String result = new RestClient().callRest();
        Assert.assertTrue(result.startsWith("Quote{type='success', value=Value{id="));
    }

}
