package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestsUtils;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


@RunWith(SerenityRunner.class)
public class UserCURDTest extends TestBase {

    static int id ;
    static String name = "SRK" + getRandomValue();
    static String email = "SRK.Prime" + getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";

    @Steps
    UserSteps userSteps;

    @Title("This test will get user data")
    @Test
    public void test001() {
        userSteps.getUserInfoById();
    }

    @Title("This will create a new user")
    @Test
    public void test002() {
        ValidatableResponse getNewId = userSteps.createUser(name, email, gender, status);
        id = getNewId.extract().path("id");
        ValidatableResponse response = userSteps.getUserInfoById();
        ArrayList<?> userNewId = response.extract().path("id");
        Assert.assertTrue(userNewId.contains(id));
    }

    @Title("Verify if the user was added to the application")
    @Test
    public void test003() {
       userSteps.getUserById(id);
   //     Assert.assertThat(id, hasValue(id));

    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test004() {

        name = name + "update_123";
        userSteps.updateUser(id, name, email, gender, status);
        ValidatableResponse response = userSteps.getUserById(id).statusCode(200);
        HashMap<String, ?> userData = response.extract().path("");
        Assert.assertThat(userData, hasValue(name));
    }

    @Title("Delete the user and verify if the user is deleted!")
    @Test
    public void test005() {

        userSteps.deleteUser(id).statusCode(204);
    }

    @Title("Verify that the user is deleted or not ? ")
    @Test
    public void test006() {

        userSteps.getUserById(id).statusCode(404);
    }

}

