package com.frank;




import tk.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "com.frank.**.mapper")
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ServletComponentScan
public class MybatisGereratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisGereratorApplication.class, args);
	}

}
