package cn.web.takeout.service;

import cn.web.takeout.model.Address;
import cn.web.takeout.vo.AddressVO;

import java.util.List;

public interface IAddressService {
    /**
     * 根据用户id查找其相关的地址
     * @param userId
     * @return
     * @throws Exception
     */
    List<AddressVO> getAddressByUserId(String userId,Integer isDefault) throws Exception;

    /**
     * 插入新地址
     * @param address
     * @return
     * @throws Exception
     */
    long insertAddress(Address address) throws Exception;

    /**
     * 更新地址
     * @param address
     * @return
     * @throws Exception
     */
    long updateAddress(Address address) throws Exception;

    /**
     * 删除地址
     * @param id
     * @return
     * @throws Exception
     */
    long delAddress(String id) throws Exception;
}
