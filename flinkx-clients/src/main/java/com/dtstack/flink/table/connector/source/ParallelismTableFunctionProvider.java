/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.flink.table.connector.source;

import org.apache.flink.table.connector.ParallelismProvider;
import org.apache.flink.table.connector.source.TableFunctionProvider;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.functions.TableFunction;

import java.util.Optional;

/**
 * @program: luna-flink
 * @author: wuren
 * @create: 2021/04/05
 **/
public interface ParallelismTableFunctionProvider
        extends TableFunctionProvider, ParallelismProvider {

    /** Helper method for creating a AsyncTableFunction provider with a provided sink parallelism. */
    static TableFunctionProvider of(TableFunction<RowData> tableFunction, Integer parallelism) {
        return new ParallelismTableFunctionProvider() {

            @Override
            public TableFunction<RowData> createTableFunction() {
                return tableFunction;
            }

            @Override
            public Optional<Integer> getParallelism() {
                return Optional.ofNullable(parallelism);
            }
        };
    }

}
