package hello;

import hello.git.GitHelper;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;

import java.io.IOException;

public class GitHelperTest {

    private String gitRepoUrl = "https://github.com/eclipse/jgit.git";
    private String gitRepoBranch = "master";

    @Test
    public void test() throws GitAPIException, IOException {
        GitHelper helper = new GitHelper(gitRepoUrl, gitRepoBranch);
        helper.cloneRepo();
        helper.delete();
    }

}
