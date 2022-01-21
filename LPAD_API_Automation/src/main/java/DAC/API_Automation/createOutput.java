package DAC.API_Automation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createOutput {

	public void writeToLog(String filename, String[] logMessage) {
		String filepath="./Evidence/"+filename;
		int rows=logMessage.length;
		System.out.println(rows);
		try {  
			if(createFile(filepath)) {
				FileWriter myWriter = new FileWriter(filepath);
				for(int i=0;i<rows;i++) {
					myWriter.write(logMessage[i]);
					myWriter.write("\n");
				}
				myWriter.write("--------------------------------------------------------");
			    myWriter.write("\n");
			    myWriter.close();
			}
  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	}



private boolean createFile(String filepath) throws IOException {
		
		File myObj = new File(filepath);
		
		try {
		      if (!myObj.exists()) {
		    	myObj.createNewFile();
		        System.out.println("File created: " + myObj.getName());
		        return true;
		      } else {
//		        System.out.println("File already exists.");
		        return true;
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		      return false;
		    }
		  }
	
public String getmethod() {
	
	return Thread.currentThread().getStackTrace()[2].getMethodName();
	}
}

