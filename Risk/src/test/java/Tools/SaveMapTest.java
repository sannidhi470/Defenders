package Tools;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveMapTest {
	
	
	Connectivity l_connectivity = new Connectivity();
	
	/**
	 *
	 * This method is used to test whether the map is being saved with correct file path name.
	 *
	 */
	

	@Test
	void saveMapWithCorrectFilePathTest() {
		File f = new File("");
		String absolute = f.getAbsolutePath();
		String l_fileName = "VeryBasic";
        l_fileName = absolute + File.separator + "src/main/resources" + File.separator + l_fileName+".map";
        l_connectivity.setD_FilepathName(l_fileName);
        
        assertEquals(0, SaveMap.saveMap(l_connectivity));
	}
	
	/**
	 *
	 * This method is used to test whether the map is being saved with wrong file path name.
	 *
	 */
	
	@Test
	void saveMapWithWrongFilePathTest() {
		String absolute = "C:";
		String l_fileName = "aa";
        l_fileName = absolute + File.separator + "src\\main\\resources" + File.separator + l_fileName+".map";
        l_connectivity.setD_FilepathName(l_fileName);
        
        assertEquals(1, SaveMap.saveMap(l_connectivity));
	}

}
