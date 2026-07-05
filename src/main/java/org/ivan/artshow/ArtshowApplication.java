package org.ivan.artshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Artshow应用主启动类
 * @author Ivan Horn
 * @since 1.0.0
 */
@SpringBootApplication
public class ArtshowApplication {
    /**
     * 应用程序入口方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ArtshowApplication.class, args);
    }
}