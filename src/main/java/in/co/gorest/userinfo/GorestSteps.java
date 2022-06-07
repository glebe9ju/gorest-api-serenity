package in.co.gorest.userinfo;

import in.co.gorest.constants.EndPoints;
import in.co.gorest.model.GorestPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class GorestSteps {
    @Step("Creating user with Name: {0}, email: {1}, gender: {2}, status:{3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        GorestPojo gorestPojo = new GorestPojo();
        gorestPojo.setName(name);
        gorestPojo.setEmail(email);
        gorestPojo.setGender(gender);
        gorestPojo.setStatus(status);

        HashMap<String, Object> header1 = new HashMap<>();
        header1.put("Content-Type", "application/json");
        header1.put("Accept", "application/json");
        header1.put("Authorization", "Bearer 536ea844ae5b2495510205bd3d00e91048cec1a3a7d9b7c888c81773be1f1954");
        return SerenityRest.given().log().all()
                .headers(header1)
                .body(gorestPojo)
                .when()
                .post(EndPoints.CREATE_USER)
                .then();
    }
    @Step("Getting the user information with name: {0}")
    public HashMap<String, Object> getUserInfoByFirstName(String name){
        String p1 = "findAll{it.name==";
        String p2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + name+ p2);
            }
            @Step("Updating user information with userId: {0}, name: {1}, email: {2}, gender: {3}, status:{4}")
    public ValidatableResponse updateUser(int userId,String name, String email, String gender, String status){
        GorestPojo gorestPojo = new GorestPojo();
        gorestPojo.setName(name);
        gorestPojo.setEmail(email);
        gorestPojo.setGender(gender);
        gorestPojo.setStatus(status);

        HashMap<String, Object> header1 = new HashMap<>();
        header1.put("Accept", "application/json");
        header1.put("Authorization", "Bearer 536ea844ae5b2495510205bd3d00e91048cec1a3a7d9b7c888c81773be1f1954");

        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .headers(header1)
                .pathParam("userId",userId)
                .body(gorestPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();

            }
            @Step("Deleting the user information with userId: {0}")
    public ValidatableResponse deleteUser(int userId){
        HashMap<String, Object> header1 = new HashMap<>();
        header1.put("Accept", "application/json");
        header1.put("Authorization", "Bearer 536ea844ae5b2495510205bd3d00e91048cec1a3a7d9b7c888c81773be1f1954");

        return SerenityRest.given().log().all()
                .headers(header1)
                .pathParam("userID", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
            }
            @Step("Getting user iinformatioin with userId: {0}")
    public ValidatableResponse getUserById(int userId){
        return SerenityRest.given().log().all()
                .pathParam("userID",userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
            }
}
