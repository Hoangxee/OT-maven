package payload.apps.OT;

public class DetailShipmentPayload {

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public void setId(int id) {
        this.id = id;
    }

    String shop;
    String urlParams;
    int id;

}
