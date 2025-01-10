package commons.constant;

import java.io.File;

public class GlobalConstants {
     public static final String SHOPIFY_ADMIN_URL = "https://admin.shopify.com/store/returns-drive/";
     public static final String SHOPIFY_ADMIN_EMAIL = "hoangnh@omegatheme.com";
     public static final String SHOPIFY_ADMIN_PASSWORD = "12345678a";
     public static final String SHOPIFY_APP_STORE_URL = "https://apps.shopify.com/";
     public static final String SHOPIFY_STORE_FRONT_URL = "https://returns-drive.myshopify.com";
     public static final String ORDER_TRACKING_STORE_FRONT_URL = "https://returns-drive.myshopify.com/apps/order-tracking";
     public static final String SHOPIFY_STORE_FRONT_PASSWORD = "12345678a";
     public static final String SHOPIFY_STORE_FRONT_PRODUCT = "Ae-1000W-1Bvef_Test 1";
     public static final String SHOPIFY_ADMIN_2_URL = "https://admin.shopify.com/store/p1chd0-bm/";
     public static final String SHOPIFY_STORE_FRONT_2_URL = "https://p1chd0-bm.myshopify.com";
     public static final String PROJECT_PATH = System.getProperty("user.dir");
     public static final String OS_NAME = System.getProperty("os.name");
     public static final String TEST_RESOURCE_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources";
     public static final String UPLOAD_FILE = TEST_RESOURCE_PATH + File.separator + "data" + File.separator + "uploadFiles" + File.separator;
     public static final String DOWNLOAD_FILE = TEST_RESOURCE_PATH + File.separator + "data" + File.separator + "downloadFiles" + File.separator;
     public static final long SHORT_TIMEOUT = 5;
     public static final long MEDIUM_TIMEOUT = 15;
     public static final long LONG_TIMEOUT = 60;
     public static final String APPROVE_SUBSCRIPTION_TITLE_PAGE = "Approve subscription";
     public static final String SHOP_API = "returns-drive.myshopify.com";
     public static final String URL_PARAM_API = "by-passs";
}
