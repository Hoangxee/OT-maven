package payload.apps.OT;


public class ListShipmentPayload {

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }


    public String getShop() {
        return shop;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public int getPage() {
        return page;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getFromDate() {
        return fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    String shop;
    String urlParams;
    int page;
    int perPage;
    String fromDate;
    String toDate;

}
