package io.github.coffee330501.common.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.converters.ReadConverterContext;
import com.alibaba.excel.converters.WriteConverterContext;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.data.WriteCellData;
import io.github.coffee330501.common.enums.IEnum;
import io.github.coffee330501.common.enums.UserTypeEnum;

public class EnumConvert implements Converter<IEnum<String>> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return IEnum.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public IEnum<String> convertToJavaData(ReadConverterContext<?> context) {
        String desc = context.getReadCellData().getStringValue();
        return UserTypeEnum.Pedlar;
    }

    @Override
    public WriteCellData<?> convertToExcelData(WriteConverterContext<IEnum<String>> context) throws Exception {
        return new WriteCellData<>(context.getValue().getValue());
    }
}
