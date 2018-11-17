package hello.git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GitHelper {

    private static final Logger log = LoggerFactory.getLogger(GitHelper.class);

    private String gitRepoUrl;

    private String gitRepoBranch;

    private Git git;

    /**
     * Constructor
     *
     * @param gitRepoUrl
     * @param gitRepoBranch
     */
    public GitHelper(String gitRepoUrl, String gitRepoBranch) {
        this.gitRepoUrl = gitRepoUrl;
        this.gitRepoBranch = gitRepoBranch;
    }

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
        this.git = Git.cloneRepository()
                .setURI(getRepoUrl())
                .setDirectory(tmpFolder)
                .setBranch(getRepoBranch())
                .call();
        //Files.deleteIfExists(tmpFolder.toPath());
    }

    private String getRepoUrl() {
        log.info("Git repo URL: '{}'", gitRepoUrl);
        return gitRepoUrl;
    }

    private String getRepoBranch() {
        log.info("Git repo BRANCH: '{}'", gitRepoBranch);
        return gitRepoBranch;
    }

    public void delete() {
        git.close();
    }

}
