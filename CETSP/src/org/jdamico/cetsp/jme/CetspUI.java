package org.jdamico.cetsp.jme;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class CetspUI extends Canvas {

	private String[] zonas;

	public CetspUI(String[] zonas) {
		this.zonas = zonas;
	}

	protected void paint(Graphics g) {
		g.setColor(0xffffff);
		g.fillRect(0, 0, getWidth(), getHeight());
		Image image = null;
		try {
			image = Image.createImage("/sp.png");
		} catch (IOException ex) {

			g.drawString("Failed to load image!", 0, 0, Graphics.TOP | Graphics.LEFT);
			return;
		}
		g.drawImage(image, (getWidth()/2), 10, Graphics.TOP | Graphics.HCENTER);
		g.setColor(0x000000);


		for(int i = 0; i<5; i++){
			g.drawString(zonas[i], 30, (i*11)+100, Graphics.TOP|Graphics.LEFT);

		}

	}

}
