import java.util.ArrayList;
import java.util.Scanner;

public class GPS {

	public static void main(String[] args) {
		GPS gps = new GPS();

		ArrayList<waypoint> waypoints = new ArrayList<>(); // Create an ArrayList to store waypoints

		Scanner scanner = new Scanner(System.in); // Create a Scanner to handle input entered by the user

		double totalDistanceTravelled = 0.0, x = 0.0, y = 0.0; // The initial values of x, y, t, and total distance
																// traveled
																// are initialized to 0.
		int t = 0;

		boolean choice = true;

		// Inner class representing a waypoint
		do {
			// Prompt the user to enter X coordinate
			System.out.print("Enter X co-ordinate(negative to end): ");
			x = scanner.nextDouble();
			if (x < 0) // An input of X < 0 marks the end of the list of entries
				break;

			// Prompt the user to enter Y coordinate
			System.out.print("Enter Y co-ordinate: ");
			y = scanner.nextDouble();

			// Prompt the user to enter Time Stamp (seconds)
			System.out.print("Enter Time Stamp (seconds):");
			t = scanner.nextInt();
			waypoints.add(new waypoint(x, y, t));

		} while (choice);

		scanner.close();

		for (int i = 1; i < waypoints.size(); i++) {
			waypoint w1 = waypoints.get(i - 1);
			waypoint w2 = waypoints.get(i);

			// Calculate change in X and Y coordinates
			double xChange = (w2.X - w1.X);
			double yChange = (w2.Y - w1.Y);

			// Calculate the distance between waypoints using the distance formula
			double distance = Math.sqrt((xChange * xChange) + (yChange * yChange));
			totalDistanceTravelled += distance;
		}
		totalDistanceTravelled = totalDistanceTravelled * 0.1; // Scaling total distance traveled to miles

		int totalTime = waypoints.get(waypoints.size() - 1).T - waypoints.get(0).T; // Calculates total time
																					// traveled
																					// using the difference between
																					// the first time stamp and the last
																					// time stamp

		double averageSpeed = (totalDistanceTravelled) / (totalTime / 3600.0); // Calculates average speed in miles
																				// per
																				// hour by converting total time to
																				// hours

		// Formats distance to output in two decimal places
		System.out.println("Total distance the hiker traveled: " + String.format("%.2f miles", totalDistanceTravelled)
				+ " in " + totalTime + " seconds");

		// Formats average speed to output in two decimal places
		System.out.printf("The average speed is: %.2f miles per hour%n", averageSpeed);
	}

	private static class waypoint {
		double X;
		double Y;
		int T;

		// Constructor created for the waypoint class
		waypoint(double X, double Y, int T) {
			this.X = X;
			this.Y = Y;
			this.T = T;
		}
	}
}
