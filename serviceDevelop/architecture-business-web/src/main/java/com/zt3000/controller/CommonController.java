package com.zt3000.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.zt3000.common.BaseController;
import com.zt3000.dao.ICommonDao;

@Controller
@RequestMapping("/common")
public class CommonController extends BaseController {
	
	@Autowired
	private ICommonDao commonDao;
	
	/**
	 * 企业集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "call")
	@ResponseBody
	public Object call(@RequestParam Map map) {
		
		System.out.println(map);
		
		return commonDao.common("selectList", "center.query_company_list", map, new PageBounds(1, 10));
	}
}
