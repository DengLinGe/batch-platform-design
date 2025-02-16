package op.sources;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 14:46
 * @Modified By:
 */
public class MysqlSourceOp implements SourceOp {


    @Override
    public void create(Map<String, Object> config, PipelineProto.Source.Builder sourceBuilder) {
        // 创建 Mysql 消息的构建器
        PipelineProto.Source.Mysql.Builder mysqlBuilder = PipelineProto.Source.Mysql.newBuilder();

        // 提取并设置 url 属性，若未配置则使用空字符串作为默认值
        String url = (String) config.getOrDefault("url", "localhost:3306");
        mysqlBuilder.setUrl(url);

        // 提取并设置 driver 属性，若未配置则使用 MySQL 的 JDBC 驱动类名作为默认值
        String driver = (String) config.getOrDefault("driver", "com.mysql.cj.jdbc.Driver");
        mysqlBuilder.setDriver(driver);

        // 提取并设置 dbtable 属性，若未配置则使用空字符串作为默认值
        if (config.get("dbName") == null) {
            throw new IllegalArgumentException("Mysql source must have dbtable.");
        }
        String dbName = (String) config.get("dbName");
        mysqlBuilder.setDbName(dbName);

        if (config.get("tableName") == null) {
            throw new IllegalArgumentException("Mysql source must have tableName.");
        }
        String tableName = (String) config.get("tableName");
        mysqlBuilder.setTableName(tableName);

        // 提取并设置 user 属性，若未配置则使用空字符串作为默认值
        String user = (String) config.getOrDefault("user", "root");
        mysqlBuilder.setUser(user);

        // 提取并设置 password 属性，若未配置则使用空字符串作为默认值
        String password = (String) config.getOrDefault("password", "21314zaq");
        mysqlBuilder.setPassword(password);

        // 提取并设置 column 属性，若未配置则使用空字符串作为默认值
        if (config.get("column") != null) {
            String column = (String) config.getOrDefault("column", "");
            mysqlBuilder.setColumn(column);
        }


        // 构建 Mysql 消息
        PipelineProto.Source.Mysql mysql = mysqlBuilder.build();

        // 将 Mysql 配置设置到 Source 构建器中
        sourceBuilder.setMysql(mysql);

    }
}
