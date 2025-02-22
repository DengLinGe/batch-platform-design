package nju.edu.taskinfoperspective.entity;

import lombok.Data;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -20 16:23
 * @Modified By:
 */
@Data
public class TaskStatusInfo {

    private Long id;
    private String taskId;
    private String status;
    private double cpuUsage;
    private double memoryUsage;
    private double networkTraffic;
    private double progress;

}
