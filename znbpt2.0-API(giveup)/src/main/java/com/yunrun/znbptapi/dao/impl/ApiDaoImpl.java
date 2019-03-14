package com.yunrun.znbptapi.dao.impl;

import com.yunrun.znbptapi.dao.ApiDao;
import com.yunrun.znbptapi.domain.DeviceInfo;
import com.yunrun.znbptapi.domain.Operator;
import com.yunrun.znbptapi.domain.ReadInfo;
import com.yunrun.znbptapi.domain.SysUser;
import com.yunrun.znbptapi.query.DeviceQuery;
import com.yunrun.znbptapi.query.ReadQuery;
import com.yunrun.znbptapi.query.SysRoleQuery;
import lombok.Getter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by FrankChen on 2018/1/2.
 */
@Component
public class ApiDaoImpl implements ApiDao {
    @Autowired
    @Getter
    public SqlSessionTemplate sqlSessionTemplate;

    @Override
    public SysUser findByUsername(String name) {
        return getSqlSessionTemplate().selectOne(getStatementNameWrap("findSysUser"),name);
    }

    @Override
    public boolean checkUser(SysRoleQuery query) {
        int s= getSqlSessionTemplate().selectOne("checkUser",query);
        if (s>0)
            return true;
        else
            return false;
    }

    @Override
    public Operator findOperator(String name) {
        return getSqlSessionTemplate().selectOne(getStatementNameWrap("findOperator"),name);
    }

    @Override
    public List listDeviceInfo(DeviceQuery deviceQuery) {
        return getSqlSessionTemplate().selectList(getStatementNameWrap("listDeviceInfo"),deviceQuery);
    }

    @Override
    public DeviceInfo findDeviceInfo(DeviceQuery deviceQuery) {
        return getSqlSessionTemplate().selectOne(getStatementNameWrap("findDeviceInfo"),deviceQuery);
    }

    @Override
    public List listReadInfo(ReadQuery readQuery) {
        return getSqlSessionTemplate().selectList(getStatementNameWrap("listReadInfo"),readQuery);
    }

    @Override
    public List listIntervalFlow(ReadQuery readQuery) {
        return getSqlSessionTemplate().selectList(getStatementNameWrap("listIntervalFlow"),readQuery);
    }

    @Override
    public List listIntervalFlowAll(ReadQuery readQuery) {
        return getSqlSessionTemplate().selectList(getStatementNameWrap("listIntervalFlowAll"),readQuery);
    }

    @Override
    public List<ReadInfo> findReadInfo(ReadQuery readQuery) {
        return getSqlSessionTemplate().selectList(getStatementNameWrap("findReadInfo"), readQuery);
    }


    /**
     * 方法功能描述: 构造mybatis sql路径，拼接命名空间路径
     *
     * @param name 需要拼接的路径
     * @return sql路径
     *
     */
    protected String getStatementNameWrap(String name) {
        return getNameSpace() + "." + name;
    }

    /**
     * 方法功能描述: 通过子类名得到命名空间路径
     *
     * @return 命名空间路径
     *
     */
    private String getNameSpace() {
        return this.getClass().getName();
    }
}
