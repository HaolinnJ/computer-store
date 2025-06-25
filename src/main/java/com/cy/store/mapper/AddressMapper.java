package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.List;

public interface AddressMapper {

    /**
     * insert user address
     * @param address address data
     * @return affected rows
     */
    Integer insert(Address address);

    /**
     * calculate number of address based on user id
     * @param uid
     * @return current all address of a certain user
     */
    Integer countByUid(Integer uid);

    /**
     * 根据用户的id查询用户所有的收货地址数据
     * @param uid
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);
}
