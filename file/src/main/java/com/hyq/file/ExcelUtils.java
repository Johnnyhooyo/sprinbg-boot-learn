package com.hyq.file;


import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel相关工具类
 *
 * @author dingxiangyong, chengyao
 */
public class ExcelUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtils.class);
    private static final int MAX_IMPORT_COUNT = 10000;

    /**
     * 解析excel文件，文件不存在或解析失败将返回空集合
     *
     * @param inputStream excel流
     * @param ext         excel文件类型
     * @param startRow    读取起始行
     * @return 字符串列表格式
     */
    public static List<List<String>> readExcel(InputStream inputStream, String ext, int startRow) {
        try {
            if ("xlsx".equalsIgnoreCase(ext)) {
                return readExcel2007(inputStream, startRow);
            } else if ("xls".equalsIgnoreCase(ext)) {
                return readExcel2003(inputStream, startRow);
            } else {
                throw new BizException("只支持excel文件", 111);
            }
        } catch (BizException ex) {
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("读取文档失败，失败原因：{}", ex);
        }
        return null;
    }

    /**
     * 解析excel文件，文件不存在或解析失败将返回空集合
     *
     * @param inputStream excel流
     * @param fileType         excel文件类型
     * @param startRow    读取起始行
     * @return 字符串列表格式
     */
    public static <T> List<T> readExcelAsObject(InputStream inputStream, String fileType, int startRow, Class<T> cls) {
        try {
            if ("xlsx".equalsIgnoreCase(fileType)) {
                return readExcel2007WithClass(inputStream, startRow, cls);
            } else if ("xls".equalsIgnoreCase(fileType)) {
                return readExcel2003WithClass(inputStream, startRow, cls);
            } else {
                throw new BizException("只支持excel文件", 111);
            }
        } catch (BizException ex) {
            throw ex;
        } catch (Exception ex) {
            LOGGER.error("读取文档失败，失败原因：{}", ex);
        }
        return null;
    }

    private static <T> List<T> readExcel2003WithClass(InputStream inputStream, int startRow, Class<T> cls) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] declaredFields = cls.getDeclaredFields();
        ArrayList<T> result = Lists.newArrayList();
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fs);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > MAX_IMPORT_COUNT) {
                throw new BizException("单次导入不能超过" + MAX_IMPORT_COUNT + "条", 222);
            }

            // 循环输出表格中的内容(忽略表头)
            for (int i = sheet.getFirstRowNum() + startRow; i < totalRows; i++) {
                T t = cls.newInstance();
                HSSFRow row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < declaredFields.length; j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，全部trim去除多余空格
                    HSSFCell hssfCell = row.getCell((short) j);
                    String cell;
                    if (null != hssfCell && CellType.NUMERIC.equals(hssfCell.getCellTypeEnum()) && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(hssfCell)) {
                        Date dateCellValue = hssfCell.getDateCellValue();
                        setDateValue(declaredFields[j].getName(), dateCellValue, t);
                    } else {
                        cell = null == row.getCell((short) j) ? "-" : row.getCell((short) j).toString().trim();
                        setValue(declaredFields[j].getName(), cell, t);
                    }
                }
                result.add(t);
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

    private static <T> List<T> readExcel2007WithClass(InputStream inputStream, int startRow, Class<T> cls) throws IOException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ArrayList<T> result = Lists.newArrayList();
        Field[] declaredFields = cls.getDeclaredFields();
        XSSFWorkbook xssfWorkbook = null;
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > MAX_IMPORT_COUNT) {
                throw new BizException("单次导入不能超过" + MAX_IMPORT_COUNT + "条", 2222);
            }

            // 循环输出表格中的内容(忽略表头)
            for (int i = sheet.getFirstRowNum() + startRow; i < totalRows; i++) {
                T t = cls.newInstance();
                XSSFRow row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < declaredFields.length; j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，全部trim去除多余空格
                    XSSFCell xssfCell = row.getCell((short) j);
                    String cell;
                    if (null != xssfCell && CellType.NUMERIC.equals(xssfCell.getCellTypeEnum()) && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(xssfCell)) {
                        Date dateCellValue = xssfCell.getDateCellValue();
                        setDateValue(declaredFields[j].getName(), dateCellValue, t);
                    } else {
                        cell = null == row.getCell((short) j) ? "-" : row.getCell((short) j).toString().trim();
                        setValue(declaredFields[j].getName(), cell, t);
                    }
                }
                result.add(t);
            }
        } finally {
            IOUtils.closeQuietly(xssfWorkbook);
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

    private static List<List<String>> readExcel2003(InputStream inputStream, int startRow) throws IOException {
        List<List<String>> result = new ArrayList<List<String>>();
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            POIFSFileSystem fs = new POIFSFileSystem(inputStream);
            HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fs);
            HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > MAX_IMPORT_COUNT) {
                throw new BizException("单次导入不能超过" + MAX_IMPORT_COUNT + "条", 2222);
            }

            // 循环输出表格中的内容(忽略表头)
            for (int i = sheet.getFirstRowNum() + startRow; i < totalRows; i++) {
                List<String> temp = new ArrayList<String>();
                HSSFRow row = sheet.getRow(i);
                int totalCells = row.getPhysicalNumberOfCells();
                for (int j = row.getFirstCellNum(); j < totalCells; j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，全部trim去除多余空格
                    HSSFCell hssfCell = row.getCell((short) j);
                    String cell;
                    if (null != hssfCell && CellType.NUMERIC.equals(hssfCell.getCellTypeEnum()) && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(hssfCell)) {
                        Date dateCellValue = hssfCell.getDateCellValue();
                        cell = DateFormatUtils.format(dateCellValue, "yyyy-MM-dd HH:mm:ss");
                    } else {
                        cell = null == row.getCell((short) j) ? "" : row.getCell((short) j).toString().trim();
                    }
                    temp.add(cell);
                }
                result.add(temp);
            }
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

    private static List<List<String>> readExcel2007(InputStream inputStream, int startRow) throws IOException {
        List<List<String>> result = new ArrayList<List<String>>();
        XSSFWorkbook xssfWorkbook = null;
        try {
            // 构造 XSSFWorkbook 对象，strPath 传入文件路径
            xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows > MAX_IMPORT_COUNT) {
                throw new BizException("单次导入不能超过" + MAX_IMPORT_COUNT + "条", 2222);
            }

            // 循环输出表格中的内容(忽略表头)
            for (int i = sheet.getFirstRowNum() + startRow; i < totalRows; i++) {
                List<String> temp = new ArrayList<String>();
                XSSFRow row = sheet.getRow(i);
                int totalCells = row.getPhysicalNumberOfCells();
                for (int j = row.getFirstCellNum(); j < totalCells; j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，全部trim去除多余空格
                    XSSFCell xssfCell = row.getCell((short) j);
                    String cell;
                    if (null != xssfCell && CellType.NUMERIC.equals(xssfCell.getCellTypeEnum()) && org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(xssfCell)) {
                        Date dateCellValue = xssfCell.getDateCellValue();
                        cell = DateFormatUtils.format(dateCellValue, "yyyy-MM-dd HH:mm:ss");
                    } else {
                        cell = null == row.getCell((short) j) ? "" : row.getCell((short) j).toString().trim();
                    }
                    temp.add(cell);
                }
                result.add(temp);
            }
        } finally {
            IOUtils.closeQuietly(xssfWorkbook);
            IOUtils.closeQuietly(inputStream);
        }
        return result;
    }

    public static void setValue(String name, String value, Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String prop = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String mname = "set" + prop;
        Class[] types = new Class[] { String.class };
        Method method = obj.getClass().getMethod(mname, types);
        method.invoke(obj, value);
    }

    public static void setDateValue(String name, Date value, Object obj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String prop = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String mname = "set" + prop;
        Class[] types = new Class[] { Date.class };
        Method method = obj.getClass().getMethod(mname, types);
        method.invoke(obj, value);
    }
}
