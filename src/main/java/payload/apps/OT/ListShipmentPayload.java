package payload.apps.OT;

import org.joda.time.DateTime;

import java.time.ZonedDateTime;

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

    String shop;
    String urlParams;
    int page;
    int perPage;
    String fromDate;
    String toDate;


}
