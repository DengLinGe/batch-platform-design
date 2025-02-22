package nju.edu.taskinfoperspective.platform;

import nju.edu.taskinfoperspective.entity.TaskStatusInfo;

import java.util.List;

/**
 * @Author: Deng.
 * @Description: 模拟中台功能
 * @Date Created in 2025 -02 -20 16:27
 * @Modified By:
 */
public  class   MidHub {
    // 获取任务的详细状态信息
    public TaskStatusInfo getTaskStatusDetails(Integer taskId){
        return null;
    }

    // 获取任务的历史平均 CPU 使用率
    public double getTaskHistoricalAverageCPUUsage(String taskId){
         return 0;
     }

    // 获取任务的历史平均内存使用率
    public double getTaskHistoricalAverageMemoryUsage(String taskId){
         return 0;
     }

    // 获取任务的历史平均网络流量`
    public double getTaskHistoricalAverageNetworkTraffic(String taskId){
         return 0;
     }

}
