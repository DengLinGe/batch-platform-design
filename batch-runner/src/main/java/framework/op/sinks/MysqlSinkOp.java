package framework.op.sinks;

import entity.PipelineProto;
import utils.YamlUtils;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 21:43
 * @Modified By:
 */
public class MysqlSinkOp implements SinkOp{
    @Override
    public void create(Map<String, Object> config, PipelineProto.Sink.Builder sinkBuilder) {
        PipelineProto.Sink.MysqlSink.Builder mysqlSinkBuilder = PipelineProto.Sink.MysqlSink.newBuilder();
        String url = YamlUtils.getString(config, "url");
        String driver = YamlUtils.getStringOrDefault(config, "driver", "com.mysql.cj.jdbc.Driver");
        String dbTable = YamlUtils.getString(config, "dbTable");
        String user = YamlUtils.getString(config, "user");
        String password = YamlUtils.getString(config, "password");

        if (url != null) {
            mysqlSinkBuilder.setUrl(url);
        }
        if (driver != null) {
            mysqlSinkBuilder.setDriver(driver);
        }
        if (dbTable != null) {
            mysqlSinkBuilder.setDbTable(dbTable);
        }

        if (user != null) {
            mysqlSinkBuilder.setUser(user);
        }
        if (password != null) {
            mysqlSinkBuilder.setPassword(password);
        }

        sinkBuilder.setJdbc(mysqlSinkBuilder.build());
    }
}
