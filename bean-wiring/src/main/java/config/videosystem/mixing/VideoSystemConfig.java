package config.videosystem.mixing;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/*
 * JavaConfig3 <---- JavaConfig2, JavaConfig1을 하나로 합침
 */

@Configuration
@Import({DVDConfig.class, DVDPlayerConfig.class})
public class VideoSystemConfig {
	
}
