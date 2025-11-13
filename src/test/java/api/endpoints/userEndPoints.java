package api.endpoints;

import api.utilities.ReadConfig;
import io.restassured.response.Response;
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
}