package api.utilities;

import io.restassured.response.Response;

import static api.endpoints.Routes.base_Url;
import static io.restassured.RestAssured.given;


public class SharePointAuth {

    public static String getDigest(String cookie) {

        ReadConfig config = new ReadConfig();

        Response response = given()
                .header("Accept", "application/json;odata=verbose")
                .header("Content-Type", "application/json")
                .header("Cookie", config.getSharePointCookies())
                .post("https://abb.sharepoint.com/sites/SiteMaterialManagement/_api/contextinfo")
                .then().log().all()
                .extract().response();

        System.out.println("Digest Status = " + response.statusCode());

        return response.jsonPath()
                .getString("d.GetContextWebInformation.FormDigestValue");
    }


    // ------- GET List Item Entity Type (important for POST item) --------
//    public static String getListItemEntityTypeFullName(String cookie, String listName) {
//
//        Response response = given()
//                .header("Accept", "application/json;odata=verbose")
//                .header("Cookie", cookie)
//                .get("https://abb.sharepoint.com/sites/SiteMaterialManagement/_api/web/lists/getbytitle('" + listName + "')?$select=ListItemEntityTypeFullName")
//                .then()
//                .extract().response();
//
//        return response.jsonPath().getString("d.ListItemEntityTypeFullName");
//    }



    public static String getListItemEntityTypeFullName(String cookie, String listName) {
        String url = base_Url + "/getByTitle('" + listName + "')/ListItemEntityTypeFullName";

        Response response = given()
                .header("Cookie", cookie)
                .header("Accept", "application/json;odata=verbose")
                .get(url);

        System.out.println("EntityType Status: " + response.getStatusCode());
        System.out.println(response.getBody().asString());

        if(response.getStatusCode() == 200){
            return response.jsonPath().getString("d.ListItemEntityTypeFullName");
        } else {
            throw new RuntimeException("Failed to get entity type. Check list name.");
        }
    }

}
