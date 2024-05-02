import java.util.*;
import java.io.*;

public class Convert {
	public static void convertFile(String fileName) throws IOException, FileNotFoundException {
		Scanner fileSC = new Scanner(new File(fileName));
		String tripFile = "triplog.csv";
		
		File resultFile = new File(tripFile);
		resultFile.setWritable(true);
		
		FileWriter fileWrite = new FileWriter(resultFile);
		
		fileSC.nextLine();
		fileSC.nextLine();
		fileSC.nextLine();
		
		String firstLine = "Time,Latitude,Longitude\n";
		fileWrite.write(firstLine);
		
		int time = 0;
		
		while(fileSC.hasNext()) {
			String line = fileSC.nextLine();
			
			if (line.isEmpty() || !line.contains("lat=") || !line.contains("lon=")){
			}
			else {
				String lineLat = "";
				String lineLon = "";
		
				int indexOfLat = line.indexOf("lat=") + 2;
				int indexOfLon = line.indexOf("lon=") + 2;
				
				for (int i = indexOfLat; i < indexOfLon; i++) {
					if ((line.charAt(i) >= '0' && line.charAt(i) <= '9') || line.charAt(i) == '.' || line.charAt(i) == '-') {
						lineLat = lineLat + line.charAt(i);
					}
				}
		
				for (int i = indexOfLon; i < line.length(); i++) {
					if ((line.charAt(i) >= '0' && line.charAt(i) <= '9') || line.charAt(i) == '.' || line.charAt(i) == '-') {
						lineLon = lineLon + line.charAt(i);
					}
				}
				String timeLatLon = time + "," + lineLat + "," + lineLon + '\n';
				fileWrite.write(timeLatLon);
				
				time = time + 5;
			}
		}
		
		fileSC.close();
		resultFile.setWritable(false);
		fileWrite.close();
			
	}
}