package entity;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -16 14:43
 * @Modified By:
 */
public class Constants {
    public static final String SOURCES = "sources";
    public static final String SOURCES_TYPE = "source_type";

    public static final String WORKFLOWS = "workflows";
    public static final String SINKS = "sinks";
    public static final String SINK_TYPE = "sink_type";
    public static final String OPERATOR_TYPE = "op";
    public static final String UID = "uid";
    public static final String INPUT = "input";
    public static final String OUTPUT = "output";



    /*source算子名*/
    public static final String MYSQL_SOURCE = "mysql_source";
    public static final String CSV_SOURCE = "csv_source";

    /*workflow算子名*/
    public static final String SELECT = "select";
    public static final String FILTER = "filter";
    public static final String JOIN = "join";
    public static final String SQL = "sql";
    public static final String TEMP_STORAGE = "temp_storage";
    public static final String SHOW = "show";
    public static final String GROUPBY = "groupBy";

    /*sink算子名*/
    public static final String MYSQL_SINK = "mysql_sink";
    public static final String CSV_SINK = "csv_sink";




}
