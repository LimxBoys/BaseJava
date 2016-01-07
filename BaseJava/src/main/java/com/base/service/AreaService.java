package com.base.service;

import java.util.List;

import com.base.vo.BaseArea;
import com.base.vo.Criteria;
/**
 * 
 * @author limingxing
 * @Date:2016-1-7上午10:57:58
 * @email:limingxing_aqgy@sina.com
 * @version:1.0
 */
public interface AreaService {
	// 查询省
	public List<BaseArea> findProvince();

	// 查询市
	public List<BaseArea> findCity(Criteria paramCriteria);

	// 查询县区
	public List<BaseArea> findCounty(Criteria paramCriteria);
}
