package hello.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GitHelper {

    private static final Logger log = LoggerFactory.getLogger(GitHelper.class);

    //@Value("${git.repo.url}")
    private String gitRepoUrl;

    //@Value("${git.repo.branch}")
    private String gitRepoBranch;

    private File getTempGitFolder() throws IOException {
        final File baseDir = new File(System.getProperty("java.io.tmpdir"), "git");
        if (!baseDir.exists()) {
            Files.createDirectory(baseDir.toPath());
        }
        Path tempDirWithPrefix = Files.createTempDirectory(baseDir.toPath(), "");
        log.info("Git clone temp folder {}", tempDirWithPrefix);
        return tempDirWithPrefix.toFile();
    }

    public void cloneRepo() throws GitAPIException, IOException {
        final File tmpFolder = getTempGitFolder();
        tmpFolder.deleteOnExit();
        final Git git = Git.cloneRepository()
                .setURI(getGitRepoUrl())
                .setDirectory(tmpFolder)
                .setBranch(getGitRepoBranch())
                .call();
        //Files.deleteIfExists(tmpFolder.toPath());
    }

    private String getGitRepoUrl() {
        this.gitRepoUrl = "https://github.com/eclipse/jgit.git";
        log.info("Git repo URL: '{}'", gitRepoUrl);
        return gitRepoUrl;
    }

    private String getGitRepoBranch() {
        this.gitRepoBranch = "master";
        log.info("Git repo BRANCH: '{}'", gitRepoBranch);
        return gitRepoBranch;
    }

    public void setGitRepoUrl(String gitRepoUrl) {
        this.gitRepoUrl = gitRepoUrl;
    }

    public void setGitRepoBranch(String gitRepoBranch) {
        this.gitRepoBranch = gitRepoBranch;
    }

}
