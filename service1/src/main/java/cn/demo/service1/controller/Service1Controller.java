package cn.demo.service1.controller;

import cn.demo.AbstractController;
import cn.demo.service1.client.Service0Client;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*; 
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Leo
 * @Blog: http://blog.csdn.net/lc0817
 * @CreateTime: 2017/1/19 14:21
 * @Description:
 */
@Api(value = "Service1", description = "用户类") 
@RestController
public class Service1Controller extends AbstractController {
	
	@ApiModel(description="用户对象user") 
	public static class User {
        private int id;
        
        @ApiModelProperty(value="用户名",name="name")
        private String name;

        private Student student;

        public User() {
        }

        public int getId() {
            return id;
        }

        public User setId(int id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }

        public Student getStudent() {
            return student;
        }

        public User setStudent(Student student) {
            this.student = student;
            return this;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", student=" + student +
                    '}';
        }
    }

    public static class Student {
        private String stuName;

        public Student() {
        }

        public String getStuName() {
            return stuName;
        }

        public Student setStuName(String stuName) {
            this.stuName = stuName;
            return this;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "stuName='" + stuName + '\'' +
                    '}';
        }
    }

    @Autowired
    Service0Client service0Client;

    @ApiOperation(value = "Service0方法调用测试", notes = "", response = String.class)
    @GetMapping("test0")
    public String test0(
    		@ApiParam(name="aaaa", value = "The id of aaa is test id" ,  required=true ) String aaaa
    		)
    {
 
        return service0Client.test(aaaa);
    }

    @GetMapping("add")
    public String add() {
 
        Student stuName = new Student()
                .setStuName("stuName");
        User asd = new User()
                .setId(0)
                .setName("asd")
                .setStudent(stuName);

        System.out.println(asd.toString());
        return service0Client.user(asd);
    }
    
    @GetMapping("user/{userId}")
    User user(
            @PathVariable String userId
    ) {
        try {
            System.out.println("hello:" + userId); 
           
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage()); 
        }
        User uu=new User();
        return uu;
    }
    
    @ApiOperation(value = "测试", notes = "", response = String.class)
    @GetMapping("test")
    String test()
    {
    	return "success,this is Service1 test fun";
    }
    
    @Value("${ip}")
    private String ip;
    @Value("${port}")
    private String port;
    @Value("${spring.cloud.config.profile}")
    private String profile;

    @ApiOperation(value = "获取git配置", notes = "", response = String.class)
    @RequestMapping("/getProperties")
    public String getProperties(){
        return ip + " : " + port+":spring.cloud.config.profile="+profile;
    }
    
}
