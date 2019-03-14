package com.yunrun.znbptapi.domain;

import lombok.Data;

/**
 * 类功能描述：操作员信息
 *
 *  @author xys
 * @date 2017/4/14
 */
@Data
public class Operator {
    //操作员id
    private int operatorId;
    //水司id
    private int companyId;
    //操作员编码
    private String operatorCode;
    //操作员姓名
    private String operatorName;
    //操作员类型
    private int operatorType;
    //操作员级别
    private int operatorLevel;
    //登录名
    private String loginName;
    //登录密码
    private String password;
    //加密盐值
    private String encryptSalt;
    //办公电话
    private String officePhone;
    //手机
    private String mobilePhone;
    //状态
    private int status;
    //电子邮箱
    private String email;
    //创建时间
    private String createTime;
    //截止时间
    private String deadline;
    //修改时间
    private String modifyTime;
    //公司名称
    private String companyName;
    //操作类型名称
    private String operatorTypeName;
    //操作员等级名称
    private String operatorLevelName;
    //状态名称
    private String statusName;
    //检查密码
    private String checkPassword;
    //角色id
    private String roleId;
    //角色名称
    private String roleName;
    //角色备注
    private String roleMemo;
    //片区id
    private int regionId;
    //片区名称
    private String regionName;
    //片区类型
    private String regionType;
    //片区备注
    private String regionMemo;
    //初始化密码
    private String newPassword;

}
