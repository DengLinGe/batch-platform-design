package nju.edu.taskinfoperspective.service;

import nju.edu.taskinfoperspective.entity.Task;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 16:46
 * @Modified By:
 */
public interface TaskService {
    /**
     * 获取待审核的任务列表
     * @return 待审核的任务列表
     */
    List<Task> getPendingAuditTasks();

    /**
     * 根据任务 ID 获取任务信息
     * @param taskId 任务 ID
     * @return 任务信息
     */
    Task getTaskById(int taskId);

    /**
     * 更新任务的审核状态
     * @param taskId 任务 ID
     * @param auditStatus 审核状态
     * @param auditLogId 审核日志 ID
     * @return 更新是否成功
     */
    boolean updateTaskAuditStatus(int taskId, String auditStatus, int auditLogId);
}
