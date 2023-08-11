package io.github.coffee330501.common.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.mybatisflex.core.paginate.Page;
import io.github.coffee330501.common.support.PageQueryFunction;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class ExcelUtil {
    /**
     * 默认页大小
     */
    private static final int DEFAULT_PAGE_SIZE = 5000;

    /**
     * 默认文件后缀
     */
    private static final String DEFAULT_SUFFIX = ".xlsx";

    /**
     * 创建Excel
     *
     * @param excelTemplate Excel模板
     * @param func          查询方法
     */
    public static void write(Class<?> excelTemplate, PageQueryFunction func) {
        String suffix = ".xlsx";
        String filename = createCompleteFileName(String.valueOf(UUID.randomUUID()), suffix);
        write(filename, suffix, excelTemplate, func);
    }

    /**
     * 创建Excel
     *
     * @param filename      文件名
     * @param excelTemplate Excel模板
     * @param func          查询方法
     */
    public static void write(String filename, Class<?> excelTemplate, PageQueryFunction func) {
        write(filename, DEFAULT_SUFFIX, excelTemplate, func);
    }

    /**
     * 创建Excel
     *
     * @param filename      文件名
     * @param suffix        文件后缀
     * @param excelTemplate Excel模板
     * @param func          查询方法
     */
    public static void write(String filename, String suffix, Class<?> excelTemplate, PageQueryFunction func) {
        filename = createCompleteFileName(filename, suffix);

        try (ExcelWriter excelWriter = EasyExcel.write(filename, excelTemplate).build()) {
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            doWrite(func, excelWriter, writeSheet);
        }
    }

    /**
     * 创建Excel并上传OSS
     *
     * @param filename      文件名
     * @param excelTemplate excel模板
     * @param func          查询方法
     */
    public static void writeToOss(String filename, Class<?> excelTemplate, PageQueryFunction func) {
        writeToOss(filename, DEFAULT_SUFFIX, excelTemplate, func);
    }

    /**
     * 创建Excel并上传OSS
     *
     * @param filename      文件名
     * @param suffix        文件后缀
     * @param excelTemplate excel模板
     * @param func          查询方法
     */
    public static void writeToOss(String filename, String suffix, Class<?> excelTemplate, PageQueryFunction func) {
        writeToOss(filename, suffix, "1", excelTemplate, func);
    }

    /**
     * 创建Excel并上传OSS
     *
     * @param filename      文件名
     * @param suffix        文件后缀
     * @param sheetName     sheetName
     * @param excelTemplate excel模板
     * @param func          查询方法
     */
    public static void writeToOss(String filename, String suffix, String sheetName, Class<?> excelTemplate, PageQueryFunction func) {
        String completeFileName = createCompleteFileName(filename, suffix);
        File file = new File(completeFileName);
        InputStream inputStream = null;
        try {
            // 获取总条数
            Page<?> page = func.query(1, 1);
            long size = page.getTotalPage();
            // 创建Excel
            EasyExcel.write(file, excelTemplate).sheet(sheetName).doWrite(func.query(1, size).getRecords());
            // 写入OSS
            inputStream = Files.newInputStream(file.toPath());
            OssUtil.upload(inputStream, completeFileName);
        } catch (Exception e) {
            log.error("writeToOss", e);
        } finally {
            // 关闭流、删除文件
            try {
                if (inputStream != null) inputStream.close();
            } catch (Exception e) {
                log.error("writeToOss inputStream.close", e);
            }
            file.deleteOnExit();
        }
    }

    private static void doWrite(PageQueryFunction func, ExcelWriter excelWriter, WriteSheet writeSheet) {
        doWrite(func, DEFAULT_PAGE_SIZE, excelWriter, writeSheet);
    }

    @SuppressWarnings("all")
    private static void doWrite(PageQueryFunction func, int pageSize, ExcelWriter excelWriter, WriteSheet writeSheet) {
        Page<?> page = func.query(1, pageSize);
        excelWriter.write(page.getRecords(), writeSheet);

        long totalPage = page.getTotalPage();
        for (int i = 2; i < totalPage; i++) {
            page = func.query(i, pageSize);
            excelWriter.write(page.getRecords(), writeSheet);
        }
    }

    /**
     * 生成完整文件名
     *
     * @param filename 文件名
     * @param suffix   文件后缀
     * @return 完整文件名
     */
    private static String createCompleteFileName(String filename, String suffix) {
        return filename + DateUtils.format(new Date(), DateUtils.DATE_FORMAT_14) + suffix;
    }
}
