package com.zt3000.service;

import java.util.Map;

public interface CompanyService {
	
	Object getCompanyList(Map<String,Object> param);
	
	Object getProCompanyList(Map<String, Object> param);
	
	Object getGoodsList(Map<String,Object> param);
	
	Object getProvinceInfo(Map<String,Object> param);
	
	Object getCompanyDetail(Map<String,Object> param);
	
	Object getGoodsDetail(Map<String,Object> param);
	
	Object getOrderList(Map<String,Object> param);
	
	Object getTotalTradeMoney(Map<String,Object> param);
	
	Object getGoodsRate(Map<String,Object> param);
	
	Object getOrderListByCity(Map<String,Object> param);
	
	Object getOrderDetail(Map<String,Object> param);
	
	Object getUnusedCarList(Map<String,Object>param);

	Object getProUnusedCarList(Map<String,Object>param);

	Object showCarDetail(Map<String,Object> param);
	
	Object getNearMonthTrade(Map<String,Object> param);
	
	Object getProvinceGoods(Map<String,Object> param);
	

	/*新增手机*/
	Object queryCompanyList(Map<String,Object> param);
	Object queryCarList(Map<String,Object> param);
	Object queryOnwayOrderList(Map<String,Object> param);
	
//	Object queryDate(Map<String, Object> param);
	
	//update 2019/1/10 by sunqiang start
	

	/** 新增注册增长趋势
	 * @param param
	 * @return
	 */
	Object getRegisterSpeed(Map<String,Object> param);
	

	/** 企业区域分布
	 * @param param
	 * @return
	 */
	Object getCompanyDistribution(Map<String,Object> param);
	

	/** 企业列表
	 * @param param
	 * @return
	 */
	Object getNewProCompanyList(Map<String, Object> param);

	/** 企业详情（新增企业交易总额、运单数）
	 * @param param
	 * @return
	 */
	Object getNewCompanyDetail(Map<String, Object> param);

	/** 在途订单
	 * @param param
	 * @return
	 */
	Object getNewQueryOnwayOrderList(Map<String,Object> param);
	
	/** 货物类型占比
	 * @param param
	 * @return
	 */
	Object getGoodsTypePercent(Map<String,Object> param);
	
	/** 订单地域占比
	 * @param param
	 * @return
	 */
	Object getOrderAreaPercent(Map<String,Object> param);
	
	/** 订单交易额占比
	 * @param param
	 * @return
	 */
	Object getOrderMoneyPercent(Map<String,Object> param);
	
	/** 交易额总额趋势
	 * @param param
	 * @return
	 */
	Object getOrderMoneySpeed(Map<String,Object> param);
	
	/** 订单详情
	 * @param param
	 * @return
	 */
	Object getNewOrderDetail(Map<String,Object> param);
	
	/** 承运人信息
	 * @param param
	 * @return
	 */
	Object getCarrierDetail(Map<String,Object> param);

	/** 待报价货源
	 * @param param
	 * @return
	 */
	Object getNewGoodsList(Map<String, Object> param);
	

	/** 待报价货源的需求车型占比
	 * @param param
	 * @return
	 */
	Object getNeedCarTypePercent(Map<String, Object> param);

	/** 待报价货源的需求车长占比
	 * @param param
	 * @return
	 */
	Object getNeedCarLengthPercent(Map<String, Object> param);
	
	/** 待报价货源的货源分布区域
	 * @param param
	 * @return
	 */
	Object getNeedOrderAreaPercent(Map<String, Object> param);
	
	/** 货源增长趋势
	 * @param param
	 * @return
	 */
	Object getOrderSpeed(Map<String, Object> param);
	
	/** 闲置的车辆list
	 * @param param
	 * @return
	 */
	Object getNewUnusedCarList(Map<String, Object> param);
	
	/** 需求车型占比
	 * @param param
	 * @return
	 */
	Object getCarTypePercent(Map<String, Object> param);
	
	/** 需求车长占比
	 * @param param
	 * @return
	 */
	Object getCarLengthPercent(Map<String, Object> param);
	
	/** 司机区域分布
	 * @param param
	 * @return
	 */
	Object getCarAreaCount(Map<String, Object> param);
	
	/** 司机总收入趋势
	 * @param param
	 * @return
	 */
	Object getCarRevenueSpeed(Map<String, Object> param);
	
	/** 车辆详情
	 * @param param
	 * @return
	 */
	Object getCarDetail(Map<String, Object> param);
	
	/** 全国交易额
	 * @param param
	 * @return
	 */
	Object getNewTotalTradeMoney(Map<String, Object> param);
	
	/** 推荐路径规划
	 * @param param
	 * @return
	 */
	Object getRoutePlanning(Map<String, String> param);
	
	/** 获取当前位置
	 * @param param
	 * @return
	 */
	Object getNowLocation(Map<String, String> param);

    
}
