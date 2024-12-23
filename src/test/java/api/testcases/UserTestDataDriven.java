package api.testcases;

import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static api.endpoints.userEndPoint.postUser;

public class UserTestDataDriven
{
    @Test(priority = 1,dataProvider = "AllData",dataProviderClass = DataProviders.class)
    public void createUser(String id, String username, String firstName, String lastName, String email, String password, String phone, String userStatus )
    {
        User testUser =  new User();
        testUser.setId((int)Double.parseDouble(id));
        testUser.setUsername(username);
        testUser.setFirstName(firstName);
        testUser.setLastName(lastName);
        testUser.setEmail(email);
        testUser.setPassword(password);
        testUser.setPhone(phone);
        testUser.setUserStatus((int)Double.parseDouble(userStatus));
        Response resp = postUser(testUser);
        Assert.assertEquals(resp.statusCode(),200);

    }
}
