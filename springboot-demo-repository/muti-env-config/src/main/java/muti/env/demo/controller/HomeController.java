package muti.env.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import muti.env.demo.configdata.DataConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mr.yang on 2018/4/29.
 */
@Api("home controller")
@RestController
public class HomeController {
    @Autowired
    private DataConfig dataConfig;

    @RequestMapping("/env")
    @ApiOperation("env")
    public Object testEnv() {
        return dataConfig;
    }
}
