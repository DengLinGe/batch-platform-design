package nju.edu.taskinfoperspective.service;

import nju.edu.taskinfoperspective.entity.TaskVersion;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 16:50
 * @Modified By:
 */
public interface TaskVersionService {
    /**
     * 根据任务 ID 获取任务的所有版本信息
     * @param taskId 任务 ID
     * @return 任务的所有版本信息列表
     */
    List<TaskVersion> getTaskVersionsByTaskId(int taskId);

    /**
     * 创建新的任务版本
     * @param taskVersion 任务版本信息
     * @return 任务版本 ID
     */
    int createTaskVersion(TaskVersion taskVersion);

    /**
     * 根据版本 ID 获取任务版本信息
     * @param versionId 版本 ID
     * @return 任务版本信息
     */
    TaskVersion getTaskVersionById(int versionId);

    /**
     * 将任务回滚到指定版本
     * @param taskId 任务 ID
     * @param versionId 目标版本 ID
     * @return 回滚是否成功
     */
    boolean rollbackTaskToVersion(int taskId, int versionId);
}
