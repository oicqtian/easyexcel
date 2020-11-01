package com.alibaba.excel.converters.date;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;

import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * Date and string converter
 *
 * @author Jiaju Zhuang
 */
public class LocalDateTimeStringConverter implements Converter<LocalDateTime> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalDateTime convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                           GlobalConfiguration globalConfiguration) throws ParseException {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return DateUtils.parseLocalDate(cellData.getStringValue(), null);
        } else {
            return DateUtils.parseLocalDate(cellData.getStringValue(),
                contentProperty.getDateTimeFormatProperty().getFormat());
        }
    }

    @Override
    public CellData convertToExcelData(LocalDateTime value, ExcelContentProperty contentProperty,
                                       GlobalConfiguration globalConfiguration) {
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            return new CellData(DateUtils.formatLocalDate(value, null));
        } else {
            return new CellData(DateUtils.formatLocalDate(value, contentProperty.getDateTimeFormatProperty().getFormat()));
        }
    }
}
