package framework.op.sources;

import entity.PipelineProto;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 14:51
 * @Modified By:
 */
public class CsvSourceOp implements SourceOp {

    @Override
    public void create(Map<String, Object> config, PipelineProto.Source.Builder sourceBuilder) {
// 创建 CSV 消息的构建器
        PipelineProto.Source.CSV.Builder csvBuilder = PipelineProto.Source.CSV.newBuilder();

        // 提取并设置 path 属性，若未配置则使用默认值
        String path = (String) config.getOrDefault("path", "");
        csvBuilder.setPath(path);

        // 提取并设置 header 属性，若未配置则使用默认值 false
        boolean header = (boolean) config.getOrDefault("header", false);
        csvBuilder.setHeader(header);

        // 提取并设置 delimiter 属性，若未配置则使用默认值逗号
        String delimiter = (String) config.getOrDefault("delimiter", null);
        csvBuilder.setDelimiter(delimiter);

        // 提取并设置 quote 属性，若未配置则使用默认值双引号
        String quote = (String) config.getOrDefault("quote", "\"");
        csvBuilder.setQuote(quote);

        // 提取并设置 escape 属性，若未配置则使用默认值反斜杠
        String escape = (String) config.getOrDefault("escape", "\\");
        csvBuilder.setEscape(escape);

        // 构建 CSV 消息
        PipelineProto.Source.CSV csv = csvBuilder.build();

        // 将 CSV 配置设置到 Source 构建器中
        sourceBuilder.setCsv(csv);
    }
}
