package com.inter;

/**
 * Created by 蔡永汪 on 2017/10/26.
 */

public interface BaseConfig {
    boolean log = true;//log日志开关
    String log_tag = "timo";//log_tag日志标记
    int pageSize = 10;//分页--默认十条数据
    boolean bgService = true;//开启后台服务--默认开启

}
