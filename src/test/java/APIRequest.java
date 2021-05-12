import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Login;
import org.json.simple.JSONObject;

public class APIRequest {
    public Response getAllUsers() {
        return given().when().
                get(Config.USERS_ENDPOINT).
                then().extract().response();
    }

    public Response getPageUsers(long pageNumber) {
        return given().
                pathParam("pageNumber", pageNumber).when().
                get(Config.PAGE_USERS_ENDPOINT).
                then().
                extract().response();
    }

    public Response getPageAndCountUser(long countNumber) {
        return given().
                when().
                get(Config.COUNT_USERS_ENDPOINT).
                then().
                extract().response();
    }

    public Response deleteProfile() {
        return given().
                when().
                delete(Config.LOGIN_ENDPOINT).
                then().
                extract().response();
    }

    public Response putProfile() {
        return given().
                when().
                put(Config.PROFILE).
                then().
                extract().response();
    }


    public Response getUserById(long id) {
        return given().
                pathParam("userId", id).when().
                get(Config.USER_BY_ID).
                then().
                extract().response();
    }

    public Response me() {
        return given().
                when().
                get(Config.LOGIN_ME).then().extract().response();
    }


    public Response loginUser(Login login) {

        return given().
                body(login).
                post(Config.LOGIN_ENDPOINT).
                then().extract().response();
    }


    private RequestSpecification given() {
        return RestAssured.given()
                .log().uri()
                .log().body()
                .baseUri(Config.BASE_URL)
                .header("API-KEY", Config.apiToken)
                .contentType(ContentType.JSON);
    }
}
