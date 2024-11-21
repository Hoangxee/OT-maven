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

    public String getShop() {
        return shop;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public int getId() {
        return id;
    }

    String shop;
    String urlParams;
    int id;

}
