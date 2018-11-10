package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

public final class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    private RestTemplate restTemplate;

    /**
     * Constructor
     */
    public RestClient() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    public String callRest() {
        final Quote quote = this.restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        log.info("--> {}", quote);
        return quote.toString();
    }

}
