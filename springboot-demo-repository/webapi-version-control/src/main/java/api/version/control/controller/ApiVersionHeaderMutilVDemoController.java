package api.version.control.controller;

import api.version.control.pojo.GetUserV1Response;
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
@RequestMapping(value = "/api2")
public class ApiVersionHeaderMutilVDemoController {
    /**
     * 和 api.version.control.controller.ApiVersionHeaderDemoController#getUserInfoV3(java.lang.Integer) 命名冲突
     *
        @RequestMapping(value = "/getUserById/{userId}", headers = "version=v2")
        public Object getUserInfoV2(@PathVariable("userId") Integer userId) {
            return new GetUserV2Response(userId, "小明", "version 2");
        }
    */

    @RequestMapping(value = "/getUserById/{userId}", headers = "version=v1")
    public Object getUserInfoV1(@PathVariable("userId") Integer userId) {
        return new GetUserV1Response(userId, "小明");
    }
}
