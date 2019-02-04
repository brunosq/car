package com.zt3000.dao.impl;

import java.lang.reflect.Method;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zt3000.dao.ICommonDao;

@Repository
public class CommonDaoImpl implements ICommonDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	/**
	 * methodName statement
	 * 
	 * 
	 * @param ps
	 * @return
	 */
	public Object common(Object... ps) {
		if (ps.length < 2) {
			return null;
		}
		String meth = (String) ps[0];
		String statement = (String) ps[1];
		try {
			Class<? extends SqlSessionTemplate> clazz = sqlSessionTemplate.getClass();
			if (2 == ps.length) {
				Method method = clazz.getMethod(meth, String.class);
				return method.invoke(sqlSessionTemplate, statement);
			}
			if (3 == ps.length) {
				Method method = clazz.getMethod(meth, String.class, Object.class);
				return method.invoke(sqlSessionTemplate, statement, ps[2]);
			}
			if (4 == ps.length) {
				Method method = clazz.getMethod(meth, String.class, Object.class, RowBounds.class);
				return method.invoke(sqlSessionTemplate, statement, ps[2], ps[3]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
