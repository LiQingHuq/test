package com.yunrun.znbptapi.domain;

import lombok.Data;
import java.util.*;

/**
 * Created by FrankChen on 2018/1/3.
 */
@Data
public class ReadInfo {
    private String meterCode;//水表编码，设备唯一编号

    private String currentReading;//抄表读数（单位：立方米）

    private String pressure;//压力值（单位：kPa）

    private Integer valveStatus;//阀门状态（0：关阀，1：开阀，2：阀门故障，-1：不带阀门）

    private Integer signalStrength;//信号强度（单位：db）

    private String readTime;//数据采集时间。时间格式：yyyy-MM-dd HH:mm:ss如：2017-07-11 12:00:00

    private List<IntervalFlowInfo> intervalFlows;

    private Integer readStatus;//抄读状态。1：正常；2：异常
}
