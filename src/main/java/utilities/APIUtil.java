package utilities;

import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class APIUtil {
    public static String getString(Response response, String jsonPath) {
        return response.jsonPath().getString(jsonPath);
    }

    public static int getInt(Response response, String jsonPath) {
        return response.jsonPath().getInt(jsonPath);
    }

    public static boolean getBoolean(Response response, String jsonPath) {
        return response.jsonPath().getBoolean(jsonPath);
    }

    public static <T> List<T> getList(Response response, String jsonPath) {
        return response.jsonPath().getList(jsonPath);
    }

    public static <K, V> Map<K, V> getMap(Response response, String jsonPath) {
        return response.jsonPath().getMap(jsonPath);
    }
}
