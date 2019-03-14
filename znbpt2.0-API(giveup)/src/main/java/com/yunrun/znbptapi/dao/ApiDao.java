package com.yunrun.znbptapi.dao;

import com.yunrun.znbptapi.domain.DeviceInfo;
import com.yunrun.znbptapi.domain.Operator;
import com.yunrun.znbptapi.domain.ReadInfo;
import com.yunrun.znbptapi.domain.SysUser;
import com.yunrun.znbptapi.query.DeviceQuery;
import com.yunrun.znbptapi.query.ReadQuery;
import com.yunrun.znbptapi.query.SysRoleQuery;
import java.util.*;

/**
 * Created by FrankChen on 2018/1/2.
 */
public interface ApiDao {
    SysUser findByUsername(String name);

    boolean checkUser(SysRoleQuery query);

    Operator findOperator(String name);

    List listDeviceInfo(DeviceQuery deviceQuery);

    DeviceInfo findDeviceInfo(DeviceQuery deviceQuery);

    List listReadInfo(ReadQuery readQuery);

    List listIntervalFlow(ReadQuery readQuery);

    List listIntervalFlowAll(ReadQuery readQuery);

    List<ReadInfo> findReadInfo(ReadQuery readQuery);
}
