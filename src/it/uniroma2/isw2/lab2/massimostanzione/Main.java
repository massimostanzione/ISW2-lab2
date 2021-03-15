
package it.uniroma2.isw2.lab2.massimostanzione;

import java.io.IOException;
import java.util.Scanner;

import org.json.JSONException;

public class Main {
	private static IdPatternRetriever ipr;
	private static Scanner inScan = new Scanner(System.in);

	public static void main(String[] args) throws IOException, JSONException, InterruptedException {
		ipr = new IdPatternRetriever();
		boolean exit = false;
		do {
			System.out.println("This program prints all the IDs from the commits matching the following requisites.\n");
			System.out.println("UserId:\t\t\t\t" + ipr.getUser());
			System.out.println("Repository name:\t\t" + ipr.getRepo());
			System.out.println("Pattern to find in the message: \"" + ipr.getPattern() + "\"\n");
			System.out.println("Which method you want to use?");
			System.out.println("1 - Local, via git shell (*)");
			System.out.println("2 - Remote, via GitHub API");
			System.out.println("3 - Both (*)");
			System.out.println("4 - Change parameters\n");
			System.out.println("(*) NOTICE: option no. 1-3 will clone the selected repository without deleting the working copy.\n");
			System.out.println("Insert your chosen method: ");
			Integer scelta = inScan.nextInt();
			System.out.println("=========================================================\n");
			// TODO check int

			switch (scelta) {
			case 1:
				ipr.local();
				exit = true;
				break;
			case 2:
				ipr.remote();
				exit = true;

				break;
			case 3:
				ipr.local();
				ipr.remote();
				exit = true;
				break;
			case 4:
				ipr = changePar();
				break;

			default:
				// TODO permetti di nuovo scelta
				System.out.println("noooooooooooo");
				break;
			}
		} while (!exit);
		return;
	}

	private static IdPatternRetriever changePar() {
		askForInput("");
		ipr.setUser(askForInput("Username: "));
		ipr.setRepo(askForInput("Repository name: "));
		ipr.setPattern(askForInput("Pattern to search for: "));
		return ipr;
	}

	private static String askForInput(String msg) {
		System.out.println(msg);
		return inScan.nextLine();
	}
}
