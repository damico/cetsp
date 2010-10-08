package org.jdamico.cetsp.jme;



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
			data = "Lentidao centro: N/A \n Lentidão leste: N/A \n Lentidão norte: N/A \n Lentidão oeste: N/A \n Lentidão sul: N/A \n";
		}
		zonas = new String[6];
		int i=0;
		
		int init = 0;
		while(i<6){
		for(int j=0; j<data.length(); j++){
			if(data.charAt(j)=='\n'){
				
				zonas[i] = data.substring(init, j);
				init = j;
				i++;
				
			}
		}	
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