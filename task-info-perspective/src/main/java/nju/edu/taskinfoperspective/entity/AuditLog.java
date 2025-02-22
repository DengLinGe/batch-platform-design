package nju.edu.taskinfoperspective.entity;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 16:48
 * @Modified By:
 */
public class AuditLog {
    private int auditLogId;
    private int taskId;
    private int auditorId;
    private String auditTime;
    private String preAuditStatus;
    private String postAuditStatus;
    private String auditOpinion;
}
