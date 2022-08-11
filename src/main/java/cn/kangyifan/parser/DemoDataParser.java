package cn.kangyifan.parser;

import cn.kangyifan.pojo.DemoData;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.read.metadata.holder.ReadSheetHolder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DemoDataParser {

    /**
     * 读取所有sheet
     */
    public void readAll(){
        // 读取所有excel sheet页
        EasyExcel.read(getPath() + "demo.xlsx", DemoData.class, new DemoDataListener()).sheet().doRead();
    }

    /**
     * 读取首页
     */
    public void readFirst(){
        // 构造ReadSheet对象
        ReadSheet readSheet = EasyExcel
                .readSheet(0)
                .head(DemoData.class)
                .registerReadListener(new DemoDataListener())
                .build();
        // 构造ExcelReader对象
        try(ExcelReader excelReader =  EasyExcel.read(getPath() + "demo.xlsx").build()){
            //读取指定的Sheet页
            excelReader.read(readSheet);
        }
    }

    public String getPath(){
        return this.getClass().getClassLoader().getResource("//").getPath();
    }

    static class DemoDataListener implements ReadListener<DemoData> {

        /**
         *
         * @param demoData
         * @param analysisContext
         */
        @Override
        public void invoke(DemoData demoData, AnalysisContext analysisContext) {
            System.out.println(demoData.toString());
        }

        /**
         * 每个sheet读完会调用该方法一次
         * @param analysisContext
         */
        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            ReadSheetHolder readSheetHolder = analysisContext.readSheetHolder();
            System.out.println(readSheetHolder.getSheetName() + "读取完毕");
        }

        @Override
        public void onException(Exception exception, AnalysisContext context) throws Exception {
            // 如果是某一个单元格的转换异常 能获取到具体行号
            // 如果要获取头的信息 配合invokeHeadMap使用
            if (exception instanceof ExcelDataConvertException) {
                ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
                log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                        excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
            }
        }
    }
}
