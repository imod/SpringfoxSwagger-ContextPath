package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiResourceController;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringfoxSwaggerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringfoxSwaggerApplication.class, args);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/documentation/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/documentation/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Controller
	@ApiIgnore
	@RequestMapping("/documentation/swagger-resources")
	public static class MyApiResourceController extends ApiResourceController {
		public MyApiResourceController(SwaggerResourcesProvider swaggerResources) {
			super(swaggerResources);
			// how to set securityConfiguration and uiConfiguration? both are
			// @Autowired by field injection :(
		}
	}

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("myapi").select().build();
	}

	// just a dummy controller
	@RestController
	@RequestMapping("/api")
	public class MyController {

		@GetMapping
		public String home() {
			return "hello world";
		}
	}
}
