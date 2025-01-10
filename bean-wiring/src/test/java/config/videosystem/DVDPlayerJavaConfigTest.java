package config.videosystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={DVDPlayerConfig.class})
public class DVDPlayerJavaConfigTest {
	// 같은 Type(or Interface)인 Bean이 2개 이상 있는 경우에는 Qualifier("<생성메소드이름>")로 특정해줘야함.
	@Autowired
	@Qualifier("avengers")
	private DigitalVideoDisc dvd1;
	
	@Autowired
	@Qualifier("ironMan")
	private DigitalVideoDisc dvd2;
	
	@Test
	public void testDVD1() {
		assertNotNull(dvd1);
	}

	@Autowired
	@Qualifier("dvdPlayer1")
	private DVDPlayer dvdPlayer1;
	
	// 같은 Type(or Interface)인 Bean이 2개 이상 있는 경우에는 Qualifier("<Bean이름>")로 특정해줘야함.
	@Autowired
	@Qualifier("DVDPlayer2nd")
	private DVDPlayer dvdPlayer2;
	
	
	@Test
	public void testDVDPlayer1() {
		assertNotNull(dvdPlayer1);
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer1.play());
	}
	
	@Test
	public void testDVDPlayer2() {
		assertNotNull(dvdPlayer2);
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer2.play());
	}
	
}
