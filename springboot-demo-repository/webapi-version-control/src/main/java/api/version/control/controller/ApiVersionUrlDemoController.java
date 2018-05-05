package api.version.control.controller;

import api.version.control.pojo.GetUserV1Response;
import api.version.control.pojo.GetUserV2Response;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mr.yang on 2018/4/30.
 *
 * URL来控制版本
 */
@RestController
@RequestMapping("/api")
public class ApiVersionUrlDemoController {
    /**
     * http://localhost:8090/api/v1/getUser/111
     * {"userId":111,"userName":"小明"}
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/v1/getUser/{userId}")
    public GetUserV1Response getUserInfoV1(@PathVariable("userId") Integer userId) {
        return new GetUserV1Response(userId, "小明");
    }

    /**
     * http://localhost:8090/api/v2/getUser/111
     * {"userId":111,"userName":"小明","notes":"人气很高的一名小学生"}
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/v2/getUser/{userId}")
    public GetUserV2Response getUserInfoV2(@PathVariable("userId") Integer userId) {
        return new GetUserV2Response(userId, "小明", "人气很高的一名小学生");
    }


    /**
     * http://localhost:8090/api/getUser/111?version=v1
     * {"userId":111,"userName":"小明"}
     * <p>
     * http://localhost:8090/api/getUser/111?version=v2
     * {"userId":111,"userName":"小明","notes":"version 2"}
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUser/{userId}")
    public Object getUserInfo(@PathVariable("userId") Integer userId, @RequestParam("version") String version) {
        if ("v1".equals(version)) {
            return new GetUserV1Response(userId, "小明");
        }
        return new GetUserV2Response(userId, "小明", "version 2");
    }
}
