package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Expose REST services
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") String id) {
        return "This is a test with id " + id;
    }

    @GetMapping("/test2/{id}")
    public ResponseEntity<String> getTest2(@PathVariable String id) {
        return new ResponseEntity<>("This is a test2 with id " + id, HttpStatus.OK);
    }

}
