package com.example.ass_j5.excel;

import com.example.ass_j5.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//lưu ra excel
public class WriteExcelExample {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_ACCOUNT = 1;
    public static final int COLUMN_INDEX_CATE = 2;
    public static final int COLUMN_INDEX_DATE = 3;
    public static final int COLUMN_INDEX_IMAGE = 4;
    public static final int COLUMN_INDEX_NAME = 5;
    public static final int COLUMN_INDEX_TITLE = 6;
    public static final int COLUMN_INDEX_PRICE = 7;
    public static final int COLUMN_INDEX_UPDELETE = 8;
    public static final int COLUMN_INDEX_DATEUP = 9;
    private static CellStyle cellStyleFormatNumber = null;

//    public static void main(String[] args) throws IOException {
//        final List<Product> books = getBooks();
//        final String excelFilePath = "products.xlsx";
//        writeExcel(books, excelFilePath);
//    }

    public static void writeExcel(List<Product> products, String excelFilePath) throws IOException {

        Workbook workbook = getWorkbook(excelFilePath);


        Sheet sheet = workbook.createSheet("Books");  //Tạo trang tính với tên trang tính
        int rowIndex = 0;


        writeHeader(sheet, rowIndex);


        rowIndex++;
        for (Product product : products) {

            Row row = sheet.createRow(rowIndex);

            writeBook(product, row);
            rowIndex++;
        }


        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);


        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }


    public static List<Product> getBooks() {
        List<Product> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ass_j5", "root", "16042002");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select id,account,cate,date,image,name,price,title,updelete,date_up from product ");
            while (rs.next()) {
                list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getString(8), rs.getInt(9), rs.getString(10)));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;

    }

    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }


    private static void writeHeader(Sheet sheet, int rowIndex) {

        CellStyle cellStyle = createStyleForHeader(sheet);


        Row row = sheet.createRow(rowIndex);


        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_ACCOUNT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("account");

        cell = row.createCell(COLUMN_INDEX_CATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("cate");

        cell = row.createCell(COLUMN_INDEX_DATE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ngay tao");

        cell = row.createCell(COLUMN_INDEX_IMAGE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("image");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("name");

        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Title");

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Price");

        cell = row.createCell(COLUMN_INDEX_UPDELETE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("trang thai");

        cell = row.createCell(COLUMN_INDEX_DATEUP);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ngay sua");

    }


    private static void writeBook(Product product, Row row) {
        if (cellStyleFormatNumber == null) {

            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");

            Workbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(product.getId());

        cell = row.createCell(COLUMN_INDEX_ACCOUNT);
        cell.setCellValue(product.getAccount());

        cell = row.createCell(COLUMN_INDEX_CATE);
        cell.setCellValue(product.getCate());

        cell = row.createCell(COLUMN_INDEX_DATE);
        cell.setCellValue(String.valueOf(product.getCreateDate()));

        cell = row.createCell(COLUMN_INDEX_IMAGE);
        cell.setCellValue(product.getImage());

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(product.getName());

        cell = row.createCell(COLUMN_INDEX_TITLE);
        cell.setCellValue(product.getTitle());

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellValue(product.getPrice());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_UPDELETE);
        cell.setCellValue(product.getUpdelete());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_DATEUP);
        cell.setCellValue(String.valueOf(product.getUpDate()));
    }


    private static CellStyle createStyleForHeader(Sheet sheet) {

        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());


        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }


    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
