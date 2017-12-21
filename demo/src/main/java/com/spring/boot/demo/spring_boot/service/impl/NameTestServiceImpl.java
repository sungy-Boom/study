package com.spring.boot.demo.spring_boot.service.impl;

import com.spring.boot.demo.spring_boot.dao.INameTestDAO;
import com.spring.boot.demo.spring_boot.entity.NameTestEntity;
import com.spring.boot.demo.spring_boot.service.NameTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        return nameTestDAO.getListByName(name);
    }

    @Override
    public void updateByName(Date updateTime, String name) {
        nameTestDAO.updateByName(updateTime, name);
    }

    @Override
    public boolean insertDB(String name) {
        String subName = getSubName(name);
        NameTestEntity entity = new NameTestEntity(name, subName);
        nameTestDAO.save(entity);
        return true;
    }

    @Override
    public List<NameTestEntity> getList(String name) {
        return nameTestDAO.getTop10ByNameContaining(name);
    }

    @Override
    public List<NameTestEntity> testCaseInSql(String name, String sub) {

        return nameTestDAO.getByMultiCondition(name, sub);
    }

    @Override
    public boolean deleteRecord(String name) {
        nameTestDAO.deleteByName(name);
        return true;
    }

    /**
     * 看是否有重名
     */
    private String getSubName(String name) {
        NameTestEntity entity = nameTestDAO.getFirstByName(name);
        if (null == entity) {
            return "";
        }
        if (StringUtils.isEmpty(entity.getSub())) {
            return "1";
        } else {
            Integer nums = Integer.valueOf(entity.getSub()) + 1;
            return nums.toString();
        }
    }
}
