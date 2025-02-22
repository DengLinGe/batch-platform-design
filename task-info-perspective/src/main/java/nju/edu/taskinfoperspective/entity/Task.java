package nju.edu.taskinfoperspective.entity;

import lombok.Data;

import java.util.Map;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -20 21:01
 * @Modified By:
 */

@Data
public class Task {
    private int taskId;
    private String taskName;
    private int versionId;
    private String creatorId;
    private String creationTime;
    private String updateTime;
    private int auditLogId;
    private String auditStatus;

}
