

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static java.awt.SystemColor.window;

public class ActivityLogAPITest {

    static String cookie = "__stripe_mid=208aa61b-537c-4109-9ffb-932e97ad2b259fbcc4; " +
            "__stripe_sid=073b470d-31a8-49c1-aed4-ba4b163fc12a9c778f; " +
            "ci_session=k147c44j6ni48ou9vv2cbd5d1lcol66p";

// Define static cookie variables for different users
    static String Sabeer_cookie = "ci_session=e3obcdsqts2h09q21ht8hq03ojvom869; expires=Tue, 13 May 2025 12:05:01 GMT; Max-Age=7200; path=/; HttpOnly";

    public static void main(String[] args) {

        RestAssured.baseURI = "https://work-room.io";

        // Change employee_id, user_id, and date to test for different users
        String employeeId = "3";
        String userId = "4";
        String date = "2025-05-13";

        getActivityLog(employeeId, userId, date);
        getUserScreenshots(employeeId, userId, date);
    }

    public static void getActivityLog(String employeeId, String userId, String date) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("employee_id", employeeId)
                .header("user_id", userId)
                .header("cookie", cookie)
                .when()
                .get("/admin/Activity_logs/get_activity?date=" + date);

        System.out.println("\n=== Activity Log API ===");
        System.out.println("Employee ID: " + employeeId + ", User ID: " + userId);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.prettyPrint());
    }

    public static void getUserScreenshots(String employeeId, String userId, String date) {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("employee_id", employeeId)
                .header("user_id", userId)
                .header("cookie", cookie)
                .when()
                .get("/admin/ScreenshotController/get_user_screenshots?date=" + date);

        System.out.println("\n=== Screenshot API ===");
        System.out.println("Employee ID: " + employeeId + ", User ID: " + userId);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.prettyPrint());
    }

    
}
