package utilities;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelConfig {
    private Sheet currentSheet;
    private Map<String, Integer> columns;

    public static ExcelConfig getExcelData() {
        return new ExcelConfig();
    }

    public void switchToSheet(String filePath,String sheetName) {
        columns = new HashMap<String, Integer>();

        try (var workbooks = WorkbookFactory.create(new File(filePath))) {
            currentSheet = workbooks.getSheet(sheetName);
            currentSheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCellData(String columnName, int row) {
        var dataRow = currentSheet.getRow(row);
        return getCellDataAsString(dataRow.getCell(columns.get(columnName)));
    }

    private String getCellDataAsString(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case FORMULA:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    public String getCellDataFormula(String columnName, int row) {
        var dataRow = currentSheet.getRow(row);
        return getCellDataFormulaAsString(dataRow.getCell(columns.get(columnName)));
    }

    private String getCellDataFormulaAsString(Cell cell) {
        String a = null;
        if (cell.getCellType() == CellType.FORMULA) {
            switch (cell.getCachedFormulaResultType()) {
                case BOOLEAN:
                    a = String.valueOf(cell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    a = String.valueOf(cell.getNumericCellValue());
                    break;
                case STRING:
                    a = String.valueOf(cell.getRichStringCellValue());
                    break;
                default:
                    return "";
            }
        }
        return a;
    }

//    //jdk 17
//    private String getCellDataAsString(Cell cell) {
//        return switch (cell.getCellType()) {
//            case STRING -> cell.getStringCellValue();
//            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
//            case FORMULA -> cell.getStringCellValue();
//            default -> "";
//        };
//    }

}