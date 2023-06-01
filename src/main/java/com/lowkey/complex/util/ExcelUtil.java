package com.lowkey.complex.util;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author yuanjifan
 * @description
 * @date 2022年05月19日 16:17
 */
public class ExcelUtil {
    public static void main(String[] args) throws IOException {
        getFileFontToRed1();
    }


   /* public static void getFileFontToRed() throws IOException {


        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("自定义单元格部分内容颜色");

        HSSFRow row = sheet.createRow(5);

        HSSFCell cell = row.createCell(5);

        HSSFCellStyle cellStyle = workbook.createCellStyle();

        HSSFFont font = workbook.createFont();

        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getColor().getIndex());

        HSSFFont font2 = workbook.createFont();

        font2.setColor(HSSFColor.HSSFColorPredefined.RED.getColor().getIndex());

        font2.setBold(true);

        cellStyle.setFont(font);

        cell.setCellStyle(cellStyle);

        String txt = "abcdefghijklmnopqrsREDtuvwxyzredssssssssssss";

        HSSFRichTextString text = new HSSFRichTextString(txt);

        text.applyFont(txt.indexOf("RED"), txt.indexOf("RED") + 3, font2);

        cell.setCellValue(text);

        sheet.autoSizeColumn(5, true);

        File file = new File("C:\\Users\\yuanj\\Desktop\\我要变成红色的字体.xls");

        if (file.exists()) {

            file.delete();

        }

        file.createNewFile();

        workbook.write(new FileOutputStream(file));


    }*/

    public static void getFileFontToRed1() throws IOException {
        SXSSFWorkbook workbook = new SXSSFWorkbook();
        SXSSFSheet sheet = workbook.createSheet("自定义单元格部分内容颜色");
        SXSSFRow row = sheet.createRow(5);
        SXSSFCell cell = row.createCell(5);

        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();

        Font font = workbook.createFont();

        font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getColor().getIndex());

        Font font2 = workbook.createFont();


        font2.setBold(true);

        cellStyle.setFont(font);
        String text = "abcdefghijklmnopqrsREDtuvwxyzredssssssssssss";
        cell.setCellValue(text);
        RichTextString richStringCellValue = cell.getRichStringCellValue();
        cell.setCellStyle(cellStyle);


        RichTextString richTextString = creationHelper.createRichTextString("RED");
        XSSFRichTextString xssfRichTextString = new XSSFRichTextString(text);

        richStringCellValue.applyFont(text.indexOf("RED"), text.indexOf("RED") + 3,font2);




        File file = new File("C:\\Users\\yuanj\\Desktop\\我要变成红色的字体.xls");

        if (file.exists()) {

            file.delete();

        }

        file.createNewFile();

        workbook.write(new FileOutputStream(file));


    }
}
