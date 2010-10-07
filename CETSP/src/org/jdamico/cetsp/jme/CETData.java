package org.jdamico.cetsp.jme;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

public class CETData {
	public String getViaStreamConnection(String url) throws IOException {
		StreamConnection c = null;
		InputStream s = null;
		StringBuffer b = new StringBuffer();
		try {
			c = (StreamConnection) Connector.open(url);
			s = c.openInputStream();
			int ch;
			while ((ch = s.read()) != -1) {
				b.append((char) ch);
			}
		} finally {
			if (s != null) {s.close();}
			if (c != null) {c.close();}
		}
		return b.toString();
	}
	public String getDateTime()
	{
		String retorno;
				
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int day = c.get(Calendar.DAY_OF_MONTH);
		int month = c.get(Calendar.MONTH);
		int year = c.get(Calendar.YEAR);
		
		int h = c.get(Calendar.HOUR_OF_DAY);
		int m = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		
		
		retorno = "data:"+day+"/"+month+"/"+year+" ["+h+":"+m+":"+s+"]";
		return retorno;
		
	}

}