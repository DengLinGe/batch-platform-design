package nju.edu.taskinfoperspective.entity;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -21 14:54
 * @Modified By:
 */
public class ResourceConfig {
    // CPU 核数
    private int cpuCores;
    // 内存大小，单位为 MB
    private int memoryMB;
    // 并行度
    private int parallelism;
    // 每个 executor 的 CPU 核数
    private int executorCpuCores;
    // 每个 executor 的内存大小，单位为 MB
    private int executorMemoryMB;
    // executor 的数量
    private int numExecutors;
}
