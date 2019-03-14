package com.yunrun.znbptapi.query;

import lombok.Data;

/**
 * Created by FrankChen on 2018/1/2.
 */
@Data
public class DeviceQuery {
    private String companyCode;

    private String startTime;

    private String endTime;

    private Integer status;

    private String meterCode;
}
