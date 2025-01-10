package payload.apps.OT;


public class TrackingLinkSetupPayload {
    String shop;
    String urlParams;
    int replaceCourierLink;
    int addLinkToOrder;
    String linkDescription;

    public void setShop(String shop) {
        this.shop = shop;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public void setReplaceCourierLink(int replaceCourierLink) {
        this.replaceCourierLink = replaceCourierLink;
    }

    public void setAddLinkToOrder(int addLinkToOrder) {
        this.addLinkToOrder = addLinkToOrder;
    }

    public void setLinkDescription(String linkDescription) {
        this.linkDescription = linkDescription;
    }

    public String getShop() {
        return shop;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public int getReplaceCourierLink() {
        return replaceCourierLink;
    }

    public int getAddLinkToOrder() {
        return addLinkToOrder;
    }

    public String getLinkDescription() {
        return linkDescription;
    }


}
