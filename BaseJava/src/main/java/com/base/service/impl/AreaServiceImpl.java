package com.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.dao.AreaDao;
import com.base.service.AreaService;
import com.base.vo.BaseArea;
import com.base.vo.Criteria;
@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaDao areaDao;
	public List<BaseArea> findProvince() {
		// TODO Auto-generated method stub
		return areaDao.findProvince();
	}

	public List<BaseArea> findCity(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return areaDao.findCity(paramCriteria);
	}

	public List<BaseArea> findCounty(Criteria paramCriteria) {
		// TODO Auto-generated method stub
		return areaDao.findCounty(paramCriteria);
	}

}
