package it.uniroma2.isw2.lab2.massimostanzione;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;

public class Main {
	public static void main(String[] args) {
		IdPatternRetriever ipr = new IdPatternRetriever();
		System.out.println("This program prints all the IDs from the commits matching the following requisites.\n");
		System.out.println("UserId:\t\t\t\t" + ipr.getUser());
		System.out.println("Repository name:\t\t" + ipr.getRepo());
		System.out.println("Pattern to find in the message: \"" + ipr.getPattern() + "\"\n");
		System.out.println("Which method you want to use?\n" + "1 - Local, via git shell\n"
				+ "2 - Remote, via GitHub API\n" + "3 - Both\n" + "Insert your chosen method: ");
		Scanner inScan = new Scanner(System.in);
		Integer scelta = inScan.nextInt();
		System.out.println("=========================================================\n");
		boolean allowed = false;
		// TODO check int
		switch (scelta) {
		case 1:
			try {
				ipr.local();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				ipr.remote();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				ipr.local();
				ipr.remote();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			allowed = true;
			break;

		default:
			// TODO permetti di nuovo scelta
			System.out.println("noooooooooooo");
			break;
		}
		return;
	}
}
