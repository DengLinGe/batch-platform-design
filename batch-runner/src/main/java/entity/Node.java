package entity;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -17 15:48
 * @Modified By:
 */
public class Node {

    public String uid;
    public String operatorType;
    public List<String> inputs;
    public List<String> outputs;
    public Object config; // 存储该节点的配置信息

    public Node(String uid, String operatorType, List<String> inputs, List<String> outputs, Object config) {
        this.uid = uid;
        this.operatorType = operatorType;
        this.inputs = inputs;
        this.outputs = outputs;
        this.config = config;
    }



}
