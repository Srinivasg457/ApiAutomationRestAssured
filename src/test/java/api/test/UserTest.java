package api.test;

import api.endpoints.userEndPoints;
import com.github.javafaker.Faker;
import api.payload.User;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTest {

    Faker faker;
    User userpayload;

    @BeforeClass
    public void setupData(){
    faker=new Faker();
    userpayload=new User();
    }
    @Test(priority=1)
    public void testgetCustomer(){
        Response response= userEndPoints.readCustomer();
    Assert.assertEquals(response.getStatusCode(),200);
    }


}
