package api.test;

import api.endpoints.userEndPoints;
import api.utilities.ReadConfig;
import api.utilities.SharePointAuth;
import com.github.javafaker.Faker;
import api.payload.User;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

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

    @Test(priority = 2)
    public void testCreateCustomer() {

        ReadConfig config = new ReadConfig();
        String cookie = config.getSharePointCookies();

        String digest = SharePointAuth.getDigest(cookie);
        String entityType = SharePointAuth.getListItemEntityTypeFullName(cookie, "Customers");

        userpayload.setAbbProjectNumber("ABB-" + faker.number().randomDigitNotZero());
        userpayload.setAddress(faker.address().fullAddress());
        userpayload.setCity(faker.address().cityName());
        userpayload.setContactPerson(faker.name().fullName());
        userpayload.setCustomerCode("CUST-" + faker.number().numberBetween(100, 999));
        userpayload.setEmail(faker.internet().emailAddress());
        userpayload.setName(faker.name().firstName());
        userpayload.setPhone(faker.phoneNumber().subscriberNumber(10));
        userpayload.setPostalCode(faker.address().zipCode());
        userpayload.setState(faker.address().state());

        Response response = userEndPoints.createCustomer(
                userpayload,
                digest,
                cookie,
                entityType
        );

        Assert.assertEquals(response.getStatusCode(), 201);
    }









}
