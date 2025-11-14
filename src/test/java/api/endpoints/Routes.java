package api.endpoints;

public class Routes {
    public static String base_Url="https://abb.sharepoint.com/sites/SiteMaterialManagement/_api/web/lists";
    public static String get_Customer_Url=base_Url+"/GetByTitle('Customers')/items?$top=1000";
    public static String Create_Customer_Url=base_Url+ "/getbytitle('Customers')/items";
    public static String updateUrl = Routes.base_Url + "/getbytitle('Customers')/items(29)";


}
