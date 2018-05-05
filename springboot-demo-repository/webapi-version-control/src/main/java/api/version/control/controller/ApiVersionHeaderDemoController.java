package api.version.control.controller;

import api.version.control.pojo.GetUserV2Response;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mr.yang on 2018/4/30.
 *
 * request header来控制版本
 */
@RestController
//@RequestMapping(value = "/api2")
@RequestMapping(value = "/api2", headers = "version=v2")
public class ApiVersionHeaderDemoController {

    //@RequestMapping(value = "/getUserById/{userId}", headers = "version=v2")
    @RequestMapping(value = "/getUserById/{userId}")
    public Object getUserInfoV3(@PathVariable("userId") Integer userId) {
        return new GetUserV2Response(userId, "小明", "version 2");
    }
}
