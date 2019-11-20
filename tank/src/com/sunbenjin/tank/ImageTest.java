package com.sunbenjin.tank;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

class ImageTest {

	@Test
	void test() {
		//fail("Not yet implemented");
		//assertNotNull(new Object());
		try {
			BufferedImage images = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(images);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
