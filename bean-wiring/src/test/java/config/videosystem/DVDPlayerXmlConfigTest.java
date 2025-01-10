package config.videosystem;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import videosystem.DigitalVideoDisc;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"classpath:config/videosystem/applicationContext.xml"})
public class DVDPlayerXmlConfigTest {
	// error
	// XML Bean Configuration(Avengers)에서는 자동으로 id를 부여하지 않는다.
	// 따라서 그냥 실행하면 같은 Interface Type이 2개 이상(Avengers, IronMan) 있어서 어떤 객체를 주입할지 몰라서 오류가 발생함 
	// @Autowired
	DigitalVideoDisc dvd1;
	
	@Autowired
	@Qualifier("ironMan")
	DigitalVideoDisc dvd2;
	
	@Disabled
	@Test
	public void testDVD1() {
		assertNotNull(dvd1);
	}
	
	@Test
	public void testDVD2() {
		assertNotNull(dvd2);
	}
}
