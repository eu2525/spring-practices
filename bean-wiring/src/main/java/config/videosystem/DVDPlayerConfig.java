package config.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import videosystem.Avengers;
import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;
import videosystem.IronMan;

@Configuration
public class DVDPlayerConfig {
	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	
	@Bean
	public DigitalVideoDisc ironMan() {
		return new IronMan();
	}
	
	// DI(의존성 주입) : DigitalVideoDisc를 DVDPlayer 생성할 때 주입해줘야함
	// 방법 1 : Bean 생성 메소드(avengers())를 직접 호출하는 방법
	// 생성자 주입
	@Bean
	public DVDPlayer dvdPlayer1() {
		return new DVDPlayer(avengers());
	}
	
	// DI(의존성 주입)
	// 방법 2 : Parameter로 Bean을 전달
	// 생성자 주입
	@Bean("DVDPlayer2nd")
	public DVDPlayer dvdPlayer2(Avengers dvd1) {
		return new DVDPlayer(dvd1);
	}
	
	// DI(의존성 주입)
	// 방법 3 : Parameter로 Bean을 전달
	// setter 주입
	@Bean
	public DVDPlayer dvdPlayer3(@Qualifier("ironMan") DigitalVideoDisc dvd) {
		DVDPlayer dvdPlayer = new DVDPlayer();
		dvdPlayer.setDVD(dvd);
		
		return dvdPlayer;
	}
}
