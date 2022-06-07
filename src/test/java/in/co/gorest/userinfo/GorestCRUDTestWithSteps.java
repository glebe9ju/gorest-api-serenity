package in.co.gorest.userinfo;


import in.co.gorest.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class GorestCRUDTestWithSteps {
    static String name = "Rechal Wool" + TestUtils.getRandomValue();
    static String email = TestUtils.getRandomValue() + "Rechal@gmail.com";
    static String gender = "Female";
    static String status = "active";
    static int userId;

    @Steps
    GorestSteps gorestSteps;

    @Title("This will create a new user")
    @Test
    public void test001(){gorestSteps.createUser(name,email,gender,status)
            .statusCode(2001)
            .log()
            .all();
    }
    @Title("Verify if the user was added to the application for name=Himadri Kocchar")
    @Test
    public void test002() {
        name = "Himadri Kocchar";
        HashMap<String, Object> userMap = gorestSteps.getUserInfoByFirstName(name);
        Assert.assertThat(userMap, hasValue(name));
        userId = (int) userMap.get("id");
        System.out.println(userId);
    }

    @Title("Update the user information and verify the updated information for ID=3866")
    @Test
    public void test003() {
        name = "Shama";
        gender="female";
        email="tenali@gmail.com";
        status="active";
        userId = 3866;
        gorestSteps.updateUser(userId,name,email,gender,status).statusCode(200).log().body().body("name", equalTo(name), "email", equalTo(email));
    }

    @Title("Delete the user and verify if the user is deleted! for ID=3868")
    @Test
    public void test004() {
        userId = 3868;
        gorestSteps.deleteUser(userId).statusCode(204).log().status();
        gorestSteps.getUserById(userId).statusCode(404).log().status();
    }



}
