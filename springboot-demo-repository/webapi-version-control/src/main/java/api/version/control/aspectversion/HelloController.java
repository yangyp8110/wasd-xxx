package api.version.control.aspectversion;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mr.yang on 2018/5/4.
 */
@RequestMapping("{version}")
@RestController
public class HelloController {

    @ApiVersion(1)
    @RequestMapping("hello")
    public String hello1(){
        return "hello1";
    }

    @ApiVersion(2)
    @RequestMapping("hello")
    public String hello2(){
        return "hello2";
    }
}
