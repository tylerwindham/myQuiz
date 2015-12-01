import java.io.*;
import java.net.*;
import java.util.*;
import static java.lang.System.*;

public class getFile {
	public void urlList(int numStudents) throws IOException{
		List<String> x = new ArrayList<String>();
		for(int i = 1; i <= numStudents; i++){
			x.add("s" + i + ".txt");
		}
		readFile(x);
	}
	
	public void readFile(List<String> urlList){
		try{
			String urlEnd = "";
			for(int i = 0; i < urlList.size(); i++){
				urlEnd = urlList.get(i);
				URL url = new URL("http://students.cse.tamu.edu/iks5005/"+urlEnd);
				 // create a urlconnection object
				URLConnection urlConnection = url.openConnection();

				// wrap the urlconnection in a bufferedreader
				BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

				String studentResult = "";
				while((studentResult = in.readLine()) != null){
					out.println(studentResult);
			}
			in.close();
			}
			
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException{
		JavaApplication15 j = new JavaApplication15();
		j.urlList(2);
	}
	
}
