package com.yunrun.znbptapi.bo.impl;

import com.yunrun.znbptapi.dao.ApiDao;
import com.yunrun.znbptapi.domain.Operator;
import com.yunrun.znbptapi.domain.SysUser;
import com.yunrun.znbptapi.util.PasswordEncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    ApiDao sysUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    public String checkUser(Operator operator,String originPassword){
        if (operator != null) {
            String password = operator.getPassword();
            String num = operator.getEncryptSalt();
            String loginPassword = null;
            try {
                loginPassword = PasswordEncryptionUtil.getEncryptedPassword(password, num);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
           return loginPassword;
        }else {
            return "";
        }
    }




   /* public boolean validate(SysUser user) {
        SysUser entity = sysUserRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (entity != null) {
            return true;
        }
        return false;
    }

    public boolean save(SysUser user) {
        sysUserRepository.save(user);
        return true;
    }

    public boolean delete(Long id) {
        sysUserRepository.delete(id);
        return true;
    }

    public boolean edit(SysUser user) {
        sysUserRepository.save(user);
        return true;
    }

    public boolean exits(SysUser user) {
        SysUser entity = sysUserRepository.findByUsername(user.getUsername());
        if (entity != null) {
            return true;
        }
        return false;
    }*/
}
