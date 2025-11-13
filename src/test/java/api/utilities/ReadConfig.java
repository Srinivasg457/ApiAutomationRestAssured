package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
    Properties properties;

    public ReadConfig() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/resources.properties");
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  String getABBSPCookie() {
        return properties.getProperty("ABBSPCookie");
    }
}
