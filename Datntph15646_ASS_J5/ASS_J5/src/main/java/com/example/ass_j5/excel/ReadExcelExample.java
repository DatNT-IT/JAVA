package com.example.ass_j5.excel;

import com.example.ass_j5.entity.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//đọc từ exel vào

public class ReadExcelExample {

    public  List<Product> readExcel(String excelFilePath) throws IOException {
        final int COLUMN_INDEX_ID = 0;
        final int COLUMN_INDEX_ACCOUNT = 1;
        final int COLUMN_INDEX_CATE = 2;
        final int COLUMN_INDEX_DATE = 3;
        final int COLUMN_INDEX_IMAGE = 4;
        final int COLUMN_INDEX_NAME = 5;
        final int COLUMN_INDEX_TITLE = 6;
        final int COLUMN_INDEX_PRICE = 7;
        final int COLUMN_INDEX_UPDELETE = 8;
        final int COLUMN_INDEX_DATEUP = 9;
        List<Product> list = new ArrayList<>();

        InputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = getWorkbook(inputStream, excelFilePath);

        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            if (nextRow.getRowNum() == 0) {
                continue;
            }

            Iterator<Cell> cellIterator = nextRow.cellIterator();

            Product product = new Product();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case COLUMN_INDEX_ID:
                        product.setId((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_ACCOUNT:
                        product.setAccount((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_CATE:
                        product.setCate(String.valueOf(getCellValue(cell)));
                        break;
                    case COLUMN_INDEX_DATE:
                        product.setCreateDate(LocalDateTime.parse((String) getCellValue(cell)));
                        break;
                    case COLUMN_INDEX_IMAGE:
                        product.setImage((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_NAME:
                        product.setName((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_TITLE:
                        product.setTitle((String) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_PRICE:
                        product.setPrice((Double) getCellValue(cell));
                        break;
                    case COLUMN_INDEX_UPDELETE:
                        product.setUpdelete((int) cell.getNumericCellValue());
                        break;
                    case COLUMN_INDEX_DATEUP:
                        product.setUpDate(LocalDateTime.parse((String) getCellValue(cell)));
                        break;
                    default:
                        break;
                }

            }
            list.add(product);
        }

        workbook.close();
        inputStream.close();

        return list;
    }

    private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    private  Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
            case BOOLEAN:
                cellValue = cell.getBooleanCellValue();
                break;
            case FORMULA:
                Workbook workbook = cell.getSheet().getWorkbook();
                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                cellValue = evaluator.evaluate(cell).getNumberValue();
                break;
            case NUMERIC:
                cellValue = cell.getNumericCellValue();
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case _NONE:
            case BLANK:
            case ERROR:
                break;
            default:
                break;
        }

        return cellValue;
    }
}
