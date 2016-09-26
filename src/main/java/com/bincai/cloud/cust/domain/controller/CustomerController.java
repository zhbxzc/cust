package com.bincai.cloud.cust.domain.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bincai.cloud.cust.domain.model.Customer;
import com.bincai.cloud.cust.domain.service.CustService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/cust")
@Api(value="cust",description="客户领域服务接口")
public class CustomerController {
	@Resource
	private CustService custService;

	@ApiOperation(value="客户新增服务",notes="客户新增服务接口")
	@ApiImplicitParam(name="customer",value="客户数据对象JSON",required=true,paramType="body")
   @RequestMapping(value="/api/v1/customers",method=RequestMethod.POST)
   @HystrixCommand(fallbackMethod = "hystrixjsonQuery")
   public String register(@RequestBody String customerInfo){
	  Customer customer=JSON.parseObject(customerInfo, Customer.class);
	customer.setId(UUID.randomUUID().toString().replace("-", ""));
		String result=custService.register(customer);	
	    return result;	   
   }
	@ApiOperation(value="客户信息修改",notes="客户信息变更接口")
	@ApiImplicitParam(name="customerInfo",value="客户信息JSON",required=true,paramType="body")
   @RequestMapping(value="/api/v1/customers/{id}",method=RequestMethod.PUT)
   @HystrixCommand(fallbackMethod = "hystrixjsonQuery")
   public String alter(@RequestBody String customerInfo){
	   Customer customer=JSON.parseObject(customerInfo, Customer.class);
		String result=custService.alter(customer);	
	    return result;
   }
	@ApiOperation(value="客户信息查询",notes="客户信息查询接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="name",value="客户姓名",required=false,paramType="query",dataType="String")
})
	@RequestMapping(value = "/api/v1/customers", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@HystrixCommand(fallbackMethod = "searchhystrixjsonQuery")
	public String search(Customer customer) {
		if(customer.getPageIndex()!=null&&customer.getPageSize()!=null){
			customer.offset();
		}
		List<Customer> list = custService.search(customer);
		String Array =JSONArray.toJSONString(list);
		return Array;
	}
	@ApiOperation(value="单个客户信息查询",notes="单个客户信息查询接口")
   @RequestMapping(value="/api/v1/customers/{id}",method=RequestMethod.GET)
   @HystrixCommand(fallbackMethod = "hystrixidQuery")
   public String getById(@PathVariable("id") String id){
	   Customer cust = custService.getById(id);
	   String result = JSONObject.toJSONString(cust);
		return result;
   }
	@ApiOperation(value="客户信息总条数查询",notes="客户信息总条数查询接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name="name",value="客户名称",required=false,paramType="query",dataType="String")
	})
	@RequestMapping(value = "/api/v1/customers/counts", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@HystrixCommand(fallbackMethod = "searchhystrixjsonQuery")
	public String searchCount(Customer customer) {
		if(customer.getPageIndex()!=null&&customer.getPageSize()!=null){
			customer.offset();
		}
		int count = custService.searchCount(customer);
		return String.valueOf(count);
	}
	@ApiOperation(value="删除单个公客户信息",notes="删除单个公客户信息接口")
   @RequestMapping(value="/api/v1/customers/{id}",method=RequestMethod.DELETE)
   @HystrixCommand(fallbackMethod = "hystrixidQuery")
   public String delete(@PathVariable("id") String id) throws UnsupportedEncodingException{
	   String result=  custService.delete(id);		 
		 return result;
   }
	
	public String hystrixQuery()
	{
		return "{\"result\":\"error\"}";
	}
	public String hystrixidQuery(String id)
	{
		return "{\"result\":\"error\"}";
	}
	public String hystrixjsonQuery(String customerInfo)
	{
		return "{\"result\":\"error\"}";
	}
	public String searchhystrixjsonQuery(Customer customer)
	{
		return "{\"result\":\"error\"}";
	}
}
