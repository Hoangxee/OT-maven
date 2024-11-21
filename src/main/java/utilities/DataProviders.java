package utilities;


import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "allData")
    public Object[][] getAllData() throws IOException{
        String pathFile = System.getProperty("user.dir") + "\\apiTestData\\Userdata.xlsx";
        String sheet = "Sheet1";

        ExcelUtil xl = new ExcelUtil(pathFile);

        int rownum = xl.getRowCount(sheet);
        int colcount = xl.getCellCount(sheet, 1);

        Object[][] apidata = new Object[rownum][colcount];

        for (int i=1;i<=rownum;i++){
            for (int j=0;j<colcount;j++){
                apidata[i-1][j] = xl.getCellData(sheet, i,j );
            }
        }
        return apidata;
    }

    @DataProvider(name = "UserNames")
    public Object[][] getUserNames() throws IOException{
        String pathFile = System.getProperty("user.dir") + "\\apiTestData\\Userdata.xlsx";
        String sheet = "Sheet1";

        ExcelUtil xl = new ExcelUtil(pathFile);

        int rownum = xl.getRowCount(sheet);

        Object[][] apidata = new Object[rownum][1];

        for (int i=1;i<=rownum;i++){
            apidata[i-1][0] = xl.getCellData(sheet, i, 1);
        }

        return apidata;
    }

}
