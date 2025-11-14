package api.endpoints;

import api.payload.User;
import api.utilities.ReadConfig;
import api.utilities.SharePointAuth;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.path.json.JsonPath.config;
import static io.restassured.RestAssured.given;

public class userEndPoints {

    public static Response readCustomer() {
        // Create config object
        ReadConfig config = new ReadConfig();

        Response response = given()
                .header("Accept", "application/json;odata=verbose")
                .header("Cookie", config.getABBSPCookie())  // ✅ reads cookie from properties file
                .when()
                .get(Routes.get_Customer_Url)
                .then()
                .log().all()
                .extract().response();

        return response;
    }

//    public static Response createCustomer(User payload) {
//
//        String digest = SharePointAuth.getDigest();
//        ReadConfig config = new ReadConfig();
//
//        Response response = given()
//                .header("Accept", "application/json;odata=verbose")
//                .header("Content-Type", "application/json;odata=verbose")
//                .header("Cookie", config.getABBSPCookie())  // ✅ reads cookie from properties file
//                .header("X-RequestDigest", digest)
//                .body(payload)
//                .when()
//                .post(Routes.Create_Customer_Url)
//                .then()
//                .log().all()
//                .extract().response();
//
//        return response;
//    }
//
//    public static Response createCustomer(User payload) {
//
//        ReadConfig config = new ReadConfig();
//        String digest = SharePointAuth.getDigest();
//
//        Response response = given()
//                .header("Accept", "application/json;odata=verbose")
//                .header("Content-Type", "application/json;odata=verbose")
//                .header("Cookie", config.getSharePointCookies())
//                .header("X-RequestDigest", digest)
//                .body(payload)
//                .post(Routes.Create_Customer_Url)
//                .then().log().all()
//                .extract().response();
//
//        return response;
//    }


    public static Response createCustomer(User payload, String digest, String cookie, String listItemEntityType) {

        JSONObject body = new JSONObject();
        body.put("__metadata", new JSONObject().put("type", listItemEntityType));

        body.put("Title", payload.getName());
        body.put("Name", payload.getName());
        body.put("AbbProjectNumber", payload.getAbbProjectNumber());
        body.put("Address", payload.getAddress());
        body.put("City", payload.getCity());
        body.put("ContactPerson", payload.getContactPerson());
        body.put("CustomerCode", payload.getCustomerCode());
        body.put("Email", payload.getEmail());
        body.put("Phone", payload.getPhone());
        body.put("PostalCode", payload.getPostalCode());
        body.put("State", payload.getState());

        Response response = given()
                .header("Accept", "application/json;odata=verbose")
                .header("Content-Type", "application/json;odata=verbose")
                .header("X-RequestDigest", digest)
                .header("Cookie", cookie)
                .body(body.toString())
                .post(Routes.Create_Customer_Url)
                .then().log().all()
                .extract().response();

        return response;
    }






}