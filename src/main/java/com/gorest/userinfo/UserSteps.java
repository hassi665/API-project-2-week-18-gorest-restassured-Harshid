package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import com.gorest.utils.TestsUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.hasValue;


public class UserSteps extends TestsUtils {
    @Step("Creating user with name: {0}, email: {1}, gender: {2} and status: {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 10b9a216b048c6920ce7fafae23400faf4d3bdb439b5d6595426b0c2cb706b61")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .body(userPojo)
                .when()
                .post("/users")
                .then().log().all().statusCode(201);
    }

    @Step("Getting all user records")
    public ValidatableResponse getUserInfoById(){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 10b9a216b048c6920ce7fafae23400faf4d3bdb439b5d6595426b0c2cb706b61")
                .header("Connection", "keep-alive")
                .when()
                .get(EndPoints.GET_ALL_USER)
                .then().log().all().statusCode(200);
    }

    @Step("Updating the user information with id: {0}, name: {1}, email: {2}, gender: {3} and status: {4}")
    public void updateUser(int id, String name, String email, String gender, String status){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        SerenityRest.given().log().all()
                .header("Authorization", "Bearer 10b9a216b048c6920ce7fafae23400faf4d3bdb439b5d6595426b0c2cb706b61")
                .header("Content-Type", "application/json")
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Deleting user information with id: {0}")
    public ValidatableResponse deleteUser(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 10b9a216b048c6920ce7fafae23400faf4d3bdb439b5d6595426b0c2cb706b61")
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().statusCode(204);
    }

    @Step("Getting user information with id: {0}")
    public ValidatableResponse getUserById(int id){
        return SerenityRest.given().log().all()
                .header("Authorization", "Bearer 10b9a216b048c6920ce7fafae23400faf4d3bdb439b5d6595426b0c2cb706b61")
                .header("Connection", "keep-alive")
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }




}
