package api.version.control.controller;

import api.version.control.pojo.GetUserV2Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mr.yang on 2018/4/30.
 *
 * Content-Type控制api版本
 */
@RestController
@RequestMapping(value = "/api3")
public class ApiVersionContentTypeDemoController {
    @Autowired
    private HttpServletRequest request;

    /**
     * 请求的是 application/vnd.apiversioncontrol.v1+json
     * 返回的是 application/json;charset=UTF-8
     * v1 是api版本
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserById/{userId}", consumes = "application/vnd.apiversioncontrol.v1+json")
    public Object getUserInfoV11(@PathVariable("userId") Integer userId) {
        return new GetUserV2Response(userId, "小明", "version 2");
    }

    /**
     * 请求、返回Content-Type都是 application/vnd.apiversioncontrol.v1+json
     * v1 ：为api版本
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserById/{userId}", consumes = "application/vnd.apiversioncontrol.v2+json", produces = "application/vnd.apiversioncontrol.v2+json")
    public Object getUserInfoV12(@PathVariable("userId") Integer userId) {
        return new GetUserV2Response(userId, "小明", "version 2");
    }

    /**
     * 请求、返回Content-Type都是 application/vnd.apiversioncontrol+json;version=v2
     * v2 ：为api版本
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserById/{userId}", consumes = "application/vnd.apiversioncontrol+json;version=v2", produces = "application/vnd.apiversioncontrol+json;version=v2")
    public Object getUserInfoV2(@PathVariable("userId") Integer userId) {
        return new GetUserV2Response(userId, "小明", "version 2");
    }
}
