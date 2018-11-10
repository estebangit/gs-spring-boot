package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Expose REST services
 */
@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String test(@PathVariable("id") String id) {
        return "This is a test with id " + id;
    }

    @GetMapping("/test2/{id}")
    public ResponseEntity<?> getTest2(@PathVariable String id) {
        return new ResponseEntity<>("This is a test2 with id " + id, HttpStatus.OK);
    }

}
