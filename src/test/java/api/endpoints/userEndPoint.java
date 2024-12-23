package api.endpoints;

import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static api.endpoints.Routes.*;
import static io.restassured.RestAssured.*;

public class userEndPoint
{
    //Create all methods for create, get, update and delete.
    public static Map<String,String> headers = new HashMap<>();

    public static Response postUser(User payload)
    {
        headers.put("Content-Type","application/json");
        Response response = given().headers(headers).body(payload).
        when().post(create_User);
//                then().assertThat().statusCode(HttpStatus.SC_OK);
        return response;
    }
   public static Response getUser(String userName)
   {
       headers.put("Content-Type","application/json");
       Response resp = given().headers(headers).
               when().get(get_User.replace("{username}",userName));
       return resp;
   }
    public static Response getUserWithPathParam(String userName)
    {
        headers.put("Content-Type","application/json");
        Response resp = given().headers(headers).pathParam("username",userName).
                when().get(get_User_PathParam);
        return resp;
    }
    public static Response updateUser(User payload, String newUserName)
    {
        headers.put("Content-Type","application/json");
        Response response = given().headers(headers).body(payload).
                when().put(update_User.replace("{username}",newUserName));
        return response;
    }
    public static Response updateUserPathParm(User payload, String newUserName)
    {
        headers.put("Content-Type","application/json");
        Response response = given().headers(headers).pathParam("username",newUserName).
                body(payload).
                when().put(create_User);
        return response;
    }

    public static Response deleteUser(String userName)
    {
        headers.put("Content-Type","application/json");
        Response resp = given().headers(headers).
                when().delete(delete_User.replace("{username}",userName));
        return resp;
    }
    public static Response deleteUserQueryParam(String userName)
    {
        headers.put("Content-Type","application/json");
        Response resp = given().headers(headers).pathParam("username",userName).
                when().delete(delete_User);
        return resp;
    }

}
