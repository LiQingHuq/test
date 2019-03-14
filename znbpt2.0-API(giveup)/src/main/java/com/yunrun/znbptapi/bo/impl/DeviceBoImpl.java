package com.yunrun.znbptapi.bo.impl;

import com.yunrun.znbptapi.bo.DeviceBo;
import com.yunrun.znbptapi.dao.ApiDao;
import com.yunrun.znbptapi.domain.DeviceInfo;
import com.yunrun.znbptapi.domain.IntervalFlow;
import com.yunrun.znbptapi.domain.IntervalFlowInfo;
import com.yunrun.znbptapi.domain.ReadInfo;
import com.yunrun.znbptapi.query.DeviceQuery;
import com.yunrun.znbptapi.query.ReadQuery;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by FrankChen on 2018/1/2.
 */
@Component
public class DeviceBoImpl implements DeviceBo {

    @Autowired
    @Getter
    public ApiDao apiDao;

    /**
      *类功能描述：该方法用于批量查询设备信息,status
     * 水表状态。1：正常；2：停用;目前116数据库上该字段没有使用
      *https://server:port/znbpt/v1.0.0/devices?companyCode={companyCode}&startTime={startTime}&endTime={endTime}&status={status}
     *
      *@author cfx
      *@date 2018/1/3
      */
    
    @Override
    public Map listDeviceInfo(DeviceQuery deviceQuery) {
        Map map = new HashMap<>();
        List<DeviceInfo> list = apiDao.listDeviceInfo(deviceQuery);
        map.put("totalCount",list.size());
        map.put("devices",list);
        return map;
    }

    /**
     *类功能描述：查询单个设备的信息，用于快速获取某一设备的信息。
     * https://server:port/znbpt/v1.0.0/devices/{companyCode}/{meterCode}
     *
     *@author cfx
     *@date 2018/1/3
     */
    @Override
    public DeviceInfo findDeviceInfo(DeviceQuery deviceQuery) {
        return apiDao.findDeviceInfo(deviceQuery);
    }

    @Override
    public Map listReadInfo(ReadQuery readQuery) {
        Map map = new HashMap<>();
        List<ReadInfo> list = apiDao.listReadInfo(readQuery);
        if (readQuery.getReadStatus()==1){//1.1:抄读正常；2：异常
            List<IntervalFlow> listFlow =apiDao.listIntervalFlowAll(readQuery);
            for (ReadInfo readInfo : list){
                readInfo.setReadStatus(1); //2.设置抄读状态
                List<IntervalFlowInfo> list1= new ArrayList<>();
                //3.根据时间与meterCode判断是否相同
                for (IntervalFlow intervalFlow:listFlow){
                    if (readInfo.getMeterCode().equals(intervalFlow.getMeterCode()) && readInfo.getReadTime().substring(0,11).equals(intervalFlow.getFlowTime().substring(0, 11))){
                        //list1.add(intervalFlow);
                        IntervalFlowInfo intervalFlowInfo = new IntervalFlowInfo();
                        intervalFlowInfo.setFlow(intervalFlow.getFlow());
                        intervalFlowInfo.setFlowTime(intervalFlow.getFlowTime());
                        list1.add(intervalFlowInfo);
                    }
                }
                readInfo.setIntervalFlows(list1);
            }
        }else {
            List<IntervalFlow> listFlow =apiDao.listIntervalFlowAll(readQuery);
            for (ReadInfo readInfo : list){
                readInfo.setReadStatus(2); //1.设置抄读状态
                List<IntervalFlowInfo> list1= new ArrayList<>();
                for (IntervalFlow intervalFlow:listFlow){
                    if (readInfo.getMeterCode().equals(intervalFlow.getMeterCode()) && readInfo.getReadTime().substring(0,11).equals(intervalFlow.getFlowTime().substring(0, 11))){
                        //list1.add(intervalFlow);
                        IntervalFlowInfo intervalFlowInfo = new IntervalFlowInfo();
                        intervalFlowInfo.setFlow(intervalFlow.getFlow());
                        intervalFlowInfo.setFlowTime(intervalFlow.getFlowTime());
                        list1.add(intervalFlowInfo);
                    }
                }
                readInfo.setIntervalFlows(list1);
            }
        }
        map.put("totalCount",list.size());
        map.put("readings",list);
        return map;
    }

    @Override
    public Map findReadInfo(ReadQuery readQuery) {
        Map map = new HashMap<>();
        List<ReadInfo> list = apiDao.findReadInfo(readQuery);
        if (readQuery.getReadStatus()==1){//1.1:抄读正常；2：异常
            List<IntervalFlow> listFlow =apiDao.listIntervalFlow(readQuery);
            for (ReadInfo readInfo : list){
                readInfo.setReadStatus(1); //2.设置抄读状态
                List<IntervalFlowInfo> list1= new ArrayList<>();
                //3.将时间相同的放一起
                for (IntervalFlow intervalFlow:listFlow){
                    if (readInfo.getReadTime().substring(0,11).equals(intervalFlow.getFlowTime().substring(0, 11))){
                        IntervalFlowInfo intervalFlowInfo = new IntervalFlowInfo();
                        intervalFlowInfo.setFlow(intervalFlow.getFlow());
                        intervalFlowInfo.setFlowTime(intervalFlow.getFlowTime());
                        list1.add(intervalFlowInfo);
                    }
                }
                readInfo.setIntervalFlows(list1);
            }
        }else {
            List<IntervalFlow> listFlow =apiDao.listIntervalFlow(readQuery);
            for (ReadInfo readInfo : list){
                readInfo.setReadStatus(2); //1.设置抄读状态
                List<IntervalFlowInfo> list1= new ArrayList<>();
                for (IntervalFlow intervalFlow:listFlow){
                    if (readInfo.getReadTime().substring(0,11).equals(intervalFlow.getFlowTime().substring(0,11))){
                        //list1.add(intervalFlow);
                        IntervalFlowInfo intervalFlowInfo = new IntervalFlowInfo();
                        intervalFlowInfo.setFlow(intervalFlow.getFlow());
                        intervalFlowInfo.setFlowTime(intervalFlow.getFlowTime());
                        list1.add(intervalFlowInfo);
                    }
                }
                readInfo.setIntervalFlows(list1);
            }
        }
        map.put("totalCount",list.size());
        map.put("readings",list);
        return map;
    }


}
