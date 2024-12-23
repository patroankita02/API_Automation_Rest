package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders
{
    @DataProvider(name ="AllData")
    public Object[][] AllDataProvider() throws IOException {
        //Read Data from excel
        String Filename = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
        int ttRowCount = ReadExcelFile.getRowCount(Filename,"TestData");
        int ttColCount = ReadExcelFile.getColumnCount(Filename,"TestData");
        String userData [][] = new String[ttRowCount-1][ttColCount];
        for(int rowNo =1; rowNo < ttRowCount;rowNo++ )
        {
            for(int colNo = 0; colNo<ttColCount;colNo++)
            {
                userData[rowNo-1][colNo] = ReadExcelFile.getCellValue(Filename,"TestData",rowNo,colNo);
            }
        }
        return userData;

    }

    @DataProvider(name ="UserNameData")
    public Object[][] userNameDataProvider () throws IOException {
        //Read Data from excel
        String Filename = System.getProperty("user.dir")+"//TestData//TestData.xlsx";
        String sheetName = "TestData";
        int ttRowCount = ReadExcelFile.getRowCount(Filename,"TestData");
        String [] userNames = new String[ttRowCount];
        for(int rowNo =1; rowNo < ttRowCount;rowNo++ )
        {
            userNames[rowNo-1] = ReadExcelFile.getCellValue(Filename,"TestData",rowNo,1);
        }
        Object[][] dataProviderArray = new Object[userNames.length][1];
        for (int i = 0; i < userNames.length; i++) {
            dataProviderArray[i][0] = userNames[i];
        }
        return dataProviderArray;
    }
}
