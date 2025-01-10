package config.videosystem.mixing;

import org.springframework.context.annotation.Bean;

import videosystem.Avengers;
import videosystem.BlankDisc;
import videosystem.DigitalVideoDisc;

public class DVDConfig {
	
	@Bean
	public DigitalVideoDisc avengers() {
		return new Avengers();
	}
	
	@Bean
	public DigitalVideoDisc avengersInfinityWar() {
		BlankDisc dvd = new BlankDisc();
		dvd.setStudio("Marvel");
		dvd.setTitle("AvengersInfinityWar");
		return dvd;
	}
		
}
