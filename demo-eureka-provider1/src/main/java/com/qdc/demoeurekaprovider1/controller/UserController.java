package com.qdc.demoeurekaprovider1.controller;

import com.qdc.demoeurekaprovider1.pojo.User;
import com.qdc.demoeurekaprovider1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//js格式控制层数据
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    //addUser方法接受前端传递的数据也是json格式
    //把json格式转换为user对象

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public boolean addUser(User user){
        return userService.addUser(user);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public boolean updateUser(User user){
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public boolean delUser(User name){
        return userService.deleteUser(name );
    }

    @RequestMapping(value = "/deatils",method = RequestMethod.GET)
    public User selectUserById(@RequestParam(value = "userid",required = true) String id){
        return userService.selectUserById(id);
    }

    @RequestMapping(value = "/userall",method = RequestMethod.GET)
    public List<User> select(){
        return userService.selectAllUsers();
    }

    @Value("{spring.cloud.client.ip.ip-address}")
    String ipaddr;
    @Value("${server.port}")
    int port;

    @RequestMapping(value = "/sayHi", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "sleep_seconds",required = true)int sleep_seconds)
            throws InterruptedException{
        System.out.println("休眠时间"+sleep_seconds);
        Thread.sleep(sleep_seconds);
        return "Hello,我在"+ipaddr+":"+port;
    }
}
