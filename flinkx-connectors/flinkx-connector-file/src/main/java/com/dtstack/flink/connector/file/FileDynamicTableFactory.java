package com.dtstack.flink.connector.file;

import org.apache.flink.configuration.ConfigOption;
import org.apache.flink.configuration.ConfigOptions;
import org.apache.flink.table.api.TableSchema;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.factories.DynamicTableFactory;
import org.apache.flink.table.factories.DynamicTableSinkFactory;
import org.apache.flink.table.factories.FactoryUtil;

import java.util.HashSet;
import java.util.Set;

import static org.apache.flink.table.factories.FactoryUtil.createTableFactoryHelper;

/**
 * @program: slink
 * @author: wuren
 * @create: 2021/01/25
 **/
public class FileDynamicTableFactory implements DynamicTableSinkFactory {

    public static final String IDENTIFIER = "dt-file";

    public static final ConfigOption<String> PATH =
            ConfigOptions.key("path")
                    .stringType()
                    .noDefaultValue()
                    .withDescription("File path.");

    @Override
    public String factoryIdentifier() {
        return IDENTIFIER;
    }

    @Override
    public Set<ConfigOption<?>> requiredOptions() {
        final Set<ConfigOption<?>> options = new HashSet<>();
        options.add(PATH);
        return options;
    }

    @Override
    public Set<ConfigOption<?>> optionalOptions() {
        final Set<ConfigOption<?>> options = new HashSet<>();
        return options;
    }

    @Override
    public DynamicTableSink createDynamicTableSink(DynamicTableFactory.Context context) {
        FactoryUtil.TableFactoryHelper helper = createTableFactoryHelper(this, context);
        helper.validate();
        TableSchema tableSchema = context.getCatalogTable().getSchema();
        return new FileDynamicTableSink(tableSchema);
    }

}
