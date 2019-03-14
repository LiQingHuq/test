package cn.scetc.web.service;

import cn.scetc.web.entity.Blogger;

public interface BloggerServiceI {

	/**
	 * 查询博主信息
	 * @return
	 */
	public Blogger find();
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName,String password);
	
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public void update(Blogger blogger);
}
