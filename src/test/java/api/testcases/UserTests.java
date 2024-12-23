package api.testcases;

import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static api.endpoints.userEndPoint.*;

public class UserTests
{
    static Faker fake = new Faker();
    User testUser;
    String userName;
    @BeforeClass
    public void generateTestData()
    {
        int id =Integer.parseInt(fake.number().digit());
        userName = fake.name().username();
        String firstname = fake.name().firstName();
        String lastName = fake.name().lastName();
        String email = firstname+lastName+"@gmail.com";
        String password = "Password";
        String phoneNumber = fake.phoneNumber().cellPhone();
        int userStatus = 1;
        testUser = new User(id,userName,firstname,lastName,email,password,phoneNumber,userStatus);
    }

    @Test (priority = 1)
    public void createUser()
    {
        Response resp = postUser(testUser);
        Assert.assertEquals(resp.statusCode(),200);

    }
    @Test (priority = 2)
    public void getUserTest()
    {
        Response resp = getUser(userName);
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(),200);

    }
    @Test (priority = 3)
    public void updateUserTest()
    {
        String userNewtest= userName+"change";
        testUser.setUsername(userNewtest);
        Response resp = updateUser(testUser,userName);
        Assert.assertEquals(resp.statusCode(),200);
        //Get User after update
        Response respUpdateUser = getUser(userNewtest);
        Assert.assertEquals(respUpdateUser.statusCode(),200);
        System.out.println("Updated user details  are  : ");
        respUpdateUser.then().log().all();

    }
    @Test (priority = 4)
    public void deleteUserTest()
    {
        Response resp = deleteUser(testUser.getUsername());
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(),200);

    }

}
