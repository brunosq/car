package com.zt3000.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.util.date.DateStyle;
import com.util.date.DateUtil;
import com.zt3000.common.Constant;
import com.zt3000.dao.CompanyDao;
import com.zt3000.model.ScreenSet;
import com.zt3000.service.CompanyService;
import com.zt3000.utils.HttpUtils;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	private CompanyDao companyDao;

	/**
	 * 企业集合
	 */
	@Override
	public Object getCompanyList(Map<String,Object> param) {
		return companyDao.getCompanyList(param);
	}


	/**
	 * 省份企业集合
	 */
	@Override
	public Object getProCompanyList(Map<String,Object> param) {
		return companyDao.getProCompanyList(param);
	}
	
	/**
	 * 待报价货源
	 */
	@Override
	public Object getGoodsList(Map<String,Object> param) {
		return companyDao.getGoodsList(param);
	}
	
	/**
	 * 省份信息
	 */
	@Override
	public Object getProvinceInfo(Map<String,Object> param) {
		
		ScreenSet totalTradeMoneySet = companyDao.getScreenSet(Constant.ALL_TRADE);
		ScreenSet companyCountSet = companyDao.getScreenSet(Constant.PROVINCE_COMPANY);
		ScreenSet driverCountSet = companyDao.getScreenSet(Constant.PROVINCE_DRIVER);
		ScreenSet orderGoodsCountSet = companyDao.getScreenSet(Constant.PROVINCE_ORDER);
		ScreenSet goodsCountSet = companyDao.getScreenSet(Constant.PROVINCE_GOODS);
		ScreenSet hasPricedGoodsCountSet = companyDao.getScreenSet(Constant.PROVINCE_PRICE);
		
		param.put("startTime", DateUtil.DateToString(totalTradeMoneySet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", totalTradeMoneySet.getRatio());
		Long tradeMoney= companyDao.getTradeMoney(param);
		
		
		param.put("startTime", DateUtil.DateToString(companyCountSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", companyCountSet.getRatio());
		Long companyCount= companyDao.getCompanyCount(param);
		
		param.put("startTime", DateUtil.DateToString(driverCountSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", driverCountSet.getRatio());
		Long driverCount= companyDao.getDriverCount(param);
		
		param.put("startTime", DateUtil.DateToString(orderGoodsCountSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", orderGoodsCountSet.getRatio());
		Long orderGoodsCount=companyDao.getOrderGoodsCount(param);
		
		param.put("startTime", DateUtil.DateToString(goodsCountSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", goodsCountSet.getRatio());
		Long goodsCount=companyDao.getGoodsCount(param);
		
		param.put("startTime", DateUtil.DateToString(hasPricedGoodsCountSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", hasPricedGoodsCountSet.getRatio());
		Long hasPricedGoodsCount=companyDao.getHasPricedGoodsCount(param);
		
		Map<String,Object> provinceInfo=new HashMap<String, Object>();
		provinceInfo.put("tradeMoney", tradeMoney==null?0:tradeMoney);
		provinceInfo.put("companyCount", companyCount);
		provinceInfo.put("driverCount", driverCount);
		provinceInfo.put("orderGoodsCount", orderGoodsCount);
		provinceInfo.put("goodsCount", goodsCount);
		provinceInfo.put("hasPricedGoodsCount", hasPricedGoodsCount);
		
		return provinceInfo;
	}
	/**
	 * 企业详情
	 */
	@Override
	public Object getCompanyDetail(Map<String, Object> param) {
		return companyDao.getCompanyDetail(param);
	}
	/**
	 * 货源详情
	 */
	@Override
	public Object getGoodsDetail(Map<String, Object> param) {
		return companyDao.getGoodsDetail(param);
	}
	/**
	 * 订单list
	 */
	@Override
	public Object getOrderList(Map<String, Object> param) {
		return companyDao.getOrderList(param);
	}
	/**
	 * @return
	 * 全国交易额
	 */
	@Override
	public Object getTotalTradeMoney(Map<String, Object> param) {
		
		ScreenSet totalTradeMoneySet = companyDao.getScreenSet(Constant.ALL_TRADE);
		List newOrderLocas=(List)companyDao.getNewestOrders(param);
		
		param.put("startTime", DateUtil.DateToString(totalTradeMoneySet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", totalTradeMoneySet.getRatio());
		Long totalTradeMoney = companyDao.getTotalTradeMoney(param);
		
		Map<String,Object> result=new HashMap<String,Object>();
		
		if(param.containsKey("agent_code")){
			//说明有省的参数，那么totalTradeMoney查的是省的总额，还需要全国的总额countryPrice
			
			param.remove("agent_code");
			Long countryPrice = companyDao.getTotalTradeMoney(param);
			result.put("totalTradeMoney", countryPrice);
			result.put("newOrderLocas", newOrderLocas);
			result.put("provincePrice", totalTradeMoney);
			return result;
		}else{
			result.put("totalTradeMoney", totalTradeMoney);
			result.put("newOrderLocas", newOrderLocas);
			return result;
		}
	}
	
	/**
	 * 货源发布率，货源报价率，货源成单率，货源发布同比增长，货源报价同比增长，货源成单同比增长
	 * @return
	 */
	@Override
	public Object getGoodsRate(Map<String, Object> param) {
		
		ScreenSet goodsRateSet = companyDao.getScreenSet(Constant.GOODS_RATE);
		ScreenSet pricedRateSet = companyDao.getScreenSet(Constant.PRICE_GOODS_RATE);
		ScreenSet orderRateSet = companyDao.getScreenSet(Constant.ORDER_GOODS_RATE);
		ScreenSet goodsGrowthSet = companyDao.getScreenSet(Constant.GOODS_GROWTH);
		ScreenSet pricedGrowthSet = companyDao.getScreenSet(Constant.PRICE_GOODS_GROWTH);
		ScreenSet orderGrowthSet = companyDao.getScreenSet(Constant.ORDER_GOODS_GROWTH);
		
		param.put("startTime", DateUtil.DateToString(goodsRateSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", goodsRateSet.getRatio());
		String goodsRate =  companyDao.getGoodsRate(param);
		
		param.put("startTime", DateUtil.DateToString(pricedRateSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", pricedRateSet.getRatio());
		String pricedRate =  companyDao.getPricedRate(param);
		
		param.put("startTime", DateUtil.DateToString(orderRateSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", orderRateSet.getRatio());
		String orderRate =  companyDao.getOrderRate(param);
		
		param.put("startTime", DateUtil.DateToString(goodsGrowthSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", goodsGrowthSet.getRatio());
		String goodsGrowth =  companyDao.getGoodsGrowth(param);
		
		param.put("startTime", DateUtil.DateToString(pricedGrowthSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", pricedGrowthSet.getRatio());
		String pricedGrowth =  companyDao.getPriceGrowth(param);
		
		param.put("startTime", DateUtil.DateToString(orderGrowthSet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", orderGrowthSet.getRatio());
		String orderGrowth =  companyDao.getOrderGrowth(param);
		
		
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("goodsRate", goodsRate);
		result.put("pricedRate", pricedRate);
		result.put("orderRate", orderRate);
		result.put("goodsGrowthRate", goodsGrowth.equals("0")?"100":goodsGrowth);
		result.put("pricedGrowthRate", pricedGrowth.equals("0")?"100":pricedGrowth);
		result.put("orderGrowthRate", orderGrowth.equals("0")?"100":orderGrowth);
		
		return result;
	}
	/**
	 * 根据出发地，目的地查询订单list
	 */
	@Override
	public Object getOrderListByCity(Map<String, Object> param) {
		return companyDao.getOrderListByCity(param);
	}
	/**
	 * 订单详情
	 */
	@Override
	public Object getOrderDetail(Map<String, Object> param) {
		return companyDao.getOrderDetail(param);
	}

	/**
	 * 闲置的车辆list
	 */
	@Override
	public Object getUnusedCarList(Map<String, Object> param) {
		return companyDao.getUnusedCarList(param);
	}

    /**
     * 省份闲置的车辆list
     */
    @Override
    public Object getProUnusedCarList(Map<String, Object> param) {
        return companyDao.getProUnusedCarList(param);
    }
	
	/**
	 * 闲置的车辆list
	 */
	@Override
	public Object showCarDetail(Map<String, Object> param) {
		return companyDao.showCarDetail(param);
	}
	
	/**
	 * 最近六个月交易额
	 */
	@Override
	public Object getNearMonthTrade(Map<String, Object> param) {
		
        ScreenSet totalTradeMoneySet = companyDao.getScreenSet(Constant.ALL_TRADE);
		param.put("startTime", DateUtil.DateToString(totalTradeMoneySet.getStartTime(),DateStyle.YYYY_MM_DD_HH_MM));
		param.put("ratio", totalTradeMoneySet.getRatio());
		
		
		
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		
//		for(int i=1;i<7;i++){
//			
//			Map<String,Object> temp=new HashMap<String,Object>();
//			Calendar now=Calendar.getInstance();
//			int curMonth = now.get(Calendar.MONTH);
//			now.set(Calendar.MONTH, curMonth-i);
//			String date = DateUtil.DateToString(now.getTime(),DateStyle.YYYY_MM);
//			param.put("createTime" , date);
//			long trade = companyDao.getTotalTradeMoney(param);
//			temp.put("month", date);
//			temp.put("trade", trade);
//			
//			list.add(temp);
//		}
		
		Calendar c = Calendar.getInstance(); 
	    c.add(Calendar.MONTH, -5);
	    String before_six=c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH);//6个月前  
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月  
	    Calendar min = Calendar.getInstance();  
	    Calendar max = Calendar.getInstance();  
	    try {
			min.setTime(sdf.parse(before_six));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    try {
			max.setTime(sdf.parse(sdf.format(new Date())));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH),-1);  
	    
	    
	    Calendar curr = min;  

	    while (curr.before(max)) {  
	        Map<String,Object> temp=new HashMap<String,Object>();
			param.put("createTime" , sdf.format(curr.getTime()));
			long trade = companyDao.getTotalTradeMoney(param);
			temp.put("month", sdf.format(curr.getTime()));
			temp.put("trade", trade);
	        list.add(temp);
	        
	        curr.add(Calendar.MONTH, 1);  
	    }
		
		return list;
	}

	@Override
	public Object getProvinceGoods(Map<String, Object> param) {
		
		return companyDao.getProvinceGoods(param);
	}
	
	
	/*手机版*/
	@Override
	public Object queryCompanyList(Map<String, Object> param) {
		
		return companyDao.queryCompanyList(param);
	}
	
	@Override
	public Object queryCarList(Map<String, Object> param) {
		
		return companyDao.queryCarList(param);
	}
	
	@Override
	public Object queryOnwayOrderList(Map<String, Object> param) {
		
		return companyDao.queryOnwayOrderList(param);
	}


	//TODO
	/* 注册增长趋势 API
	 * 
	 */
	@Override
	public Object getRegisterSpeed(Map<String, Object> param) {
		return companyDao.getRegisterSpeed(param);
	}
	
	//TODO
	/* 企业区域分布 API
	 * 
	 */
	@Override
	public Object getCompanyDistribution(Map<String, Object> param) {
		return companyDao.getCompanyDistribution(param);
	}


	/* (non-Javadoc)
	 * @see com.zt3000.service.CompanyService#getNewProCompanyList(java.util.Map)
	 */
	@Override
	public Object getNewProCompanyList(Map<String, Object> param) {
		return companyDao.getNewProCompanyList(param);
	}
	
	/**
	 * 企业详情
	 */
	@Override
	public Object getNewCompanyDetail(Map<String, Object> param) {
		return companyDao.getNewCompanyDetail(param);
	}


	/* 
	 * 在途订单
	 */
	@Override
	public Object getNewQueryOnwayOrderList(Map<String, Object> param) {
		return companyDao.getNewQueryOnwayOrderList(param);
	}


	/* 
	 * 货物类型占比
	 */
	@Override
	public Object getGoodsTypePercent(Map<String, Object> param) {
		return companyDao.getGoodsTypePercent(param);
	}


	/* 
	 * 订单地域占比
	 */
	@Override
	public Object getOrderAreaPercent(Map<String, Object> param) {
		return companyDao.getOrderAreaPercent(param);
	}


	/* 
	 * 订单交易额占比
	 */
	@Override
	public Object getOrderMoneyPercent(Map<String, Object> param) {
		return companyDao.getOrderMoneyPercent(param);
	}


	/* 
	 *  交易额总额趋势
	 */
	@Override
	public Object getOrderMoneySpeed(Map<String, Object> param) {
		return companyDao.getOrderMoneySpeed(param);
	}

	/* 
	 *  订单详情
	 */
	@Override
	public Object getNewOrderDetail(Map<String, Object> param) {
		return companyDao.getNewOrderDetail(param);
	}

	/* 
	 *  承运人信息
	 */
	@Override
	public Object getCarrierDetail(Map<String, Object> param) {
		return companyDao.getCarrierDetail(param);
	}

	/* 
	 *  待报价货源
	 */
	@Override
	public Object getNewGoodsList(Map<String, Object> param) {
		return companyDao.getNewGoodsList(param);
	}


	/* 
	 * 待报价货源的需求车型占比
	 */
	@Override
	public Object getNeedCarTypePercent(Map<String, Object> param) {
		return companyDao.getNeedCarTypePercent(param);
	}


	/* 
	 * 待报价货源的需求车长占比
	 */
	@Override
	public Object getNeedCarLengthPercent(Map<String, Object> param) {
		return companyDao.getNeedCarLengthPercent(param);
	}


	/* 
	 * 待报价货源的货源分布区域
	 */
	@Override
	public Object getNeedOrderAreaPercent(Map<String, Object> param) {
		return companyDao.getNeedOrderAreaPercent(param);
	}

	/* 
	 * 货源增长趋势
	 */
	@Override
	public Object getOrderSpeed(Map<String, Object> param) {
		return companyDao.getOrderSpeed(param);
	}

	/* 
	 * 闲置的车辆list
	 */
	@Override
	public Object getNewUnusedCarList(Map<String, Object> param) {
		return companyDao.getNewUnusedCarList(param);
	}


	/* 
	 * 需求车型占比
	 */
	@Override
	public Object getCarTypePercent(Map<String, Object> param) {
		return companyDao.getCarTypePercent(param);
	}
	
	/* 
	 * 需求车长占比
	 */
	@Override
	public Object getCarLengthPercent(Map<String, Object> param) {
		return companyDao.getCarLengthPercent(param);
	}

	/* 
	 * 司机区域分布
	 */
	@Override
	public Object getCarAreaCount(Map<String, Object> param) {
		return companyDao.getCarAreaCount(param);
	}

	/* 
	 * 司机总收入趋势
	 */
	@Override
	public Object getCarRevenueSpeed(Map<String, Object> param) {
		return companyDao.getCarRevenueSpeed(param);
	}

	/* 
	 * 车辆详情
	 */
	@Override
	public Object getCarDetail(Map<String, Object> param) {
		return companyDao.getCarDetail(param);
	}

	/* 
	 * 全国交易额
	 */
	@Override
	public Object getNewTotalTradeMoney(Map<String, Object> param) {
		return companyDao.getNewTotalTradeMoney(param);
	}

	/* 
	 * 推荐路径规划
	 */
	@Override
	public Object getRoutePlanning(Map<String, String> param) {
		
		//最终返回的jsonList
		List<String> jsonList = new ArrayList<String>();
		//最终返回的json
		String json = null;
		
		//发送请求，包装返回诗句
		try {
			//拼接参数并发送请求
			String responseJson = HttpUtils.get(HttpUtils.getUrl(Constant.BAIDU_MAP_URL_ROUTE_PLANNING,param));
			 //转换器  
			ObjectMapper mapper = new ObjectMapper();
			//返回的json解析
			Map<String, Object> m = mapper.readValue(responseJson, Map.class);
			List<Map<String, Object>> resultSteps = (List<Map<String, Object>>)((Map<String, Object>)((List<Object>)(((Map<String, Object>) m.get("result"))).get("routes")).get(0)).get("steps");
			 for (Map<String, Object> step : resultSteps){
				 String[] path = ((String) step.get("path")).split(";");
				 jsonList.add(path[0]);
			 }
			 json = mapper.writeValueAsString(jsonList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/* 
	 * 获取当前位置
	 */
	@Override
	public Object getNowLocation(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public Object queryDate(Map<String, Object> param){
//		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//	 	Calendar c = Calendar.getInstance(); 
//	    c.add(Calendar.MONTH, -5);  
//	    String before_six=c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH);//6个月前  
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月  
//	    Calendar min = Calendar.getInstance();  
//	    Calendar max = Calendar.getInstance();  
//	    try {
//			min.setTime(sdf.parse(before_six));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    
//	    try {
//			max.setTime(sdf.parse(sdf.format(new Date())));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}  
//	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH),-1);  
//	    
//	    Calendar curr = min;  
//
//	    while (curr.before(max)) {  
//	        curr.add(Calendar.MONTH, 1);  
//	        
//	        Map<String,Object> temp=new HashMap<String,Object>();
//	        temp.put("month", sdf.format(curr.getTime()));
//	        list.add(temp);
//	    }
//   
//	    return list;
//	}
}
