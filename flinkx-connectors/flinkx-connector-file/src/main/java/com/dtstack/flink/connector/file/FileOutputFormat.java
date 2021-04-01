package com.dtstack.flink.connector.file;

import org.apache.flink.api.common.io.RichOutputFormat;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.types.logical.LogicalType;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 * @program: slink
 * @author: wuren
 * @create: 2021/01/25
 **/
public class FileOutputFormat extends RichOutputFormat<RowData> {

    private transient OutputStream outputStream;
    private LogicalType[] logicalTypes;
    public FileOutputFormat(LogicalType[] logicalTypes) {
        this.logicalTypes = logicalTypes;
    }

    @Override
    public void configure(Configuration parameters) {

    }

    @Override
    public void open(int taskNumber, int numTasks) throws IOException {
        Path path = Paths.get("./foo.txt");
        OutputStream stream = Files.newOutputStream(path, CREATE, APPEND);
        outputStream = new BufferedOutputStream(stream);
    }

    @Override
    public void writeRecord(RowData record) throws IOException {
        int len = record.getArity();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            RowData.FieldGetter fieldGetter = RowData.createFieldGetter(logicalTypes[i], i);
            String s = fieldGetter.getFieldOrNull(record).toString();
            sb.append(s);
            sb.append(",");
        }
        sb.append("\n");
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }
}
