package org.ivan.artshow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Artshow应用主启动类
 *
 * <p>这是整个Artshow项目的Spring Boot应用入口类。该类负责启动Spring Boot应用容器，
 * 初始化所有配置的Bean和组件，启动内嵌的Web服务器。</p>
 *
 * <p>主要功能：</p>
 * <ul>
 *   <li>扫描并加载所有带@Component、@Service、@Repository、@Controller等注解的Bean</li>
 *   <li>自动配置Spring Boot的各种功能模块</li>
 *   <li>启动嵌入式Tomcat服务器</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@SpringBootApplication
public class ArtshowApplication {
    /**
     * 应用程序入口方法
     *
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(ArtshowApplication.class, args);
    }
}
