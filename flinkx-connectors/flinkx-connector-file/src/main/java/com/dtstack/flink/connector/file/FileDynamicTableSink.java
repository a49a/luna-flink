package com.dtstack.flink.connector.file;

import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.connector.sink.OutputFormatProvider;
import org.apache.flink.table.types.DataType;
import org.apache.flink.table.types.logical.LogicalType;

import java.util.Arrays;

/**
 * @program: slink
 * @author: wuren
 * @create: 2021/01/25
 **/
public class FileDynamicTableSink implements DynamicTableSink {

    private final TableSchema tableSchema;

    public FileDynamicTableSink(TableSchema tableSchema) {
        this.tableSchema = tableSchema;
    }

    @Override
    public ChangelogMode getChangelogMode(ChangelogMode changelogMode) {
        return ChangelogMode.insertOnly();
    }

    @Override
    public SinkRuntimeProvider getSinkRuntimeProvider(Context context) {
        DataType[] fieldDataTypes = tableSchema.getFieldDataTypes();
        final LogicalType[] logicalTypes =
                Arrays.stream(fieldDataTypes)
                        .map(DataType::getLogicalType)
                        .toArray(LogicalType[]::new);
        FileOutputFormat outputFormat = new FileOutputFormat(logicalTypes);
        tableSchema.getPrimaryKey();
        return OutputFormatProvider.of(outputFormat);
    }

    @Override
    public String asSummaryString() {
        return "Luna File System";
    }

    @Override
    public DynamicTableSink copy() {
        return new FileDynamicTableSink(tableSchema);
    }
}
