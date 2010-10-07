package org.jdamico.cetsp.jme;

import gov.nist.core.StringTokenizer;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

public class Cetsp extends MIDlet implements CommandListener {
	
	private Display display;
	private Command cmdExit;
	private String[] zonas;
	private String datetime;
	
	public Cetsp() {
		display = Display.getDisplay(this);
		cmdExit = new Command("Exit", Command.SCREEN,1);
		CETData cet = new CETData();
		datetime = cet.getDateTime();
        String data = null;
        try {
			data = cet.getViaStreamConnection("http://www.dcon.com.br/cet/index.php");
		} catch (IOException e) {
			e.printStackTrace();
		}
		zonas = new String[6];
		int i=0;
		StringTokenizer st = new StringTokenizer(data, '\n');
		while(st.hasMoreChars()){
			String e = st.nextToken();
			zonas[i] = e;
			System.out.println(">>> "+zonas[i]);
			i++;
		}
	}

	protected void destroyApp(boolean unconditional) {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		destroyApp(false);
		notifyDestroyed();

	}

	protected void startApp() throws MIDletStateChangeException {		
		
		CetspUI ui = new CetspUI(zonas, datetime);
		ui.addCommand(cmdExit);
		ui.setCommandListener(this);
		display.setCurrent(ui);
	}

	public void commandAction(Command c, Displayable d) {
		if(c==cmdExit){
			destroyApp(false);
			notifyDestroyed();
		}
		
	}

}