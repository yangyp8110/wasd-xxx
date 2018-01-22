package api.doc.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by mr.yang on 2018/1/22.
 */
@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "api.doc.demo.controller";
    public static final String VERSION = "1.0.0";

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                /**api接口包扫描路径*/
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))
                /**可以根据url路径设置哪些请求加入文档，忽略哪些请求*/
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                /**设置文档的标题*/
                .title("Swagger2 接口文档示例")
                /**设置文档的描述*/
                .description("更多内容请关注：http://www.abc.com")
                /**设置文档的联系方式*/
                .contact(new Contact("create by XXX", "http://www.abc.com", "xxxx.@xxx.com"))
                /**设置文档的License信息->1.3 License information*/
                .termsOfServiceUrl("www.abc.com")
                .license("xxx")
                .licenseUrl("http://www.xxx.com")
                /**设置文档的版本信息-> 1.1 Version information*/
                .version(VERSION)
                .build();
    }
}
