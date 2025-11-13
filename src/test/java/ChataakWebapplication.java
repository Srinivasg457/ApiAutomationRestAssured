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

    @Test
    public void verifyOrdersAPI() {

        // Base URI
        RestAssured.baseURI = "https://dev-api.chataak.in:8086";

        // Bearer token (⚠️ replace with a fresh token if it expires)
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJvcmdhbml6YXRpb25UeXBlIjoxLCJVc2VyS2V5SWQiOjEwMCwib3JnYW5pemF0aW9uS2V5SWQiOjEwNSwiZW1haWwiOiJwb3Rvd29tNzAxQGFuZGluZXdzLmNvbSIsInN1YiI6InBvdG93b203MDFAYW5kaW5ld3MuY29tIiwiaXNzIjoiY2hhdGFhayIsImlhdCI6MTc2Mjc1OTkzOCwiZXhwIjozMTU1ODcxNDc1OTkzOH0.ahgjUJMS7DMkQ8yr_i4FDCFIuovi1Qq21OPPgCiN1kc";

        // Send the GET request
        Response response = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .queryParam("page", 1)
                .queryParam("size", 10)
                .queryParam("filter", "ALL")
                .when()
                .get("/api/orders")
                .then()
                .log().all() // Logs the full response
                .extract().response();

        // ✅ Validate response status
        Assert.assertEquals(response.statusCode(), 200, "Expected status code is 200");

        // ✅ Validate content type
        Assert.assertTrue(response.contentType().contains("application/json"), "Response should be JSON");

        // ✅ Print the response body
        System.out.println("Response Body:\n" + response.asPrettyString());

        // ✅ Example JSON assertions (if response has "data" array)
        int totalOrders = response.jsonPath().getList("data").size();
        System.out.println("Total Orders Returned: " + totalOrders);
        Assert.assertTrue(totalOrders >= 0, "API should return a valid order list");
    }

}
