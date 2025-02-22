package nju.edu.taskinfoperspective.service;

import nju.edu.taskinfoperspective.entity.DataLineage;
import nju.edu.taskinfoperspective.entity.Task;
import nju.edu.taskinfoperspective.entity.TaskData;

import java.util.List;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 15:10
 * @Modified By:
 */
public interface TaskDataService {
    List<TaskData> queryTaskDataList(String sql, String taskId);

    /**
     * 根据数据去向查询相关任务的数据血缘信息列表
     * @param dataDestination 数据去向
     * @return 包含符合条件的任务数据血缘信息的列表
     */
    List<TaskData> getTaskDataLineageInfoByDataDestination(String dataDestination);

    /**
     * 根据数据来源查询相关任务的数据血缘信息列表
     * @param dataSource 数据来源
     * @return 包含符合条件的任务数据血缘信息的列表
     */
    List<TaskData> getTaskDataLineageInfoByDataSource(String dataSource) ;


    /**
     * 删除指定任务的数据血缘信息
     * @param taskId 任务的唯一标识符
     * @return 删除是否成功，成功返回 true，失败返回 false
     */
    boolean deleteTaskDataLineageInfo(String taskId);

    /**
     * 更新指定任务的数据血缘信息
     * @param taskId 任务的唯一标识符
     * @param newDataLineageInfo 新的数据血缘信息
     * @return 更新是否成功，成功返回 true，失败返回 false
     */
    boolean updateTaskDataLineageInfo(String taskId, DataLineage newDataLineageInfo);


    /**
     * 上传任务数据到 MidData
     * @param taskData 要上传的任务数据
     * @return 上传是否成功，成功返回 true，失败返回 false
     */
    boolean uploadTaskDataToMidData(TaskData taskData);


    /**
     * 上传任务数据到 MidHub
     * @param taskData 要上传的任务数据
     * @return 上传是否成功，成功返回 true，失败返回 false
     */
    boolean uploadTaskDataToMidHub(Task taskData);


    /**
     * 获取所有任务的数据血缘关系图
     *
     * @return 包含所有任务数据血缘关系图的对象
     */
    List<DataLineage> getAllTasksDataLineageGraph();


    /**
     * 获取指定任务的数据血缘信息
     *
     * @param taskId 任务的唯一标识符
     * @return 包含指定任务数据血缘信息的对象，包括数据来源、数据去向、数据依赖关系等，若任务不存在则返回 null
     */
    DataLineage getTaskDataLineageInfo(String taskId);

}
