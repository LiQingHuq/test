package cn.scetc.work;

import cn.scetc.entity.Customer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Demo
 * @Description:
 * @Auther:
 * @Version: 1.0
 * @create 2019/3/14 11:15
 */
public class Demo {
    public static void main(String[] args) {
        List<Customer> customerList=new ArrayList<Customer>();
        try {
            List<String[]> list = readExcel("D:\\杭州云润\\唐山导入\\information.xlsx");

            for (String[] str:list) {
                for (String s:str) {
                    System.out.print(s+" ");
                }
                System.out.println("");
                Customer customer=new Customer();
                customer.setUserName(str[0]);
                customer.setUserState(str[1]);
                customer.setCeMing(str[2]);
                customer.setUserAddress(str[3]);
                customer.setUserTel(str[4]);
                customer.setUserIDcard(str[5]);
                customer.setIcardNumber(str[6]);
                customer.setUserType(str[7]);
                customer.setUserBalance(str[8]);
                customer.setUserWater(str[9]);
                customer.setMeterId(str[10]);
                customer.setMeterButton(str[11]);
                customer.setMeterState(str[12]);

                customerList.add(customer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("-------------------------------------------------------------------------");
        for (Customer customer:customerList) {
            System.out.println(" ("+customer.getUserName()+" ) ("+customer.getMeterId()+") ("+customer.getUserType()+") ("+customer.getMeterButton()+") ("+customer.getMeterState()+") ");
        }
    }

    public static List<String[]> readExcel(String file) throws IOException {
        //检查文件
        checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<String[]>();
        if(workbook != null){
            //sheet工作表读取
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum =10;rowNum <= lastRowNum;rowNum++){ //为了过滤到第一行因为我的第一行是数据库的列
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getLastCellNum();//为空列获取
//                    int lastCellNum = row.getPhysicalNumberOfCells();//为空列不获取
//                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    String[] cells = new String[row.getLastCellNum()];
                    //循环当前行
                    for(int cellNum =firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }
        }
        return list;
    }
    public static void checkFile(String file) throws IOException{
        //判断文件是否存在
        if(null == file){
            throw new FileNotFoundException("文件不存在！");
        }
        //判断文件是否是excel文件
        if(!file.endsWith("xls") && !file.endsWith("xlsx")){
            throw new IOException(file + "不是excel文件");
        }
    }
    public static Workbook getWorkBook(String  file) {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(file.endsWith("xls")){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(file.endsWith("xlsx")){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
//                cellValue = String.valueOf(cell.getCellFormula());
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
}

