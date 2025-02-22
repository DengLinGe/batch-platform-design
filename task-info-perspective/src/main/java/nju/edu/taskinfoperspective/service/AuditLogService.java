package nju.edu.taskinfoperspective.service;

import nju.edu.taskinfoperspective.entity.AuditLog;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 16:48
 * @Modified By:
 */
public interface AuditLogService {
    /**
     * 创建审核日志
     * @param auditLog 审核日志信息
     * @return 审核日志 ID
     */
    int createAuditLog(AuditLog auditLog);

    /**
     * 根据任务 ID 获取审核日志列表
     * @param taskId 任务 ID
     * @return 审核日志列表
     */
    List<AuditLog> getAuditLogsByTaskId(int taskId);
}
