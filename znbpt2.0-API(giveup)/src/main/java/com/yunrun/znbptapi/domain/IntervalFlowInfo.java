package com.yunrun.znbptapi.domain;

import lombok.Data;

/**
 * Created by FrankChen on 2018/1/4.
 */
@Data
public class IntervalFlowInfo {
    private String flow;//间隔流量（单位：升）

    private String flowTime;//间隔流量时间。时间格式：yyyy-MM-dd HH:mm:ss如：2017-07-11 12:00:00
}
