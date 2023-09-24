package Utilities;
import java.util.*;

import Models.Continent;
import Models.Country;

import java.io.*;

public class MapLoader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Enter game command\n");
		Scanner l_input = new Scanner(System.in);
		String l_fileName = l_input.nextLine();
		ArrayList<Continent> continentList=new ArrayList<Continent>();
		ArrayList<Country> l_countryList=new ArrayList<Country>();
		//Getting file location of map
       try {	   
            // Create a file object
            File f = new File("");
  
            // Get the absolute path of file f
            String absolute = f.getAbsolutePath();
  
            // Display the file path of the file object
            // and also the file path of absolute file
          //  System.out.println("Absolute  path: "
          //                    + absolute);
            l_fileName = absolute + File.separator + "src\\main\\resources" + 
            		File.separator + l_fileName+".map";
         //   System.out.print(l_fileName);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
       

	}

}
