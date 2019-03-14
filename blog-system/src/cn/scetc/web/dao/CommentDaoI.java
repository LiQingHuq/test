package cn.scetc.web.dao;

import java.util.List;
import java.util.Map;

import cn.scetc.web.entity.Blog_Type;
import cn.scetc.web.entity.Comment;
import cn.scetc.web.entity.PageBean;

public interface CommentDaoI {

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * 修改评论
	 * @param comment
	 * @return
	 */
	public void update(Comment comment);
	
	/**
	 * 查找用户评论信息
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
	 * 通过id查询评论对象
	 * @param id
	 * @return
	 */
	public Comment findById(int id);

	public List<Comment> countList(int id);
}
