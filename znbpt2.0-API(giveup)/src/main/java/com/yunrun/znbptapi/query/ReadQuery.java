package com.yunrun.znbptapi.query;

import lombok.Data;

/**
 * Created by FrankChen on 2018/1/3.
 */
@Data
public class ReadQuery {
    private String companyCode;

    private String startTime;

    private String endTime;

    private Integer readStatus;

    private String meterCode;

    private String intervalFlowStartTime;

    private String intervalFlowEndTime;
}
