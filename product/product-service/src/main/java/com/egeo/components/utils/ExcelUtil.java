package com.egeo.components.utils;

import com.egeo.exception.BusinessException;
import com.egeo.utils.Upload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ExcelUtil {

    public static  <T> String writeDataInExcel(String sheetName, String[] columns,
                                        List<T> dataSources,DoExcel<T> doExcel,
                                        Upload uploadService) {
        Workbook wsd = new XSSFWorkbook();
        Sheet cloneSheet = wsd.createSheet(sheetName);
        Row head = cloneSheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            head.createCell(i).setCellValue(columns[i]);
        }
        for (int i = 0; i < dataSources.size(); i++) {
            Row row = cloneSheet.createRow(i + 1);
            doExcel.setColumnValue(row,dataSources.get(i));
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            wsd.write(bos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("导出失败!");
        }
        String upload = uploadService.fastDFSUpload(bos.toByteArray(), "xlsx");
        return upload;
    }

    public interface DoExcel<T>{

        void setColumnValue(Row row, T t);
    }
}
