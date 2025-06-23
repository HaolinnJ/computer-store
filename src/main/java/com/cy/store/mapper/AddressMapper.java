package com.cy.store.mapper;

import com.cy.store.entity.Address;

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
}
