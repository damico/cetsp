package org.jdamico.cetsp.jme;

import java.io.IOException;
import java.io.InputStream;

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

}
