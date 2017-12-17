package com.qwm.bos.test;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: qiwenming(杞文明)
 * @date: 17/12/17 下午12:29
 * @className: POITest
 * @description:
 */
public class POITest {

    /**
     * 使用POI解析Excel文件
     * @throws IOException
     */
    //@Test
    public void test1() throws IOException {
        //文件路径
        String filePath = "/Volumes/wiming/learn/maven/bos/bos-parent/files/区域导入测试数据.xls";
        //创建一个工作簿(包装了一个Excel文件对象)
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File(filePath)));
        //读取文件中的第一个Sheet标签页  可以通过 Sheet名称或者索引获取到.
        HSSFSheet hssfSheet = workbook.getSheetAt(0);
        //遍历便签也中的每一行
        for (Row row: hssfSheet) {
            //第一行为标题,我们可以不遍历第一行
            if(row.getRowNum()==0)
                continue;
            //遍历每一行的单元格
            for (Cell cell: row) {
                //获取cell的值
                String value = cell.getStringCellValue();
                //加个制表符,显示更好看一点
                System.out.print(cell+"\t");
            }
            System.out.println();
        }
    }
}
