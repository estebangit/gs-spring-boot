package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//        };
//    }

    //@Scheduled(cron = "* * * * *")
    @Scheduled(fixedDelay = 100000, initialDelay = 5000)
    public void callRestClient() {
        log.debug("About to call rest service ...");
        new RestClient().callRest();
        log.debug("Call to rest service done.");
    }

}
