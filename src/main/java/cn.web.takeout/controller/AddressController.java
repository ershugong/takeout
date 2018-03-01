package cn.web.takeout.controller;

import cn.web.takeout.model.Address;
import cn.web.takeout.service.IAddressService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.AddressVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Resource
    private IAddressService addressService;

    /**
     * 添加地址
     * @param address
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/insertAddress")
    public List insertAddress(@ModelAttribute("form") Address address) throws Exception{
        address.setId(CommenUtil.getUUID32());
        address.setCreateTime(new Date());
        addressService.insertAddress(address);
        return new ArrayList();
    }

    /**
     * 获取用户的所有地址(当isDefault有值时代表获取默认地址)
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getUserAddress")
    public List getUserAddress(String userId,Integer isDefault) throws Exception{
        List<AddressVO> result = addressService.getAddressByUserId(userId,isDefault);
        return result;
    }


    @ResponseBody
    @RequestMapping("/updateAddress")
    public List updateAddress(@ModelAttribute("form") Address address) throws Exception{
        addressService.updateAddress(address);
        return new ArrayList();
    }

    @ResponseBody
    @RequestMapping("/delAddress")
    public List delAddress(String id) throws Exception{
        addressService.delAddress(id);
        return new ArrayList();
    }

    @ResponseBody
    @RequestMapping("/selectAddress")
    public Address selectAddress(String id) throws Exception{
        return addressService.selectAddress(id);
    }

    @ResponseBody
    @RequestMapping("/setDefaultAddress")
    public List setDefaultAddress(String id) throws Exception{
        Address address = addressService.selectAddress(id);
        AddressVO defaultAddress = addressService.getAddressByUserId(address.getUserId(),CommenUtil.DEFAULT_ADDRESS).get(0);
        Address addressTemp = selectAddress(defaultAddress.getId());
        addressTemp.setIsDefault(CommenUtil.COMMON_ADDRESS);
        this.updateAddress(addressTemp);
        address.setIsDefault(CommenUtil.DEFAULT_ADDRESS);
        this.updateAddress(address);
        return new ArrayList();
    }



}
