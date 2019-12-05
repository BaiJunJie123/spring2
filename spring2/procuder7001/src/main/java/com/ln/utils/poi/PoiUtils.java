package com.ln.utils.poi;

import com.sun.deploy.net.proxy.pac.PACFunctions;
import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.util.diff.myers.PathNode;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.Iterator;

/**
 * @author 白俊杰
 * @Date 2019/12/1
 * @Description
 **/
public class PoiUtils {
    public static void main(String[] args) {
        //String zhi = readXlsx("C:\\Users\\26044\\Desktop\\446.xlsx");
        createWord("C:\\Users\\26044\\Desktop\\1234.docx");
        //System.out.println(zhi);
    }

    //读doc
    public static String readDoc(String pathName){

        FileInputStream in;
        String text = "";
        try {
            in  = new FileInputStream(pathName);
            try {
                XWPFWordExtractor extractor = new XWPFWordExtractor(OPCPackage.open(in));
                text = extractor.getText();
            } catch (XmlException e) {
                e.printStackTrace();
            } catch (OpenXML4JException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return text;
    }
    //读docx
    public static String readDocx(String pathName){
        String text = "";
        try {
            InputStream is = new FileInputStream(pathName);
            try {
                XWPFDocument doc = new XWPFDocument(is);
                XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
                text = extractor.getText();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return text;
    }
    // 读xls
    public static  String readXls(String pathName){
        String builder = null;
        try {
            FileInputStream is = new FileInputStream(pathName);
            try {
                HSSFWorkbook book = new HSSFWorkbook(is);
                HSSFSheet sheet0 = book.getSheetAt(0); // 获取第一个sheet
                Iterator rowiterator = sheet0.iterator();
                while (rowiterator.hasNext()){
                    HSSFRow row = (HSSFRow) rowiterator.next();
                    Iterator celliterator = row.cellIterator();
                    while (celliterator.hasNext()){
                        HSSFCell cell = (HSSFCell) celliterator.next();
                        if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING) builder+=(cell.getStringCellValue()+"\t");
                        else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) builder+=(cell.getNumericCellValue()+"\t");
                        else if(cell.getCellType()==XSSFCell.CELL_TYPE_FORMULA) builder+=(cell.getCellFormula()+"\t");
                    }

                    builder+=("\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder;
    }

    //读xlsx
    public static String readXlsx(String pathName){
        // 使用springbuffer 会报空指针
        String builder = null;
        try {
            OPCPackage pkg = OPCPackage.open(pathName);
            try {
                XSSFWorkbook excel = new XSSFWorkbook(pkg);
                //获取第一个sheel
                XSSFSheet sheet = excel.getSheetAt(0);
                Iterator rowiterator = sheet.iterator();
                while (rowiterator.hasNext()){
                    XSSFRow row=(XSSFRow) rowiterator.next();
                  Iterator cellit =  row.cellIterator();
                  while (cellit.hasNext()){
                      XSSFCell cell=(XSSFCell) cellit.next();
                      if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING) builder+=(cell.getStringCellValue()+"\t");
                      else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC) builder+=(cell.getNumericCellValue()+"\t");
                      else if(cell.getCellType()==XSSFCell.CELL_TYPE_FORMULA) builder+=(cell.getCellFormula()+"\t");
                  }

                  builder+=("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        return builder;
    }
    //创建Excel
    public  static void createExcel(String path){
        String datas = path.split("\\.")[1];
        if(datas == "xls"){

        }else if(datas.equals("xlsx")){
            Workbook hssfWorkbook = new XSSFWorkbook(); //创建excel
            XSSFSheet sheet = (XSSFSheet) hssfWorkbook.createSheet("Test"); //创建sheet
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("aaa");
            row.createCell(1).setCellValue("bbb");
            row.createCell(2).setCellValue("ccc");
            row.createCell(3).setCellValue("ddd");


            try {
                FileOutputStream outputStream = new FileOutputStream(path);
                try {
                    hssfWorkbook.write(outputStream);
                    hssfWorkbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    //创建word
    public  static  void  createWord(String pathName){
        XWPFDocument doc = new XWPFDocument();// 创建Word文件
        XWPFParagraph p = doc.createParagraph();// 新建一个段落
        p.setAlignment(ParagraphAlignment.CENTER);// 设置段落的对齐方式
        p.setBorderBottom(Borders.DOUBLE);//设置下边框
        p.setBorderTop(Borders.DOUBLE);//设置上边框
        p.setBorderRight(Borders.DOUBLE);//设置右边框
        p.setBorderLeft(Borders.DOUBLE);//设置左边框
        XWPFRun r = p.createRun();//创建段落文本
        r.setText("POI创建的Word段落文本");
        r.setBold(true);//设置为粗体
        r.setColor("FF0000");//设置颜色
        try {
            FileOutputStream out = new FileOutputStream(pathName);
            try {
                doc.write(out);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
