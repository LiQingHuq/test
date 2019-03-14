package cn.scetc.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.scetc.web.dao.CommentDaoI;
import cn.scetc.web.entity.Comment;
import cn.scetc.web.service.CommentServiceI;

@Service(value="commentServiceImpl")//<bean id="commentServiceImpl" class="cn.scetc.web.service.impl.CommentServiceImpl"/>
public class CommentServiceImpl implements CommentServiceI{
	@Autowired 
	private CommentDaoI commentDao;//<property id=blogDao ref="blogDaoImpl"/>
	
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	@Override
	@Transactional
	public int add(Comment comment) {
		// TODO Auto-generated method stub
		return commentDao.add(comment);
	}

	/**
	 * 查找用户评论信息
	 * @param map
	 * @return
	 */
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	@Override
	@Transactional
	public int getTotal() {
		return commentDao.getTotal();
	}
	
	/**
	 * 删除评论信息
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public void delete(Comment comment) {
	   commentDao.delete(comment);
	}

	@Override
	@Transactional
	public List<Comment> query(int pc, int ps){
		// TODO Auto-generated method stub
		return commentDao.query(pc, ps);
	}
	@Override
	@Transactional
	public Comment findById(int id){
		// TODO Auto-generated methd stub
		return commentDao.findById(id);
	}

	@Override
	public List<Comment> countList(int id) {
		// TODO Auto-generated method stub
		return commentDao.countList(id);
	}

}
