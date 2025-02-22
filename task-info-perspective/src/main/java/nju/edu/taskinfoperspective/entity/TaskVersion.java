package nju.edu.taskinfoperspective.entity;

import lombok.Data;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 16:50
 * @Modified By:
 */

@Data
public class TaskVersion {
    private int versionId;
    private int taskId;
    private String versionNumber;
    private String taskUrl;
    private String changeTime;
    private String changePerson;
}
