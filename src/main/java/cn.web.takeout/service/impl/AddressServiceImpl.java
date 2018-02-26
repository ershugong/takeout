package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IAddressDao;
import cn.web.takeout.model.Address;
import cn.web.takeout.service.IAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("addressService")
public class AddressServiceImpl implements IAddressService{
    @Resource
    private IAddressDao addressDao;

    @Override
    public List<Address> getAddressByUserId(String userId) throws Exception {
        return addressDao.getAddressByUserId(userId);
    }

    @Override
    public long insertAddress(Address address) throws Exception {
        return addressDao.insertAddress(address);
    }

    @Override
    public long updateAddress(Address address) throws Exception {
        return addressDao.updateAddress(address);
    }
}
