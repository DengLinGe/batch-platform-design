package nju.edu.taskinfoperspective.auto;

import nju.edu.taskinfoperspective.entity.Task;
import nju.edu.taskinfoperspective.entity.TaskExceptionInfo;
import nju.edu.taskinfoperspective.entity.TaskStatusInfo;
import nju.edu.taskinfoperspective.platform.MidHub;

import java.util.List;

/**
 * @Author: Deng.
 * @Description: 自动化采集
 * @Date Created in 2025 -02 -20 16:43
 * @Modified By:
 */
public class TaskAuto {


    /**
     * 定时任务方法，按固定时间间隔自动触发任务状态采集和异常检测
     * 使用 @Scheduled 注解，fixedRate 表示任务执行的固定时间间隔，单位为毫秒
     */
    // @Scheduled(fixedRate = 1000)
    public void collectTaskStatusPeriodically() {
        // 1. 获取所有任务列表
        List<Task> allTasks = getAllTasks();

        // 2. 遍历任务列表，采集每个任务的状态
        for (Task task : allTasks) {
            TaskStatusInfo taskStatus = getTaskStatus(task.getTaskId());

            // 3. 检测任务状态是否异常
            if (isTaskStatusAbnormal(taskStatus)) {
                // 4. 如果异常，记录异常信息
                TaskExceptionInfo taskException = detectTaskException(task.getTaskId());
                recordTaskException(taskException);

                // 5. 发送异常通知
                sendExceptionNotification(taskException);
            }
        }

    }

    /**
     * 获取所有任务列表
     * @return 包含所有任务的列表
     */
    public List<Task> getAllTasks() {
        // 实现获取所有任务列表的逻辑，例如从数据库或其他数据源查询
        return null;
    }

    /**
     * 检测任务的异常信息
     * @param taskId 任务的唯一标识符
     * @return 任务的异常信息
     */
    public TaskExceptionInfo detectTaskException(Integer taskId) {
        // 实现检测任务异常信息的逻辑，例如获取异常类型、堆栈信息等
        return null;
    }


    public TaskStatusInfo getTaskStatus(Integer taskId) {
        MidHub midHubAPI = new MidHub();
        // 通过 MidHub 接口获取任务的基本状态信息
        TaskStatusInfo taskStatus = midHubAPI.getTaskStatusDetails(taskId);
        return taskStatus;
    }

    /**
     * 判断任务状态是否异常
     * @param taskStatus 任务的状态信息
     * @return 如果任务状态异常返回 true，否则返回 false
     */
    public boolean isTaskStatusAbnormal(TaskStatusInfo taskStatus) {
        MidHub midHubAPI = new MidHub();
        // 通过 MidHub 接口获取任务的详细状态信息


        // 检查任务的基本状态
        if ("FAILED".equals(taskStatus.getStatus())) {
            return true;
        }

        // 检查任务长时间处于等待执行状态
        if ("WAITING".equals(taskStatus.getStatus())) {
            // 这里可以根据具体业务逻辑判断等待时间是否过长，例如设置一个阈值
            // 假设等待时间超过 1 小时认为异常，这里简单示例，未实现具体时间判断
            return true;
        }

        // 检查 CPU 使用率是否异常
        double historicalAverageCPUUsage = midHubAPI.getTaskHistoricalAverageCPUUsage(taskStatus.getTaskId());
        if (taskStatus.getCpuUsage() > historicalAverageCPUUsage * 2) {
            return true;
        }

        // 检查内存使用率是否异常
        double historicalAverageMemoryUsage = midHubAPI.getTaskHistoricalAverageMemoryUsage(taskStatus.getTaskId());
        if (taskStatus.getMemoryUsage() > historicalAverageMemoryUsage * 2) {
            return true;
        }

        // 检查网络流量是否异常
        double historicalAverageNetworkTraffic = midHubAPI.getTaskHistoricalAverageNetworkTraffic(taskStatus.getTaskId());
        if (taskStatus.getNetworkTraffic() > historicalAverageNetworkTraffic * 2) {
            return true;
        }

        // 检查任务执行进度是否异常
        if (taskStatus.getProgress() == 0 && !"WAITING".equals(taskStatus.getStatus())) {
            return true;
        }

        return false;
    }

    /**
     * 记录任务的异常信息
     * @param taskException 任务的异常信息
     */
    public void recordTaskException(TaskExceptionInfo taskException) {
        // 实现记录任务异常信息的逻辑，例如写入日志或数据库
    }

    /**
     * 发送任务异常通知
     * @param taskException 任务的异常信息
     */
    public void sendExceptionNotification(TaskExceptionInfo taskException) {
        // 实现发送任务异常通知的逻辑，例如发送邮件、短信等

    }
}
