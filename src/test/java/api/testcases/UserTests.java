package api.testcases;

import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import static api.endpoints.userEndPoint.*;

public class UserTests
{
    static Faker fake = new Faker();
    User testUser;
    String userName;
    private static Logger logger = LogManager.getLogger(UserTests.class);


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
        System.setProperty("log4j2.debug", "true");
        Configurator.initialize(null, "C:\\AnkitaWorkspace\\API_Framework\\src\\test\\resources\\log4j.properties");
       // logger = LogManager.getLogger("logs");
    }

    @Test (priority = 1)
    public void createUser()
    {
        Response resp = postUser(testUser);
        Assert.assertEquals(resp.statusCode(),200);
        logger.info("Create user logged");

    }
    @Test (priority = 2)
    public void getUserTest()
    {
        Response resp = getUser(userName);
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(),200);
        logger.info("Get user logged");

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
        logger.info("Update user logged");

    }
    @Test (priority = 4)
    public void deleteUserTest()
    {
        Response resp = deleteUser(testUser.getUsername());
        resp.then().log().all();
        Assert.assertEquals(resp.statusCode(),200);
        logger.info("Delete user logged");

    }

}
