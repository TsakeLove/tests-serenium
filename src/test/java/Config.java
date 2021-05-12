import java.util.Base64;

public class Config {
    public static String githubAPIToken = new String(Base64.getDecoder().decode("dG9rZW4gZ2hwX2tGNXpoeTVWOFMxSnJVSXY0YW9YeEtZYVQzNWc4bTRkdEE5Yw=="));
    public static String githubAPIBaseURI = "https://api.github.com";
    public static String githubAPIgetUsersPath = "/users";
    public static String githubAPIcreateUserProjectPath = "/user/projects";
    public static String githubAPIupdateUserProjectPath = "/projects/{projectId}";
    public static String githubAPIdeleteUserProjectPath = "/projects/{projectId}";
    public static String githubAPIgetIssues = "/repos/inidzelsky/tests-serenium/issues";
    public static String githubAPIgetEmail = "/user/emails";
    public static String githubAPIdeleteEmail = "/user/emails";
    public static String githubAPIdeleteUserBlock = "/user/blocks/{username}";
}
