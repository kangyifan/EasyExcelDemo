package cn.kangyifan.pojo;

import cn.kangyifan.converter.StringToLocalDateTimeConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DemoData {

    @ExcelProperty(index = 0)
    private Long no;

    @ExcelProperty(index = 1)
    private String name;

    @ExcelProperty(index = 2)
    private Integer age;

    @ExcelProperty(index = 3, converter = StringToLocalDateTimeConverter.class)
    private LocalDateTime createTime;
}
