package de.firma.protocol;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.lang.ModuleLayer;
import java.util.ArrayList;

public class Protocol {

	final static String CRLF = "\r\n";

	private boolean inProgress;

	public String startConversation() {
		inProgress = true;
		return "Hallo!"  + CRLF + CRLF + "Wie sind Deine Befehle?";
	}

	public String processRequest(BufferedReader in) throws Exception {
		while (true) {
			String headerLine = in.readLine();
			if (headerLine.equals(CRLF) || headerLine.equals("")) {
				break;
			}
			StringTokenizer stringTokenizer = new StringTokenizer(headerLine);
			String requestMethod = stringTokenizer.nextToken();
			if (requestMethod.equals("GET")) {
				String token = stringTokenizer.nextToken();
				if (token != null) {
					return processToken(token.substring(1, token.length()).toLowerCase());
				}
			}
		}
		return null;
	}

	private String processToken(String token) {
		if (token.contains("bye")) {
			inProgress = false;
			return "Bye";
		} else if (token.contains("modules")) {
			return getBootLayerModules();
		} else if (token.length()>0) {
			return "Ich kenne den Befehl " + token + " nicht.";
		}
		return "";
	}
	
	private String getBootLayerModules() {
        ArrayList<String> moduleNames = new ArrayList<>();
        ModuleLayer.boot()
          .modules()
          .stream()
          .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
          .forEach(mod -> moduleNames.add(mod.getName()));
        return "Boot layer modules:" + CRLF + moduleNames;		
	}
	
	public boolean isInProgress() {
		return inProgress;
	}
}
