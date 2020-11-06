package com.taobao.yugong.common.utils;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlMasterSlaveDataSourceFactory;
import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;


public class DataSourceHelper {

	public static DataSource getDataSourceForShardingDatabases() throws IOException, SQLException,Exception {
		return YamlShardingDataSourceFactory.createDataSource(getFile("/databases.yaml"));
    }
	
	static DataSource getDataSourceForShardingDatabasesAndTables() throws IOException, SQLException {
		return YamlShardingDataSourceFactory.createDataSource(getFile("/hint-databases-tables.yaml"));
    }
	
	static DataSource getDataSourceForMaster() throws IOException, SQLException {
		return YamlMasterSlaveDataSourceFactory.createDataSource(getFile("/hint-master-only.yaml"));
    }
	
    private static File getFile(final String configFile) {
        return new File(Thread.currentThread().getClass().getResource(configFile).getFile());
    }
}
