package com.dtstack.flink.connector.jdbc.sync;

import org.apache.flink.connector.jdbc.JdbcExecutionOptions;
import org.apache.flink.connector.jdbc.internal.JdbcBatchingOutputFormat;
import org.apache.flink.connector.jdbc.internal.connection.JdbcConnectionProvider;

import javax.annotation.Nonnull;

import java.util.List;

/**
 * @program: luna-flink
 * @author: wuren
 * @create: 2021/04/07
 **/
public class SyncJdbcBatchingOutputFormat extends JdbcBatchingOutputFormat {

    protected List<String> preSql;

    protected List<String> postSql;

    public SyncJdbcBatchingOutputFormat(
            @Nonnull JdbcConnectionProvider connectionProvider,
            @Nonnull JdbcExecutionOptions executionOptions,
            @Nonnull StatementExecutorFactory statementExecutorFactory,
            @Nonnull RecordExtractor recordExtractor) {
        super(connectionProvider, executionOptions, statementExecutorFactory, recordExtractor);
    }


}
