package com.bincai.cloud.cust.domain.service;

import static com.bincai.cloud.cust.domain.common.CommonUtil.setResult;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bincai.cloud.cust.domain.dao.CustomerMapper;
import com.bincai.cloud.cust.domain.model.Customer;

@Service("custService")
public class CustService {
	@Resource
	private CustomerMapper customerMapper;
	
	/**
	 * 注册
	 * FIXME 
	 * @param 信息的JSON串
	 * @return 注册结果
	 */
	@Transactional
	public String register(Customer customer){

		customer.setCreatedTime(new Date());
		customerMapper.insertSelective(customer);
		return setResult(customer, true, "新增成功");
	}
	
	/**
	 * 根据ID删除一条信息
	 * @param id
	 * @return 删除成功标志
	 * 
	 */
	public String delete(String id) {
		int result = 0;
		String flag = "0";
		try {
			result = customerMapper.delete(id);

		} catch (Exception e) {
			System.out.println(e);
		}
		if (result > 0) {
			flag = "1";
		} else {
			flag = "0";
		}
		return flag;
	}
		

	
	/**
	 * 根据查询条件返回列表
	 * @return PageBean
	 * @author 
	 * @param  
	 * @since 2016/8/15 
	 */
	public List<Customer> search(Customer customer){
		//查询某一页的所有记录 含有offset和size,如0-7,8-15
		List<Customer> List= customerMapper.selectByCondition(customer);
		//返回当前页面所有的记录信息
		return List;
	}
	
	
	/**
	 * 根据查询条件返回列表条数
	 * @return
	 * @author 
	 * @since 2016/8/16 
	 */
	public int searchCount(Customer customer) {
		int count = customerMapper.searchCount(customer);
		return count;
	}
	
	/**
	 * 根据ID查询信息
	 * @return 
	 * @author 
	 * @since 2016/8/16 
	 */
	public Customer getById(String id) {
		return customerMapper.selectById(id);
	}
	
	
	/**
	 * FIXME 修改信息 
	 * @param 
	 * @return 
	 */
	public String alter(Customer customer){
	
		customer.setLastUpdatedTime(new Date());
		// 更新信息
		int num = customerMapper.updateByPrimaryKeySelective(customer);
		if(num>0){
			return setResult(customer, true, "修改成功");
		}else{
			return setResult(customer, false, "修改失败");
		}
	}
	
}
