package hello;

import hello.git.GitHelper;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Expose REST services
 */
@RestController
public class HelloController {

    @Value("${git.repo.url}")
    private String gitRepoUrl;

    @Value("${git.repo.branch}")
    private String gitRepoBranch;

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

    @GetMapping("/git")
    public ResponseEntity<String> git() {
        try {
            GitHelper helper = new GitHelper(gitRepoUrl, gitRepoBranch);
            helper.cloneRepo();
            helper.delete();
        } catch (GitAPIException | IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to clone repo. " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Repo cloned.", HttpStatus.OK);
    }

}
