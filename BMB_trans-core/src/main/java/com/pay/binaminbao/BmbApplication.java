package com.pay.binaminbao;

import com.pay.binaminbao.utils.ApplicationContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sun.tools.java.Environment;

import java.security.KeyStore;

/**
 * Hello world!
 *
 */
//@EnableDiscoveryClient
@SpringBootApplication
@EnableWebMvc
//@EnableTransactionManagement
@EnableAutoConfiguration
public class BmbApplication 
{
    
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> container.addErrorPages(new ErrorPage("/error/notFound"), new ErrorPage("/error"));
    }
    
    public static void main( String[] args )
    {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(BmbApplication.class, args);
        ApplicationContextUtils.setApplicationContext(applicationContext);
    }
}
