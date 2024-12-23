package api.endpoints;

public class Routes
{
    public static String base_url = "https://petstore.swagger.io/v2";
    public static String create_User =base_url+"/user";
    public String create_ArrayofUser =base_url+"/user/createWithArray";
    public String create_UserWithList =base_url+"/user/createWithList";
    public static String get_User =base_url+"/user/{username}";
    public static String get_User_PathParam =base_url+"/user";
    public String get_User_Login =base_url+"user/login?username={username}&password={password}";
    public static String update_User =base_url+"/user/{username}";
    public static String delete_User =base_url+"/user/{username}";

}
