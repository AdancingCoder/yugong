package com.taobao.yugong.common.utils;

import org.apache.shardingsphere.api.hint.HintManager;

public class HintManagerHelper {

	static void initializeHintManagerForShardingDatabasesAndTables(final HintManager hintManager) {
		hintManager.addDatabaseShardingValue("health_record", 3L);
		hintManager.addTableShardingValue("health_record", 2L);
	}

	public static void initializeHintManagerForShardingDatabases(final HintManager hintManager) {
        //hintManager.addDatabaseShardingValue("yugong_example_mysql", 2L);
        hintManager.setDatabaseShardingValue(1L);
	}

	static void initializeHintManagerForMaster(final HintManager hintManager) {
		hintManager.setMasterRouteOnly();
	}
}
