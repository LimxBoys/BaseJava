package com.base.dao;

import java.util.List;

import com.base.vo.BaseArea;
import com.base.vo.Criteria;

public interface AreaDao {
	// 查询省
	public List<BaseArea> findProvince();

	// 查询市
	public List<BaseArea> findCity(Criteria paramCriteria);

	// 查询县区
	public List<BaseArea> findCounty(Criteria paramCriteria);
}
