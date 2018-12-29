package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.service.UserService;
import com.springmvc.util.Productor;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class ViewController {

    private static final Logger logger = Logger.getLogger(ViewController.class);

    @Resource(name = "UserService")
    private UserService userService;

    @RequestMapping("/index")
    public String index(){

        return "index";
    }

    @RequestMapping("/find")
    @ResponseBody
    public Map<String,Object> find(User user, HttpServletRequest request){

        Map<String,Object> map = new HashedMap();
        System.err.println("你已通过springMVC进入controller方法。。。。");
        logger.info("你已通过springMVC进入controller方法。。。。");
        User loginuser = userService.findByUsernameAndPwd(user.getUsername(),user.getPassword());
        if(loginuser != null){
            map.put("result","success");
        }else {
            map.put("result","fail");
        }
        return map;
    }

    @RequestMapping("/success")
    public String success(){
        System.err.println("登录成功。。。。");
        logger.info("登录成功。。。。");
        return "success";
    }

    @Resource
    private Productor productor;

    private static final String HELLO_KEY="HELLO_KEY";
    private static final String HELLO_KEY_002="HELLO_KEY_002";
    private static final String HELLO_KEY_003="HELLO_KEY_003";

    /**
     * @Description 整合rabbitMQ和reids，测试方法
     * @author liyu from yitang, E-mail: 15381002414@163.com
     * @Date  2018/7/19 14:54
     */
    @RequestMapping("/send")
    @ResponseBody
    public void sendMessage(){
        String msg="hello world!!";
        productor.sendMessage(msg,HELLO_KEY);
//        productor.sendMessage(msg,HELLO_KEY_002);
//        productor.sendMessage(msg,HELLO_KEY_003);
    }

    /**
     *
     * @Description aop测试用例
     * @author liyu from yitang, E-mail: 15381002414@163.com
     * @Date  2018/7/23 16:39
     */
    @PostMapping("/queryUser")
    @ResponseBody
    public Object queryUser( String str){
        logger.info("解密后的参数为："+str);
        return str;
    }
}
