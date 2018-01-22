package api.doc.demo.controller;

import api.doc.demo.model.UserModel;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by mr.yang on 2018/1/22.
 */
@Api(value = "api", description = "the swagger API")
@RequestMapping("/api")
@RestController
public class ApiController {
    @ApiOperation(value="获取用户列表", notes="获取所有用户列表",produces = "application/json")
    @RequestMapping(value="/getList", method= RequestMethod.GET)
    public List<UserModel> getUserList() {
        return getDemoList();
    }

    @ApiOperation(value="按userId查询用户信息", notes="按userId查询用户信息",produces = "application/json")
    /**ApiResponses : 默认Response的基础上增加新的Response说明*/
//    @ApiResponses(value = {@ApiResponse(code = 405,message = "Invalid input",response = Integer.class)})

    /**描述接口参数*/
    @ApiImplicitParam(name = "userId",value = "用户ID",dataType = "int",paramType = "path")
    /**多个接口参数*/
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户ID",paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "userName",value = "用户名称",paramType = "form",dataType = "string")
    })
    @RequestMapping(value="/get/byUserId", method= RequestMethod.GET)
    public UserModel getUserByUserId(@RequestParam("userId") Integer userId) {
        Optional<UserModel> first = getDemoList().stream().filter(x -> x.getUserId().equals(userId)).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        return null;
    }

    @ApiOperation(value="查询用户", notes="根据url的id来查询对象")
    @RequestMapping(value="/get/{userId}", method = RequestMethod.GET)
    public UserModel getUser(@PathVariable Integer userId) {
        Optional<UserModel> first = getDemoList().stream().filter(x -> x.getUserId().equals(userId)).findFirst();
        if (first.isPresent()) {
            return first.get();
        }
        return null;
    }

    @ApiOperation(value="创建用户", notes="json格式传递数据",produces = "application/json")
    @RequestMapping(value="/saveUser", method= RequestMethod.POST)
    public UserModel saveUser(@RequestBody UserModel user) {

        return user;
    }

    private List<UserModel> getDemoList() {
        List<UserModel> list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            UserModel demoModel = new UserModel();
            demoModel.setUserId(1000 + i);
            demoModel.setUserName(String.valueOf(1000 + i));
            list.add(demoModel);
        }

        return list;
    }
}
