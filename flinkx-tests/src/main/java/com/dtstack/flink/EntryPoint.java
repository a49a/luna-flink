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

package com.dtstack.flink;

import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @program: luna-flink
 * @author: wuren
 * @create: 2021/03/29
 **/
public class EntryPoint {

    public static void main(String[] args) {

        StreamTableEnvironment tEnv = EnvFactory.createTableEnv();

        // Kafka Source
        tEnv.executeSql("CREATE TABLE ods (\n" +
                "  `id` INT,\n" +
                "  `name` STRING\n" +
                ") WITH (\n" +
                "  'connector' = 'kafka',\n" +
                "  'topic' = 'luna',\n" +
                "  'properties.bootstrap.servers' = 'localhost:9092',\n" +
                "  'properties.group.id' = 'luna_g',\n" +
                "  'scan.startup.mode' = 'earliest-offset',\n" +
                "  'format' = 'json'\n" +
                ")");

        // Mysql Sink
        tEnv.executeSql("CREATE TABLE ads (\n" +
                "    `id` INT,\n" +
                "    `name` STRING\n" +
                ") WITH (\n" +
                "    'connector' = 'dt-mysql',\n" +
                "    'url' = 'jdbc:mysql://localhost:3306/flink_dev',\n" +
                "    'table-name' = 'ads_m',\n" +
                "    'username' = 'root',\n" +
                "    'password' = 'root'\n" +
                ")");

        tEnv.executeSql("INSERT INTO ads SELECT id, name FROM ods");

    }

}
