package com.taobao.yugong;

import com.taobao.yugong.common.utils.DataSourceHelper;
import com.taobao.yugong.common.utils.HintManagerHelper;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.Test;

import com.taobao.yugong.controller.YuGongController;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author agapple 2014年2月25日 下午11:38:06
 * @since 1.0.0
 */
public class YuGongControllerTest {

    @Test
    public void testSimple() throws Exception {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load(YuGongLauncher.class.getClassLoader().getResourceAsStream("yugong.properties"));

        YuGongController controller = new YuGongController(config);
        controller.start();
        controller.waitForDone();
        Thread.sleep(3 * 1000); // 等待3s，清理上下文
        controller.stop();
    }

    @Test
    public void testYaml() throws Exception {
        //HintManager hintManager = HintManager.getInstance();
        //HintManagerHelper.initializeHintManagerForShardingDatabases(hintManager);
        DataSource dataSource = DataSourceHelper.getDataSourceForShardingDatabases();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        for (Long i = 1L; i <= 10; i++) {
            jdbcTemplate.execute("INSERT INTO yugong_example_mysql (id) VALUES (" + i + ")");
            System.out.println(i+ " -- ");
        }
        /*try (Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement()) {
            for (Long i = 1L; i <= 10; i++) {
                boolean execute = statement.execute("INSERT INTO yugong_example_mysql (id) VALUES ("+i+")");
                System.out.println(i+ " -- "+execute);
            }
            //ResultSet result = statement.executeQuery("select user_id, user_name from user");

            while (result.next()) {
                System.out.println(result.getLong(1) + ": " + result.getString(2));
            }
        }*/
    }
}
