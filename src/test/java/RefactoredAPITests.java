import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Title;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import models.Login;
import models.Profile;


import static org.junit.Assert.assertEquals; //

@RunWith(SerenityRunner.class)
public class RefactoredAPITests {
    @Title("Checking for the correct user")
    @Test
    public void verifyLoginExistedUserAndReturn200() {
        Login login = new Login();
        login.setEmail("tsake.love@gmail.com");
        login.setPassword("Aa123456");
        new APIRequest().loginUser(login).then().statusCode(200);
    }

    @Title("Profile access check")
    @Test
    public void getMtProfileWithCode200() {
        new APIRequest().me().then().statusCode(200);
    }

    @Title("An attempt to log in to a non-existent user")
    @Test
    public void verifyLoginNonExistedUserAndReceiveError() {


        Login login = new Login();
        login.setEmail("NotExisted@gmail.com");
        login.setPassword("123456");
        new APIRequest().loginUser(login).then().assertThat().body(Matchers.notNullValue());

    }

    @Title("Attempt to access encrypted information")
    @Test
    public void verifyNonAuthorizationUserAndReceiveError() {

        Profile message = new APIRequest().putProfile().as(Profile.class);
        assertEquals("Authorization has been denied for this request.", message.message);

    }

    @Title("User log out")
    @Test
    public void LogOut() {

        new APIRequest().deleteProfile().then().statusCode(201);

    }
}
