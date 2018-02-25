package com.zl.checkapi.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TemplateToPdf {

    public static void main(String[] args) throws IOException, DocumentException {

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\lenovo\\Desktop\\test.pdf"));

        document.open();
        //中文字体,解决中文不能显示问题
        BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
        Font baseFont = new Font(bfChinese);

        Paragraph paragraph = new Paragraph("今天天气相当不错!", baseFont);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        Chunk chunk = new Chunk("可以认真的工作一段时间了!", baseFont);
        paragraph.add(chunk);
        document.add(paragraph);

        //表格
        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100); // 宽度100%填充
        table.setSpacingBefore(10f); // 前间距
        table.setSpacingAfter(10f); // 后间距
        ArrayList<PdfPRow> listRow = table.getRows();
        //设置列宽
        float[] columnWidths = { 1f, 1f, 1f , 1f };
        table.setWidths(columnWidths);
        //行1
        PdfPCell cells1[]= new PdfPCell[4];
        PdfPRow row1 = new PdfPRow(cells1);
        //单元格
        cells1[0] = new PdfPCell(new Phrase("身份证", baseFont));//单元格内容
//        cells1[0].setHorizontalAlignment(Element.ALIGN_CENTER);//水平居中
//        cells1[0].setVerticalAlignment(Element.ALIGN_MIDDLE);//垂直居中
        cells1[1] = new PdfPCell(new Phrase("姓名", baseFont));
        cells1[2] = new PdfPCell(new Phrase("手机号", baseFont));
        cells1[3] = new PdfPCell(new Phrase("地址", baseFont));

        //行2
        PdfPCell cells2[]= new PdfPCell[4];
        PdfPRow row2 = new PdfPRow(cells2);
        cells2[0] = new PdfPCell(new Phrase("12345678965487"));
        cells2[1] = new PdfPCell(new Phrase("张三", baseFont));
        cells2[2] = new PdfPCell(new Phrase("13227136694"));
        cells2[3] = new PdfPCell(new Phrase("广东省深圳市", baseFont));

        //行3
        PdfPCell cells3[]= new PdfPCell[4];
        PdfPRow row3 = new PdfPRow(cells3);
        cells3[0] = new PdfPCell(new Phrase("12345678965487"));
        cells3[1] = new PdfPCell(new Phrase("李四", baseFont));
        cells3[2] = new PdfPCell(new Phrase("13227136694"));
        cells3[3] = new PdfPCell(new Phrase("广东省深圳市", baseFont));
        //把第一行添加到集合
        listRow.add(row1);
        //把第二行添加到集合
        listRow.add(row2);
        //把第三行添加到集合
        listRow.add(row3);
        //把表格添加到文件中
        document.add(table);
        //设置属性
        //标题
        document.addTitle("this is a title");
        //作者
        document.addAuthor("H__D");
        //主题
        document.addSubject("this is subject");
        //关键字
        document.addKeywords("Keywords");
        //创建时间
        document.addCreationDate();
        //应用程序
        document.addCreator("hd.com");

        document.close();

    }
}
