package api.test;

import endPoints.apps.ST.ListShipmentEndpoints;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.apps.OT.ListShipmentPayload;
import utilities.JsonUtil;

import java.util.List;
import java.util.Map;


public class ListShipmentTestAPI {
    @BeforeClass
    public void setupData(){
        listShipmentPayload = new ListShipmentPayload();

        listShipmentPayload.setShop(shop);
        listShipmentPayload.setUrlParams(urlParams);
        listShipmentPayload.setPage(page);
        listShipmentPayload.setPerPage(perPage);
        listShipmentPayload.setFromDate(fromDate);
        listShipmentPayload.setToDate(toDate);
    }

    @Test
    public void TC01_getListShipment(){
        Response response = ListShipmentEndpoints.getListShipment(shop, urlParams,page,perPage,fromDate, toDate);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        int stringFieldValue = JsonUtil.getValueByKey(response, "data.currentPage");
        System.out.println("value by currentPage: "+stringFieldValue);

        List<String> orderIds = JsonUtil.getListValueByKey(response,"data.shipments.orderId");
        System.out.println("list orderId in shipments: " + orderIds);

        List<Map<String, Object>> shipments = JsonUtil.getListObjectByKey(response,"data.shipments");
        System.out.println("all object in shipments: " + shipments);
        System.out.println("orderId in shipments: " + shipments.get(2).get("orderId"));

        Map<String, Object> objectShipment = JsonUtil.getObjectByKeyValue(response,"data.shipments","orderId","5375017320548");
        System.out.println("1 object in shipments: "+objectShipment);
        System.out.println("orderId in shipments: "+objectShipment.get("orderId"));

//        String specificOrderId = response.jsonPath().getString("data.shipments.find { it.orderId == '5375017320548' }.orderId");
//        System.out.println("Specific Order ID: " + specificOrderId);

    }

    ListShipmentPayload listShipmentPayload;
    String shop = "hoangxe-test-3.myshopify.com";
    String urlParams = "by-passs";
    int page = 1;
    int perPage = 5;
    String fromDate = "2024-10-07T08:21:56.271Z";
    String  toDate = "2024-11-05T08:21:56.271Z";
}
