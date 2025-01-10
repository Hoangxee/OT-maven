package payload.apps.OT;


public class OrderLookupWidgetPayload {
    private String shop;
    private String urlParams;
    private TrackingForm trackingForm;
    private ButtonStyle buttonStyle;
    private String widgetTitle;
    private String widgetSize;
    private String alignment;
    private Boolean isShowResultInWidget;
    private Boolean isLoading;

    public void setShop(String shop) {
        this.shop = shop;
    }
    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }
    public void setTrackingForm(TrackingForm trackingForm) {
        this.trackingForm = trackingForm;
    }
    public void setButtonStyle(ButtonStyle buttonStyle) {
        this.buttonStyle = buttonStyle;
    }
    public void setWidgetTitle(String widgetTitle) {
        this.widgetTitle = widgetTitle;
    }
    public void setWidgetSize(String widgetSize) {
        this.widgetSize = widgetSize;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public void setIsShowResultInWidget(Boolean showResultInWidget) {
        isShowResultInWidget = showResultInWidget;
    }
    public void setIsLoading(Boolean loading) {
        isLoading = loading;
    }
    public String getShop() {
        return shop;
    }
    public String getUrlParams() {
        return urlParams;
    }
    public TrackingForm getTrackingForm() {
        return trackingForm;
    }
    public ButtonStyle getButtonStyle() {
        return buttonStyle;
    }
    public String getWidgetTitle() {
        return widgetTitle;
    }
    public String getWidgetSize() {
        return widgetSize;
    }
    public String getAlignment() {
        return alignment;
    }
    public Boolean getIsShowResultInWidget() {
        return isShowResultInWidget;
    }
    public Boolean getIsLoading() {
        return isLoading;
    }

    public static class TrackingForm {
        private boolean isRequireEmail;
        private String typeMethodTracking;

        public void setIsRequireEmail(boolean requireEmail) {
            isRequireEmail = requireEmail;
        }
        public void setTypeMethodTracking(String typeMethodTracking) {
            this.typeMethodTracking = typeMethodTracking;
        }
        public boolean getIsRequireEmail() {
            return isRequireEmail;
        }
        public String getTypeMethodTracking() {
            return typeMethodTracking;
        }
     }
    public static class ButtonStyle {
        private String label;
        private String background;
        private String text;
        private String shape;
        public void setLabel(String label) {
            this.label = label;
        }
        public void setBackground(String background) {
            this.background = background;
        }
        public void setText(String text) {
            this.text = text;
        }
        public void setShape(String shape) {
            this.shape = shape;
        }
        public String getLabel() {
            return label;
        }
        public String getBackground() {
            return background;
        }
        public String getText() {
            return text;
        }
        public String getShape() {
            return shape;
        }
    }
}

