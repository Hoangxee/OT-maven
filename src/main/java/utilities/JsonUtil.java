package utilities;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static <T> T getValueByKey(Response response, String jsonPath) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.get(jsonPath);
    }

    public static <T> List<T> getListValueByKey(Response response, String jsonPath) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.getList(jsonPath);
    }

    public static List<Map<String, Object>> getListObjectByKey(Response response, String jsonPath) {
        JsonPath jsonPathEvaluator = response.jsonPath();
        return jsonPathEvaluator.getList(jsonPath);
    }

    public static Map<String, Object> getObjectByKeyValue(Response response, String jsonPath, String key, String value) {
        List<Map<String, Object>> shipments = getListObjectByKey(response, jsonPath);

        for (Map<String, Object> shipment : shipments) {
            if (value.equals(shipment.get(key))) {
                return shipment;
            }
        }
        System.out.println(value + " not found.");
        return null;
    }
}
