package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class RestClientTest {

    @Test
    public void test() {
        String result = new RestClient().callRest();
        System.out.println("-> " + result);
    }

}
