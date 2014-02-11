package myUtility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.util.vector.Vector3f;

public class OBJLoader {
	public static Model loadModel(File f) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(f));
		
		Model m = new Model();
		
		String line;
		while((line = reader.readLine()) != null){
			if (line.startsWith("v ")){
				float x = Float.valueOf(line.split(" ")[1]);
				float y = Float.valueOf(line.split(" ")[1]);
				float z = Float.valueOf(line.split(" ")[1]);
				m.vertices.add(new Vector3f(x,y,z));
			}
			
		}
		
		return m;

	}

}
