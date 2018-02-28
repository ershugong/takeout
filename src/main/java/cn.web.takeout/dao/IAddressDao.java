package cn.web.takeout.dao;

import cn.web.takeout.model.Address;

import java.util.List;
import java.util.Map;

public interface IAddressDao {
    /**
     * 根据用户id查找其相关的地址
     * @param map
     * @return
     * @throws Exception
     */
    List<Address> getAddressByUserId(Map<String,Object> map) throws Exception;

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

    /**
     * 通过id查询地址
     * @param id
     * @return
     * @throws Exception
     */
    Address selectAddress(String id) throws Exception;
}
