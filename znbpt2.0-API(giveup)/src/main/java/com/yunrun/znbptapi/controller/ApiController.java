package com.yunrun.znbptapi.controller;

import com.yunrun.znbptapi.bo.DeviceBo;
import com.yunrun.znbptapi.domain.DeviceInfo;
import com.yunrun.znbptapi.query.DeviceQuery;
import com.yunrun.znbptapi.query.ReadQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by FrankChen on 2018/1/2.
 */
@RestController
@RequestMapping("/znbpt/v1.0.0/")
public class ApiController {

    @Autowired
    private DeviceBo deviceBo;

    /**
     *类功能描述：该方法用于批量查询设备信息,status
     * 水表状态。1：正常；2：停用;目前116数据库上该字段没有使用
     *https://server:port/znbpt/v1.0.0/devices?companyCode={companyCode}&startTime={startTime}&endTime={endTime}&status={status}
     *
     *@author cfx
     *@date 2018/1/3
     */
    @RequestMapping(value = "devices",method = RequestMethod.GET)
    @ResponseBody
    public Map devices( @RequestParam(value = "companyCode", required = false) String companyCode,
                           @RequestParam(value = "startTime", required = false) String startTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "status", required = false) int status){
        DeviceQuery deviceQuery = new DeviceQuery();
        deviceQuery.setCompanyCode(companyCode);
        deviceQuery.setStartTime(startTime);
        deviceQuery.setEndTime(endTime);
        deviceQuery.setStatus(status);
        return deviceBo.listDeviceInfo(deviceQuery);
    }

    /**
     *类功能描述：查询单个设备的信息，用于快速获取某一设备的信息。
     * https://server:port/znbpt/v1.0.0/devices/{companyCode}/{meterCode}
     *
     *@author cfx
     *@date 2018/1/3
     */
    @RequestMapping(value = "devices/{companyCode}/{meterCode}",method = RequestMethod.GET)
    @ResponseBody
    public DeviceInfo devices( @PathVariable(value = "companyCode") String companyCode,
                               @PathVariable(value = "meterCode") String meterCode
                       ){
        DeviceQuery deviceQuery = new DeviceQuery();
        deviceQuery.setCompanyCode(companyCode);
        deviceQuery.setMeterCode(meterCode);
        return deviceBo.findDeviceInfo(deviceQuery);
    }

    /**
     *类功能描述：按条件查询设备的抄表读数
     * https://server:port/znbpt/v1.0.0/readings?companyCode={companyCode}&startTime={startTime}&endTime={endTime}&readStatus={readStatus}
     *
     *@author cfx
     *@date 2018/1/3
     */
    @RequestMapping(value = "readings",method = RequestMethod.GET)
    @ResponseBody
    public Map readings( @RequestParam(value = "companyCode", required = false) String companyCode,
                               @RequestParam(value = "startTime", required = false) String startTime,
                               @RequestParam(value = "endTime", required = false) String endTime,
                               @RequestParam(value = "readStatus", required = false) int readStatus
    ){
        ReadQuery readQuery = new ReadQuery();
        readQuery.setCompanyCode(companyCode);
        readQuery.setEndTime(endTime);
        readQuery.setStartTime(startTime);
        readQuery.setReadStatus(readStatus);
        return deviceBo.listReadInfo(readQuery);
    }

    /**
     *类功能描述：按条件查询单一设备的抄表读数
      *https://server:port/znbpt/v1.0.0/readings/{companyCode}/{meterCode}?startTime={startTime}&endTime={endTime}&readStatus={readStatus}     *
     *@author cfx
     *@date 2018/1/3
     */
    @RequestMapping(value = "readings/{companyCode}/{meterCode}",method = RequestMethod.GET)
    @ResponseBody
    public Map readings( @PathVariable(value = "companyCode", required = false) String companyCode,
                         @RequestParam(value = "startTime", required = false) String startTime,
                         @RequestParam(value = "endTime", required = false) String endTime,
                         @RequestParam(value = "readStatus", required = false) int readStatus,
                         @PathVariable(value = "meterCode", required = false) String meterCode
    ){
        ReadQuery readQuery = new ReadQuery();
        readQuery.setCompanyCode(companyCode);
        readQuery.setMeterCode(meterCode);
        readQuery.setEndTime(endTime);
        readQuery.setStartTime(startTime);
        readQuery.setReadStatus(readStatus);
        return deviceBo.findReadInfo(readQuery);
    }

}



















