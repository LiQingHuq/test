package cn.scetc.web.service;

import java.util.List;
import cn.scetc.web.entity.Comment;

public interface CommentServiceI {

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * 查找用户评论信息,分页
	 * @param map
	 * @return
	 */
	public List<Comment> query(int pc, int ps);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public int getTotal();
	
	/**
	 * 删除评论信息
	 * @param comment
	 * @return
	 */
	public void delete(Comment comment);
	/**
	 * 通过id查询博客类型，返回博客对象
	 * @param id
	 * @return
	 */
	public Comment findById(int id);

	public List<Comment> countList(int id);
}
