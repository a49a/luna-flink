package com.dtstack.flink.connector.mysql;

import org.apache.flink.connector.jdbc.internal.converter.AbstractJdbcRowConverter;
import org.apache.flink.table.types.logical.RowType;

/**
 * @program: luna-flink
 * @author: wuren
 * @create: 2021/03/29
 **/
public class MySQLRowConverter extends AbstractJdbcRowConverter {

    private static final long serialVersionUID = 1L;

    @Override
    public String converterName() {
        return "MySQL";
    }

    public MySQLRowConverter(RowType rowType) {
        super(rowType);
    }
}
