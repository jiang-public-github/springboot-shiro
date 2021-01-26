package com.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author jianglanglang
 * @Date 2020/11/6 15:30
 * @Description springboot启动类
 */
@EnableCaching
@SpringBootApplication
public class PlugboardApplication {

    //    ...．．∵ ∴★．∴∵∴ ╭ ╯╭ ╯╭ ╯╭ ╯∴∵∴∵∴
    //    ．☆．∵∴∵．∴∵∴▍▍ ▍▍ ▍▍ ▍▍☆ ★∵∴
    //    ▍．∴∵∴∵．∴▅███████████☆ ★∵
    //    ◥█▅▅▅▅███▅█▅█▅█▅█▅█▅███◤
    //    ． ◥███████████████████◤ 生活不止眼前的苟且，还有诗和远方，但诗和远方需要money。
    //    .．.．◥████████████████■◤

    public static void main(String[] args) {
        SpringApplication.run(PlugboardApplication.class, args);
    }

}
