package com.cy.store.mapper;

import com.cy.store.entity.Address;

import java.util.Date;
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

    /**
     * 根据aid查询收货地址数据
     * @param aid
     * @return 如果没有找到则返回null
     */
    Address findByAid(Integer aid);

    /**
     * 根据用户的uid修改用户的收货地址统一设置为非默认
     * @param uid
     * @return 受影响的行数
     */
    Integer updateNonDefault(Integer uid);

    Integer updateDefaultByAid(
            Integer aid,
            String modifiedUser,
            Date modifiedTime
    );
}
