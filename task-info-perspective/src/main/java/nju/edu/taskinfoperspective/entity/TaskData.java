package nju.edu.taskinfoperspective.entity;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -20 21:52
 * @Modified By:
 */
public class TaskData {
    // 数据ID
    private Integer dataId;
    // 数据名称
    private String dataName;
    // 任务ID
    private Integer taskId;
    // 数据上游
    private List<TaskData> sources;
    // 数据下游
    private List<TaskData> targets;
    // 数据涉及的属性名
    private List<String> attributeNames;
    // 数据的描述
    private String description;
    // 数据类型
    private String dataType;
}
