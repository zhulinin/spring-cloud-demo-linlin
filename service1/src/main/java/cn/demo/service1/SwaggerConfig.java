package cn.demo.service1; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 
public class SwaggerConfig extends WebMvcConfigurerAdapter{

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	  registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
          registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

          registry.addResourceHandler("/webjars/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
	 
	 
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "cn.demo.service1.controller";
    public static final String VERSION = "1.0.0";

    private ApiInfo buildApiInf () {
        return new ApiInfoBuilder()
                .title("Service1 API")
                .description("这是一个测试的API接口文档 ")
                .license("容大智造开放平台")
                .licenseUrl("http://www.cenntroauto.cn")
                .termsOfServiceUrl("")
                .version(VERSION)
                .contact(new Contact("","", "zll@cenntroauto.cn"))
                .build();
    }

    @Bean 
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())    //.apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))//要注释的接口名
                .paths(PathSelectors.any())
                .build();
    }
}