import static org.junit.Assert.*;
import org.junit.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class JUnitTest {

	final String newLine = System.getProperty("line.separator");
	String pathPrefix = null;
	
	public JUnitTest () {
		Path path = FileSystems.getDefault().getPath("./test/files/").toAbsolutePath();		
		pathPrefix = path.toString();
	}
	
	private static String normalizeLineEnds(String s) {
	    return s.replace("\r\n", "\n").replace('\r', '\n');
	}
	
	private void checkEquality(String testFileName) {		
		try {
			//	
			// Define the input and the output files.
			//
			File inputFile  = new File (pathPrefix + File.separator + testFileName + ".in.txt");
			File outputFile = new File (pathPrefix + File.separator + testFileName + ".out.txt");
			//
			// Read the expected output
			//
			String expectedResult = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
			//
			// Define the input and output streams
			// 
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileInputStream in = new FileInputStream(inputFile);		
			PrintStream out = new PrintStream(baos);			
			//
			// Perform the test
			//		
			Main.read_and_solve(in, out);
			//
			// Flush the output and obtain the data
			//
			out.flush();
			out.close();
			String result = baos.toString();
			//
			// Now check for the actual assertion
			//
			assertEquals(normalizeLineEnds(expectedResult), normalizeLineEnds(result));			
		} catch (IOException e) {			
			fail(e.getMessage());
			
		}		
	}
	
	@Test
	public void SimpleMST() {
		checkEquality("test01");
	}
	
	@Test
	public void SmallMST() {
		checkEquality("test02");
	}
	
	@Test
	public void LargeMST() {
		checkEquality("test03");
	}

}







