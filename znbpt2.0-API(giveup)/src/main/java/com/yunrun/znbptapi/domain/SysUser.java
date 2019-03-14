package com.yunrun.znbptapi.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Data
public class SysUser implements UserDetails {


    public SysUser() {
    }

    public SysUser(String username, String password) {
        this.id = id;
        this.appId = username;
        this.secret = password;
    }

    public SysUser(Long id, String username, String password) {
        this.appId = username;
        this.secret = password;
    }

    @Id
    @GeneratedValue
    private Long id;
    /*private String username;
    private String password;*/
    private String appId;
    private String secret;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<SysRole> roles;

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        /*cfx,暂时没有角色*/
        /*List<SysRole> roles = this.getRoles();
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getName()));
        }*/
        return auths;
    }

    @Override
    public String getPassword() {
        return this.secret;
    }

    @Override
    public String getUsername() {
        return this.appId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
