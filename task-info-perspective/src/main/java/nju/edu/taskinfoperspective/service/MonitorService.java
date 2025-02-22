package nju.edu.taskinfoperspective.service;

import nju.edu.taskinfoperspective.entity.ResourceConfig;
import nju.edu.taskinfoperspective.entity.TaskStatusDetailInfo;
import nju.edu.taskinfoperspective.entity.TaskStatusInfo;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 17:02
 * @Modified By:
 */
public interface MonitorService {

    /**
     * 获取所有任务的实时状态信息
     *
     * @return 包含所有任务实时状态信息的列表，包括任务 ID、名称、状态、开始时间、结束时间、执行时长等
     */
    List<TaskStatusInfo> getAllTaskStatusInfo();


    /**
     * 获取指定任务的详细状态信息
     *
     * @param taskId 任务的唯一标识符
     * @return 包含指定任务详细状态信息的对象，包括任务状态、开始时间、结束时间、执行时长、CPU 使用率、内存占用、网络流量、任务执行进度等，若任务不存在则返回 null
     */
    TaskStatusDetailInfo getTaskStatusDetailInfo(String taskId);


    /**
     * 重新启动失败的任务
     *
     * @param taskId 任务的唯一标识符
     * @return 重启是否成功，成功返回 true，失败返回 false
     */
    boolean restartFailedTask(String taskId);


    /**
     * 调整任务的资源分配
     *
     * @param taskId         任务的唯一标识符
     * @param resourceConfig 新的资源配置信息
     * @return 调整是否成功，成功返回 true，失败返回 false
     */
    boolean adjustTaskResource(String taskId, ResourceConfig resourceConfig);
}
