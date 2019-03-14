package com.yunrun.znbptapi.bo;

import com.yunrun.znbptapi.domain.DeviceInfo;
import com.yunrun.znbptapi.domain.ReadInfo;
import com.yunrun.znbptapi.query.DeviceQuery;
import com.yunrun.znbptapi.query.ReadQuery;

import java.util.*;

/**
 * Created by FrankChen on 2018/1/2.
 */
public interface DeviceBo {
    Map listDeviceInfo(DeviceQuery deviceQuery);

    DeviceInfo findDeviceInfo(DeviceQuery deviceQuery);

    Map listReadInfo(ReadQuery readQuery);

    Map findReadInfo(ReadQuery readQuery);
}
