package com.cy.store.mapper;

import com.cy.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parent 父代号
     * @return 某个父区域下的所有区域列表
     */
    List<District> findByParent(String parent);

    /**
     * 根据自身代号查询
     * @param code 区域号
     * @return 自身的区域名
     */
    String findNameByCode (String code);

}
