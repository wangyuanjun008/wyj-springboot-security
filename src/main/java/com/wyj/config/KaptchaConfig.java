package com.wyj.config;

import java.util.Properties;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

/**
 * 验证码配置类
 * 
 * 
 * @author：WangYuanJun
 * @date：2017年12月12日 下午8:19:17
 */
@SpringBootApplication
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "yes");  
        properties.setProperty("kaptcha.border.color", "105,179,90");  
        properties.setProperty("kaptcha.textproducer.font.color", "blue");  
        properties.setProperty("kaptcha.image.width", "110");  
        properties.setProperty("kaptcha.image.height", "40");  
        properties.setProperty("kaptcha.textproducer.font.size", "30");  
        properties.setProperty("kaptcha.session.key", "code");  
        properties.setProperty("kaptcha.textproducer.char.length", "4");  
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);  
        defaultKaptcha.setConfig(config);  
          
        return defaultKaptcha;  
    }
    
}
