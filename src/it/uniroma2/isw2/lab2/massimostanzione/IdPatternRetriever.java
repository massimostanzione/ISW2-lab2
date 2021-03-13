package it.uniroma2.isw2.lab2.massimostanzione;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class IdPatternRetriever {
	private String user = "massimostanzione";
	private String repo = "ISW2-lab1";
	private String pattern = "added";

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONArray readJsonArrayFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONArray json = new JSONArray(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			// TODO!!! ev. if che riconosce le brackets
			// rimuovendo le brackets: JSONObject json = new
			// JSONObject(jsonText.substring(1, jsonText.length()-1));
			JSONObject json = new JSONObject("{" + jsonText + "}");
			return json;
		} finally {
			is.close();
		}
	}

	public void local() throws IOException {
		System.out.println("Metodo LOCALE (tramite shell git)");
		System.out.println("SHA-ID                                   Comment");
		System.out.println("---------------------------------------------------------");
		Process ip = Runtime.getRuntime().exec("git clone https://github.com/massimostanzione/ISW2-lab1");
		// opzione --pretty=online basta e avanza per la stampa richiesta (ID spazio
		// messaggio)
		ip = Runtime.getRuntime().exec("git --git-dir ./ISW2-lab1/.git log --pretty=oneline --grep=added");
		BufferedReader input = new BufferedReader(new InputStreamReader(ip.getInputStream()));
		Scanner s = new java.util.Scanner(input).useDelimiter("\\A");// separa righe
		String val = "";
		val = s.hasNext() ? s.next() : "";
		System.out.println(val);
		Runtime.getRuntime().exec("rmdir ISW2-lab1"); // gira senza sudo?
	}

	public void remote() throws IOException, JSONException {
		System.out.println("Metodo REMOTO (tramite GitHub API)");
		Integer i = 0, total = 1;
		String url = "https://api.github.com/repos/" + getUser() + "/" + this.getRepo() + "/commits";
		JSONArray json = readJsonArrayFromUrl(url);
		total = json.length() - 1;
		System.out.println("SHA-ID                                   Comment");
		System.out.println("---------------------------------------------------------");
		for (; i <= total; i++) {
			JSONObject obj = json.getJSONObject(i); // gli oggetti numerati
			JSONObject commit = obj.getJSONObject("commit"); // i commit
			// System.out.println(commit.get(0));
			String msg = commit.getString("message");
			if (msg.contains(getPattern())) {
				System.out.println(obj.get("sha") + " " + msg);
			}
		}
	}

	// getters, setters
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getRepo() {
		return this.repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

}