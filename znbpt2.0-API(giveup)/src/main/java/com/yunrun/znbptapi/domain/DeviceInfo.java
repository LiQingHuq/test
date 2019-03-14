package com.yunrun.znbptapi.domain;

import lombok.Data;

/**
 * Created by FrankChen on 2018/1/2.
 */
@Data
public class DeviceInfo {
    private String customerNo;//客户编码
    private String meterCode;//水表编码，设备唯一标识。
    private String meterName;//水表名称
    private String hasValve;//是否带阀门。0：不带阀门；1：带阀门
    private String valveStatus;//阀门状态。0：开阀；1: 关阀；2：阀门异常；-1：不带阀门
    private String status;//水表状态。1：正常；2：停用
    private String installTime;//安装时间。时间格式：yyyy-MM-dd HH:mm:ss如：2017-07-11 12:00:00
    private String installAddr;//安装地址
}
