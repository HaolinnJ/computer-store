package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

/**新增收货地址的实现类*/
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    //在添加用户的收货地址的业务层依赖于DistrictService的业务层接口
    @Autowired
    private IDistrictService districtService;
    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收货地址统计的地址
        Integer count = addressMapper.countByUid(uid);
        if (count>=maxCount){
            throw new AddressCountLimitException("Number of address exceeds limit.");
        }

        // 对address对象中的数据进行补全：省市区
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        // Default address information 1:Default, 0:Not Default
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);

        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if (rows != 1){
            throw new InsertException("Insert address failed.");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list){
            //address.setUid(null);
            address.setProvinceCode(null);
            //address.setCityName(null);
            address.setCityCode(null);
            //address.setAreaName(null);
            address.setAreaCode(null);
            address.setZip(null);
            //address.setTel(null);
            address.setIsDefault(null);
            address.setCreatedTime(null);
            address.setCreatedUser(null);
            address.setModifiedTime(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if(result==null) {
            throw new AddressNotFoundException("Address does not exist.");
        }
        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("Invalid request.");
        }

        Integer rows = addressMapper.updateNonDefault(uid);
        if(rows<1){
            throw new UpdateException("Unknown problem has occurred during update.");
        }

        rows = addressMapper.updateDefaultByAid(aid,username,new Date());
        if (rows!=1){
            throw new UpdateException("Unknown problem has occurred during update.");
        }
    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result==null){
            throw new AddressNotFoundException("Address does not exist.");
        }

        if(!result.getUid().equals(uid)){
            throw new AccessDeniedException("Invalid request.");
        }

        Integer rows = addressMapper.deleteByAid(aid);
        if(rows !=1){
            throw new DeleteException("Unknown problem has occurred during delete.");
        }

        if(result.getIsDefault()==0){
            return;
        }
        Integer count = addressMapper.countByUid(uid);
        if(count ==0){
            return;
        }

        Address address = addressMapper.findLastModified(uid);

        rows =addressMapper.updateDefaultByAid(address.getAid(),username, new Date());
        if(rows != 1){
            throw new UpdateException("Unknown problem has occurred during update");
        }
    }
}
