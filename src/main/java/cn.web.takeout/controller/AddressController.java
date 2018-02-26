package cn.web.takeout.controller;

import cn.web.takeout.service.IAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/address")
public class AddressController {
    @Resource
    private IAddressService addressService;


}
