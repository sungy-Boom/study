package com.daily.study.spring_boot.service.impl;

import com.daily.study.spring_boot.dao.INameTestDAO;
import com.daily.study.spring_boot.entity.NameTestEntity;
import com.daily.study.spring_boot.service.NameTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author SunGuiyong
 */
@Service
public class NameTestServiceImpl implements NameTestService {

    @Autowired
    private INameTestDAO nameTestDAO;

    @Override
    public List<NameTestEntity> getNameTestByName(String name) {
        return nameTestDAO.getNameTestEntityByName(name);
    }

    @Override
    public void updateByName(Date updateTime, String name) {
        nameTestDAO.updateByName(updateTime, name);
    }


}
