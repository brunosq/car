package com.zt3000.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zt3000.common.BaseController;
import com.zt3000.service.CompanyService;

@Controller
@RequestMapping("/center")
public class CenterController  extends BaseController{
	
	@Autowired
	private CompanyService companyService;
	
	/**
	 * 企业集合
	 * @return
	 */
	@RequestMapping(value = "getCompanyList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCompanys() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getCompanyList(param);
		return object;
	}

    /**
     * 省份企业集合
     * @return
     */
    @RequestMapping(value = "getProCompanyList", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getProCompanyList() {
        String province=request.getParameter("province");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        Object object = companyService.getProCompanyList(param);
        return object;
    }

	/**
	 * 待报价货源
	 * @return
	 */
	@RequestMapping(value = "getGoodsList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getGoodsList() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getGoodsList(param);
		return object;
	}
	
	
	
	/**
	 * 省份区域信息
	 * @return
	 */
	@RequestMapping(value = "getProvinceInfo", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getProvinceInfo() {
		String province=request.getParameter("province");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		return companyService.getProvinceInfo(param);
	}
	
	/**
	 * 全国交易额
	 */
	@RequestMapping(value = "getTotalTradeMoney", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getTotalTradeMoney(String agent_code) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(agent_code)){
			param.put("agent_code", agent_code);
		}
		return companyService.getTotalTradeMoney(param);
	}
	
	/**
	 * 企业详情
	 * @return
	 */
	@RequestMapping(value = "getCompanyDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCompanyDetail() {
		String companyId=request.getParameter("companyId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("companyId", companyId);
		return companyService.getCompanyDetail(param);
	}
	
	/**
	 * 货源详情
	 * @return
	 */
	@RequestMapping(value = "getGoodsDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getGoodsDetail() {
		String goodsId=request.getParameter("goodsId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("goodsId", goodsId);
		return companyService.getGoodsDetail(param);
	}
	
	/**
	 * 订单详情
	 * @return
	 */
	@RequestMapping(value = "getOrderDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderDetail() {
		String orderId=request.getParameter("orderId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		return companyService.getOrderDetail(param);
	}
	
	
	/**
	 * 订单list,地图上绘点
	 * [[{name:'南京'},{name:'广东'}],[{name:'南京'},{name:'广东'}]]
	 */
	@RequestMapping(value = "getOrderList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderList() {
		Map<String,Object> param = new HashMap<String,Object>();
		
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list=(List<Map<String,String>>)companyService.getOrderList(param);
		
		List<Object> arealist=new ArrayList<Object>();
		
		for(Map<String,String> map:list){
			
			List<Map<String,String>> areaItem=new ArrayList<Map<String,String>>();
			
			Set<String> keys=map.keySet();
			for(String key:keys){
				Map<String,String> temp=new HashMap<String,String>();
				temp.put("name", map.get(key));
				areaItem.add(temp);
			}
			
			arealist.add(areaItem);
		}
		
		return arealist;
	}
	
	
	/**
	 * 根据出发地，目的地查询订单list
	 */
	@RequestMapping(value = "getOrderListByCity", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderListByCity() {
		String startCity=request.getParameter("startCity");
		String destCity=request.getParameter("destCity");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("startCity", startCity.trim());
		param.put("destCity", destCity.trim());
		return companyService.getOrderListByCity(param);
	}
	
	/**
	 * 货源发布率，货源报价率，货源成单率，货源发布同比增长，货源报价同比增长，货源成单同比增长
	 * @return
	 */
	@RequestMapping(value = "getGoodsRate", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getGoodsRate(String agent_code) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(agent_code)){
			param.put("agent_code", agent_code);
		}
		return companyService.getGoodsRate(param);
	}
	
	/**
	 * 闲置的车辆list
	 */
	@RequestMapping(value = "getUnusedCarList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getUnusedCarList() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		return companyService.getUnusedCarList(param);
	}


    /**
     * 省份 闲置的车辆list
     */
    @RequestMapping(value = "getProUnusedCarList", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getProUnusedCarList() {
        String province=request.getParameter("province");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        return companyService.getProUnusedCarList(param);
    }
	
	/**
	 * 闲置车辆详情
	 */
	@RequestMapping(value = "showCarDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object showCarDetail() {
		Map<String,Object> param = new HashMap<String,Object>();
		String carId=request.getParameter("carId");
		param.put("carId", carId);
		return companyService.showCarDetail(param);
	}
	
	/**
	 * 最近六个月交易额
	 */
	@RequestMapping(value = "getNearMonthTrade", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNearMonthTrade(String agent_code) {
		Map<String,Object> param = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(agent_code)){
			param.put("agent_code", agent_code);
		}
		return companyService.getNearMonthTrade(param);
	}
	
	/**
	 * 最近六个月交易额
	 */
	@RequestMapping(value = "getProvinceGoods", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getProvinceGoods() {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", request.getParameter("province"));
		return companyService.getProvinceGoods(param);
	}
	
	
	/*
	 新增手机版接口 
	 */
	/**
	 * 最近获取企业列表
	 */
	@RequestMapping(value = "queryCompanyList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object queryCompanyList(@RequestParam Map<String, Object> map) {
		return companyService.queryCompanyList(map);
	}
	
	
	/**
	 * 闲置的车辆
	 */
	@RequestMapping(value = "queryCarList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object queryCarList(@RequestParam Map<String, Object> map) {
		return companyService.queryCarList(map);
	}
	
	/**
	 * 在途订单
	 */
	@RequestMapping(value = "queryOnwayOrderList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object queryOnwayOrderList(@RequestParam Map<String, Object> map) {
		return companyService.queryOnwayOrderList(map);
	}	
	
	
	/**
	 * 注册增长趋势
	 */
	@RequestMapping(value = "getRegisterSpeed", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getRegisterSpeed(@RequestParam Map<String, Object> map) {
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
        return companyService.getRegisterSpeed(map);
	}	
	
	/**
	 * 企业区域分布 
	 */
	@RequestMapping(value = "getCompanyDistribution", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCompanyDistribution(@RequestParam Map<String, Object> map) {
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
        return companyService.getCompanyDistribution(map);
	}
	
    /**
     * 企业列表
     * @return
     */
    @RequestMapping(value = "getNewProCompanyList", produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object getNewProCompanyList() {
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        String county=request.getParameter("county");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
        param.put("county", county);
        return companyService.getNewProCompanyList(param);
    }
    
	/**
	 * 企业详情
	 * @return
	 */
	@RequestMapping(value = "getNewCompanyDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNewCompanyDetail() {
		String companyId=request.getParameter("companyId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("companyId", companyId);
		return companyService.getNewCompanyDetail(param);
	}
	
	/**
	 * 在途订单
	 */
	@RequestMapping(value = "getNewOnwayOrderList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object newQueryOnwayOrderList(@RequestParam Map<String, Object> map) {
		
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
		return companyService.getNewQueryOnwayOrderList(map);
	}	
	
	/**
	 * 货物类型占比
	 */
	@RequestMapping(value = "getGoodsTypePercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getGoodsTypePercent(@RequestParam Map<String, Object> map) {
		return companyService.getGoodsTypePercent(map);
	}	
	
	/**
	 * 订单地域占比
	 */
	@RequestMapping(value = "getOrderAreaPercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderAreaPercent(@RequestParam Map<String, Object> map) {
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
		return companyService.getOrderAreaPercent(map);
	}
	
	/**
	 * 订单交易额占比
	 */
	@RequestMapping(value = "getOrderMoneyPercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderMoneyPercent(@RequestParam Map<String, Object> map) {
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
		return companyService.getOrderMoneyPercent(map);
	}
	
	/**
	 * 交易额总额趋势
	 */
	@RequestMapping(value = "getOrderMoneySpeed", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderMoneySpeed(@RequestParam Map<String, Object> map) {
        String province=request.getParameter("province");
        String city = request.getParameter("city");
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("province", province);
        param.put("city", city);
        return companyService.getOrderMoneySpeed(map);
	}
	
	/**
	 * 订单详情
	 * @return
	 */
	@RequestMapping(value = "getNewOrderDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNewOrderDetail() {
		String orderId=request.getParameter("orderId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("orderId", orderId);
		return companyService.getNewOrderDetail(param);
	}
	
	/**
	 * 承运人信息
	 * @return
	 */
	@RequestMapping(value = "getCarrierDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCarrierDetail() {
		String orderId=request.getParameter("carrierId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("carrierId", orderId);
		return companyService.getCarrierDetail(param);
	}
	
	/**
	 * 待报价货源
	 * @return
	 */
	@RequestMapping(value = "getNewGoodsList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNewGoodsList() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getNewGoodsList(param);
		return object;
	}
	
	/**
	 * 待报价货源的需求车型占比
	 * @return
	 */
	@RequestMapping(value = "getNeedCarTypePercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNeedCarTypePercent() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getNeedCarTypePercent(param);
		return object;
	}
	
	/**
	 * 待报价货源的需求车长占比
	 * @return
	 */
	@RequestMapping(value = "getNeedCarLengthPercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNeedCarLengthPercent() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getNeedCarLengthPercent(param);
		return object;
	}
	
	/**
	 * 待报价货源的货源分布区域
	 * @return
	 */
	@RequestMapping(value = "getNeedOrderAreaPercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNeedOrderAreaPercent() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getNeedOrderAreaPercent(param);
		return object;
	}
	
	/**
	 * 货源增长趋势
	 * @return
	 */
	@RequestMapping(value = "getOrderSpeed", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getOrderSpeed() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getOrderSpeed(param);
		return object;
	}
	
	/**
	 * 闲置的车辆list
	 */
	@RequestMapping(value = "getNewUnusedCarList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNewUnusedCarList() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		return companyService.getNewUnusedCarList(param);
	}
	
	/**
	 * 需求车型占比
	 * @return
	 */
	@RequestMapping(value = "getCarTypePercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCarTypePercent() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getCarTypePercent(param);
		return object;
	}
	
	/**
	 * 需求车长占比
	 * @return
	 */
	@RequestMapping(value = "getCarLengthPercent", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCarLengthPercent() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getCarLengthPercent(param);
		return object;
	}
	
	/**
	 * 司机区域分布
	 * @return
	 */
	@RequestMapping(value = "getCarAreaCount", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCarAreaCount() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getCarAreaCount(param);
		return object;
	}
	
	/**
	 * 司机总收入趋势
	 * @return
	 */
	@RequestMapping(value = "getCarRevenueSpeed", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCarRevenueSpeed() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		Object object = companyService.getCarRevenueSpeed(param);
		return object;
	}
	
	
	/**
	 * 车辆详情
	 * @return
	 */
	@RequestMapping(value = "getCarDetail", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getCarDetail() {
		String carId=request.getParameter("carId");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("carId", carId);
		return companyService.getCarDetail(param);
	}
	
	/**
	 * 全国交易额
	 */
	@RequestMapping(value = "getNewTotalTradeMoney", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNewTotalTradeMoney() {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("province", province);
		param.put("city", city);
		return companyService.getNewTotalTradeMoney(param);
	}
	 
	
	/**
	 * 推荐路径规划
	 */
	@RequestMapping(value = "getRoutePlanning", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getRoutePlanning() {
		//起始地的经纬度
		String origin=request.getParameter("origin");
		//目的地的经纬度
		String destination=request.getParameter("destination");
		Map<String,String> param = new HashMap<String,String>();
		param.put("origin", origin);
		param.put("destination", destination);
		return companyService.getRoutePlanning(param);
	}
	
	/**
	 * 获取当前位置
	 */
	@RequestMapping(value = "getNowLocation", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getNowLocation() {
		//起始地的经纬度
		String origin=request.getParameter("origin");
		//目的地的经纬度
		String destination=request.getParameter("destination");
		Map<String,String> param = new HashMap<String,String>();
		param.put("origin", origin);
		param.put("destination", destination);
		return companyService.getNowLocation(param);
	}
//	/**
//	 * 日期测试
//	 */
//	@RequestMapping(value = "queryDate", produces = "application/json; charset=utf-8")
//	@ResponseBody
//	public Object queryDate(@RequestParam Map<String, Object> map) {
//		return companyService.queryDate(map);
//	}
}
