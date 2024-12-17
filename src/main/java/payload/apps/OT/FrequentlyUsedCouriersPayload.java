package payload.apps.OT;

import java.util.List;

public class FrequentlyUsedCouriersPayload {

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public void setPayload(List<String> payload) {
        this.payload = payload;
    }

    public String getShop() {
        return shop;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public List<String> getPayload() {
        return payload;
    }

    String shop;
    String urlParams;
    List<String> payload;

}
