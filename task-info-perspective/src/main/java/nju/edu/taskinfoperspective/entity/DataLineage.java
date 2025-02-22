package nju.edu.taskinfoperspective.entity;

import lombok.Data;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -20 16:24
 * @Modified By:
 */
@Data
public class DataLineage {

    private Long id;
    private String source;
    private String target;

}
