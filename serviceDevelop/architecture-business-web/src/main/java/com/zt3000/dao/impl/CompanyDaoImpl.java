package com.zt3000.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zt3000.dao.CompanyDao;
import com.zt3000.model.ScreenSet;

@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Object getCompanyList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_company_list", param);
	}

    @Override
    public Object getProCompanyList(Map<String, Object> param) {
        return sqlSessionTemplate.selectList("query_pro_company_list", param);
    }

	@Override
	public Object getGoodsList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_goods_list", param);
	}

	@Override
	public Long getCompanyCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_company_count", param);
	}

	@Override
	public Long getDriverCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_driver_count", param);
	}

	@Override
	public Long getOrderGoodsCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_orderGoods_count", param);
	}

	@Override
	public Long getGoodsCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_goods_count", param);
	}

	@Override
	public Long getHasPricedGoodsCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_priced_goods_count", param);
	}

	@Override
	public Long getTradeMoney(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_trade_money", param);
	}

	@Override
	public Object getCompanyDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_company_detail", param);
	}

	@Override
	public Object getGoodsDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_goods_detail", param);
	}

	@Override
	public Object getOrderList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_order_list", param);
	}

	@Override
	public Long getTotalTradeMoney(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_total_tradeMoney", param);
	}

	@Override
	public Long getTodayCompanyCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_today_company_count", param);
	}

	@Override
	public Long getTodayDriverCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_today_driver_count", param);
	}

	@Override
	public String getGoodsRate(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_goods_rate", param);
	}

	@Override
	public String getPricedRate(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_pricedGoods_rate", param);
	}

	@Override
	public String getOrderRate(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_orderGoods_rate", param);
	}

	@Override
	public Object getOrderListByCity(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_order_list_bycity", param);
	}

	@Override
	public Object getOrderDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_order_detail", param);
	}

	@Override
	public Object getUnusedCarList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_unused_car_list", param);
	}

    @Override
    public Object getProUnusedCarList(Map<String, Object> param) {
        return sqlSessionTemplate.selectList("query_pro_unused_car_list", param);
    }

	@Override
	public Object showCarDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_car_detail", param);
	}

	@Override
	public ScreenSet getScreenSet(Integer type) {
		return sqlSessionTemplate.selectOne("query_screen_set", type);
	}

	@Override
	public Object getNewQuarter() {
		return sqlSessionTemplate.selectList("query_quarter");
	}

	@Override
	public Long getUserAmount(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_useramount_byquarter",param);
	}

	@Override
	public String getGoodsGrowth(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_goods_growth",param);
	}

	@Override
	public String getPriceGrowth(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_price_growth",param);
	}

	@Override
	public String getOrderGrowth(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("query_order_growth",param);
	}

	@Override
	public Object getNewestOrders(Map<String, Object> param) {
		List<Map<String,Object>> list=(List)sqlSessionTemplate.selectList("query_order_location",param);
		
		List resList=new ArrayList();
		for(Map<String,Object> map:list){
			String [] arr=new String[]{map.get("lon")+"",map.get("lat")+""};
			resList.add(arr);
		}
		return resList;
	}

	@Override
	public Object getProvinceGoods(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_province_goods_list",param);
	}
	
	/*新增手机版*/
	@Override
	public Object queryCompanyList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_company_list",param);
	}
	
	@Override
	public Object queryCarList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_car_list",param);
	}
	
	@Override
	public Object queryOnwayOrderList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_onway_orders",param);
	}
	
	@Override
	public Object getRegisterSpeed(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_register_speed",param);
	}
	
	@Override
	public Object getCompanyDistribution(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("query_company_distribution",param);
	}
	
    @Override
    public Object getNewProCompanyList(Map<String, Object> param) {
        return sqlSessionTemplate.selectList("new_query_pro_company_list", param);
    }
    
	@Override
	public Object getNewCompanyDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne("new_query_company_detail", param);
	}
	
	@Override
	public Object getNewQueryOnwayOrderList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("new_query_onway_orders", param);
	}

	@Override
	public Object getGoodsTypePercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_goods_type_percent", param);
	}

	@Override
	public Object getOrderAreaPercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_order_area_percent", param);
	}

	@Override
	public Object getOrderMoneyPercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_order_money_percent", param);
	}

	@Override
	public Object getOrderMoneySpeed(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_order_money_speed", param);
	}

	@Override
	public Object getNewOrderDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_new_order_detail", param);
	}

	@Override
	public Object getCarrierDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_carrier_detail", param);
	}

	@Override
	public Object getNewGoodsList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_new_goods_list", param);
	}

	@Override
	public Object getNeedCarTypePercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_need_car_type_percent", param);
	}

	@Override
	public Object getNeedCarLengthPercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_need_car_length_percent", param);
	}

	@Override
	public Object getNeedOrderAreaPercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_need_order_area_percent", param);
	}

	@Override
	public Object getOrderSpeed(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_order_speed", param);
	}

	@Override
	public Object getNewUnusedCarList(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_new_unuser_car_list", param);
	}

	@Override
	public Object getCarTypePercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_car_type_percent", param);
	}

	@Override
	public Object getCarLengthPercent(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_car_length_percent", param);
	}

	@Override
	public Object getCarAreaCount(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_car_area_count", param);
	}

	@Override
	public Object getCarRevenueSpeed(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_car_revenue_speed", param);
	}

	@Override
	public Object getCarDetail(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_car_detail", param);
	}

	@Override
	public Object getNewTotalTradeMoney(Map<String, Object> param) {
		return sqlSessionTemplate.selectList("get_new_total_trade_money", param);
	}
}
