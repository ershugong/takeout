package cn.web.takeout.controller;

import cn.web.takeout.model.Address;
import cn.web.takeout.service.IAddressService;
import cn.web.takeout.util.CommenUtil;
import cn.web.takeout.vo.AddressVO;
import org.springframework.stereotype.Controller;
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
     * @param province
     * @param city
     * @param area
     * @param detailPlace
     * @param userId
     * @param userName
     * @param phone
     * @param isDefault
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/insertAddress")
    public List insertAddress(String province,String city,String area,
                              String detailPlace,String userId,String userName,
                              String phone,Integer isDefault) throws Exception{
        userName = new String(userName.getBytes("iso8859-1"),"UTF-8");//GET请求参数包含中文需要转码
        Address address = new Address();
        address.setId(CommenUtil.getUUID32());
        address.setArea(area);
        address.setCity(city);
        address.setDetailPlace(detailPlace);
        address.setProvince(province);
        address.setCreateTime(new Date());
        address.setPhone(phone);
        address.setUserId(userId);
        address.setUserName(userName);
        address.setIsDefault(isDefault);
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
    public List updateAddress(String id,String province,String city,String area,
                              String detailPlace,String userId,String userName,
                              String phone,Integer isDefault) throws Exception{
        userName = new String(userName.getBytes("iso8859-1"),"UTF-8");//GET请求参数包含中文需要转码
        Address address = new Address();
        address.setId(id);
        address.setArea(area);
        address.setCity(city);
        address.setDetailPlace(detailPlace);
        address.setProvince(province);
        address.setCreateTime(new Date());
        address.setPhone(phone);
        address.setUserId(userId);
        address.setUserName(userName);
        address.setIsDefault(isDefault);
        addressService.updateAddress(address);
        return new ArrayList();
    }

    @ResponseBody
    @RequestMapping("/delAddress")
    public List delAddress(String id) throws Exception{
        addressService.delAddress(id);
        return new ArrayList();
    }



}
