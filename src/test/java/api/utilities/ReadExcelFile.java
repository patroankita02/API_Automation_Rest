package api.utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadExcelFile
{
   // public static String excelFilePath = "src/test/resources/TestData.xlsx";
    public static FileInputStream inputStream;
    public static XSSFWorkbook workBook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static String getCellValue(String fileName, String sheetname, int rowNo, int cellNo) throws IOException
    {
        inputStream = new FileInputStream(fileName);
        workBook = new XSSFWorkbook(inputStream);
        excelSheet= workBook.getSheet(sheetname);
        cell = workBook.getSheet(sheetname).getRow(rowNo).getCell(cellNo);
//        workBook.close();;
//        return cell.getStringCellValue();
        String cellValue;

        // Check the cell type
        switch (cell.getCellType()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    // Handle date values
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    // Handle numeric values
                    cellValue = String.valueOf(cell.getNumericCellValue());
                }
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case BLANK:
                cellValue = ""; // Handle blank cells
                break;
            default:
                throw new IllegalStateException("Unsupported cell type: " + cell.getCellType());
        }

        workBook.close();
        inputStream.close();
        return cellValue;
    }

    public static int getRowCount(String fileName, String sheetName) throws IOException {
        inputStream = new FileInputStream(fileName);
        workBook = new XSSFWorkbook(inputStream);
        excelSheet = workBook.getSheet(sheetName);
        int ttRows = excelSheet.getLastRowNum()+1;
        workBook.close();
        return ttRows;
    }

    public static int getColumnCount(String fileName, String sheetName) throws IOException {
        inputStream = new FileInputStream(fileName);
        workBook = new XSSFWorkbook(inputStream);
        excelSheet = workBook.getSheet(sheetName);
        int ttCell = excelSheet.getRow(0).getLastCellNum();
        workBook.close();
        return ttCell;
    }
}
