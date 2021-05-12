import models.Project;
import models.User;
import models.Email;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class RefactoredGithubAPITests {
    @Title("getUsers")
    @Test
    public void getUsers() {
        User[] users = new GithubAPIRequestService()
                .getUsersRequest()
                .then()
                .statusCode(200)
                .extract()
                .as(User[].class);

        assertThat(users.length, equalTo(30));
    }

    @Title("updateUserProject")
    @Test
    public void updateUserProject() {
        Project project = new GithubAPIRequestService()
                .createUserProjectRequest("create-project")
                .as(Project.class);

        long projectId = project.id;

        String updatedName = "closed-rest-assured-test-project";
        String updatedState = "closed";

        Project updatedProject = new GithubAPIRequestService()
                .updateUserProjectRequest(projectId, updatedName, updatedState)
                .then()
                .statusCode(200)
                .extract()
                .as(Project.class);

        assertThat(updatedProject.name, equalTo("closed-rest-assured-test-project"));
        assertThat(updatedProject.state, equalTo("closed"));
    }

    @Title("createUserProject")
    @Test
    public void createUserProject() {
        String projectName = "rest-assured-test-project";
        Project project = new GithubAPIRequestService()
                .createUserProjectRequest(projectName)
                .then()
                .statusCode(201)
                .extract()
                .as(Project.class);

        assertThat(project.name, equalTo("rest-assured-test-project"));
    }

    @Title("deleteUserProject")
    @Test
    public void deleteUserProject() {
        Project project = new GithubAPIRequestService()
                .createUserProjectRequest("create-project")
                .as(Project.class);

        long projectId = project.id;

        new GithubAPIRequestService().deleteUserProjectRequest(projectId).then().statusCode(204);
    }

    // New tests
    @Title("getRepositoryIssues")
    @Test
    public void getRepositoryIssues() {
        new GithubAPIRequestService().getRepositoryIssues().then().statusCode(200);
    }

    @Title("getUserEmails")
    @Test
    public void getUserEmails() {
        Email[] emails = new GithubAPIRequestService()
                .getUserEmails()
                .then()
                .statusCode(200)
                .extract()
                .as(Email[].class);

        assertThat(emails[0].email, equalTo("inidzelsky@gmail.com"));
    }

    @Title("deleteUserBlock")
    @Test
    public void deleteUserBlock() {
        new GithubAPIRequestService()
                .deleteUserBlock("TsakeLove")
                .then()
                .statusCode(204);

    }

    // This fill fall
    @Title("deleteUserEmail")
    @Test
    public void deleteUserEmail() {
        new GithubAPIRequestService()
                .deleteUserEmail("octocat@cat.com")
                .then()
                .statusCode(204);
    }
}
