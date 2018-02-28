package cn.web.takeout.service.impl;

import cn.web.takeout.dao.IAddressDao;
import cn.web.takeout.model.Address;
import cn.web.takeout.service.IAddressService;
import cn.web.takeout.vo.AddressVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("addressService")
public class AddressServiceImpl implements IAddressService{
    @Resource
    private IAddressDao addressDao;

    @Override
    public List<AddressVO> getAddressByUserId(String userId,Integer isDefault) throws Exception {

        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("isDefault",isDefault);
        List<Address> addressList = addressDao.getAddressByUserId(map);
        List<AddressVO> result = new ArrayList<>();
        if(null != addressList){
            AddressVO vo;
            for (Address address : addressList){
                vo = new AddressVO();
                vo.setId(address.getId());
                vo.setUserName(address.getUserName());
                vo.setPhone(address.getPhone());
                vo.setDetailPlace(address.getProvince()+address.getCity()+address.getArea()+address.getDetailPlace());
                vo.setIsDefault(address.getIsDefault());
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public long insertAddress(Address address) throws Exception {
        return addressDao.insertAddress(address);
    }

    @Override
    public long updateAddress(Address address) throws Exception {
        return addressDao.updateAddress(address);
    }

    @Override
    public long delAddress(String id) throws Exception {
        return addressDao.delAddress(id);
    }

    @Override
    public Address selectAddress(String id) throws Exception {
        return addressDao.selectAddress(id);
    }
}
