import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChataakWebapplication {

    @Test
    public void getStoreProducts() {

        // Base URI
        RestAssured.baseURI = "https://dev-api.chataak.in:8086";

        // Bearer token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJvcmdhbml6YXRpb25UeXBlIjoxLCJVc2VyS2V5SWQiOjEwMCwib3JnYW5pemF0aW9uS2V5SWQiOjEwNSwiZW1haWwiOiJwb3Rvd29tNzAxQGFuZGluZXdzLmNvbSIsInN1YiI6InBvdG93b203MDFAYW5kaW5ld3MuY29tIiwiaXNzIjoiY2hhdGFhayIsImlhdCI6MTc2Mjc1OTkzOCwiZXhwIjozMTU1ODcxNDc1OTkzOH0.ahgjUJMS7DMkQ8yr_i4FDCFIuovi1Qq21OPPgCiN1kc";

        // Perform GET request
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .when()
                .get("/api/products/store")
                .then()
                .log().all() // log response
                .extract().response();

        // Validate response
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");

        // Example validation on body
        String body = response.asString();
        System.out.println("Response Body:\n" + body);

        // Optional: Validate a JSON field
        String firstProductName = response.jsonPath().getString("data[0].name");
        System.out.println("First Product Name: " + firstProductName);
    }


    @Test
    public void verifyOrganizationProductsAPI() {

        // Base URL
        RestAssured.baseURI = "https://dev-api.chataak.in:8086";

        // Bearer token
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJvcmdhbml6YXRpb25UeXBlIjoxLCJVc2VyS2V5SWQiOjEwMCwib3JnYW5pemF0aW9uS2V5SWQiOjEwNSwiZW1haWwiOiJwb3Rvd29tNzAxQGFuZGluZXdzLmNvbSIsInN1YiI6InBvdG93b203MDFAYW5kaW5ld3MuY29tIiwiaXNzIjoiY2hhdGFhayIsImlhdCI6MTc2Mjc1OTkzOCwiZXhwIjozMTU1ODcxNDc1OTkzOH0.ahgjUJMS7DMkQ8yr_i4FDCFIuovi1Qq21OPPgCiN1kc";

        // Send GET Request
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .queryParam("page", 1)
                .queryParam("size", 10)
                .queryParam("sortField", "")
                .queryParam("sortOrder", "")
                .queryParam("search", "")
                .queryParam("isConfigured", "")
                .when()
                .get("/api/products/organization")
                .then()
                .log().all() // log response
                .extract().response();

        // ✅ Validate response code
        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200 OK");

        // ✅ Validate content type
        Assert.assertTrue(response.contentType().contains("application/json"), "Response is JSON");

        // ✅ Print response body
        System.out.println("Response Body:\n" + response.asPrettyString());

        // ✅ Example JSON field validation
        int totalItems = response.jsonPath().getInt("data.size()");
        System.out.println("Number of products in response: " + totalItems);
        Assert.assertTrue(totalItems >= 0, "There should be at least 0 products returned");
    }


}
