package edu.psgv.sweng861;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitConvertorTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	//Checking if a integer given as length in mm is properly being converted to mil
	void testToMillWhenInputIsmm() {
		assertEquals( 787.402, UnitsConvertor.toMil(20, "mm"));
		assertEquals( 787.402, UnitsConvertor.toMil(20, "millimeter"));
		assertEquals( -787.402, UnitsConvertor.toMil(-20, "mm"));
	}
	
	@Test
	//Checking if a integer given as length in cm is properly being converted to mil
	void testToMillWhenInputIscm() {
		assertEquals( 7874.02, UnitsConvertor.toMil(20, "cm"));
		assertEquals( 7874.02, UnitsConvertor.toMil(20, "centimeter"));
		assertEquals( -7874.02, UnitsConvertor.toMil(-20, "cm"));
	}
	
	@Test
	//Checking if a integer given as length in meters is properly being converted to mil
	void testToMillWhenInputIsm() {
		assertEquals( 787402, UnitsConvertor.toMil(20, "m"));
		assertEquals( 787402, UnitsConvertor.toMil(20, "meter"));
		assertEquals( -787402, UnitsConvertor.toMil(-20, "m"));
	}
	
	//Checking if a integer given as length in km is properly being converted to mil
	@Test
	void testToMillWhenInputIskm() {
		assertEquals( 7.87402e+8, UnitsConvertor.toMil(20, "km"));
		assertEquals( 7.87402e+8, UnitsConvertor.toMil(20, "kilometer"));
		assertEquals( -7.87402e+8, UnitsConvertor.toMil(-20, "km"));
	}
	
	@Test
	//Checking if a bad string passed as metric would give IllegalArgumentException
	void testToMillIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {
				UnitsConvertor.toMil(20, "pm");
		});
	}
	
	@Test
	//Checking if a integer given as length in mil is properly being converted to mm
	void testToMmWhenInputIsMil() {
		assertEquals( 0.762, UnitsConvertor.toMm(30, "mil"));
		assertEquals( -0.762, UnitsConvertor.toMm(-30, "mil"));
	}
	
	
	@Test
	//Checking if a integer given as length in inch is properly being converted to mm
	void testToMmWhenInputIsInch() {
		assertEquals( 762, UnitsConvertor.toMm(30, "in"));
		assertEquals( 762, UnitsConvertor.toMm(30, "inch"));
		assertEquals( -762, UnitsConvertor.toMm(-30, "inch"));
	}
	
	@Test
	//Checking if a integer given as length in ft is properly being converted to mm
	void testToMmWhenInputIsFt() {
		assertEquals( 9144, UnitsConvertor.toMm(30, "ft"));
		assertEquals( 9144, UnitsConvertor.toMm(30, "feet"));
		assertEquals( 9144, UnitsConvertor.toMm(30, "foot"));
		assertEquals( -9144, UnitsConvertor.toMm(-30, "ft"));
	}
	
	@Test
	//Checking if a integer given as length in yard is properly being converted to mm
	void testToMmWhenInputIsYard() {
		assertEquals( 27432, UnitsConvertor.toMm(30, "yd"));
		assertEquals( 27432, UnitsConvertor.toMm(30, "yard"));
		assertEquals( -27432, UnitsConvertor.toMm(-30, "yard"));
	}
	
	@Test
	//Checking if a integer given as length in mile is properly being converted to mm
	void testToMmWhenInputIsMile() {
		assertEquals( 4.828032e+7, UnitsConvertor.toMm(30, "mi"));
		assertEquals( 4.828032e+7, UnitsConvertor.toMm(30, "mile"));
		assertEquals( -4.828032e+7, UnitsConvertor.toMm(-30, "mile"));
	}
		
	@Test
	//Checking if a bad string passed as imperial would give IllegalArgumentException
	void testToMmIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> {
				UnitsConvertor.toMm(20, "pmx");
		});
	}
	
	// This function splits strings at "\n" and prints them in new lines one after the other in the same order.
	public String normalizeExpectedOutput(String expectedOutput) {
	String normExpectedOutput;
	String [] outputs = expectedOutput.split("\n");
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	for (String str: outputs) {
	pw.println(str);
	}
	pw.close();
	normExpectedOutput = sw.toString();
	return normExpectedOutput;
	}
	
	@Test
	//Testing main function with input in mm to check conversions
	public void mainTestToMill() {
	
	String input = "20 mm\n";
	InputStream sysIn = System.in;
	PrintStream sysOut = System.out;
	InputStream myIn = new ByteArrayInputStream(input.getBytes());
	System.setIn(myIn);
	
	final String unNormalizedExpectedOutput =
			"Please Enter the input value followed by the unit:\n" +
			"20 mm is:\n" +
			"7.9e+02 mil\n" +
			"0.79inch\n" +
			"0.066ft\n" +
			"0.022yard\n" +
			"1.2e-05mile\n" ;
	
	final String expectedOutput =
	normalizeExpectedOutput(unNormalizedExpectedOutput);
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
	System.setOut(new PrintStream(myOut));;
	UnitsConvertor.main(null);
	final String printResult = myOut.toString();
	assertEquals(expectedOutput, printResult);
	System.setOut(sysOut);
	System.setIn(sysIn);
	}
	
	@Test
	//Testing main function with input in inch to check conversions
	public void mainTestToMm() {
	
	String input = "20 inch\n";
	InputStream sysIn = System.in;
	PrintStream sysOut = System.out;
	InputStream myIn = new ByteArrayInputStream(input.getBytes());
	System.setIn(myIn);
	
	final String unNormalizedExpectedOutput =
			"Please Enter the input value followed by the unit:\n" +
			"20 inch is:\n" +
			"508.000000 mm\n" +
			"50.800000 cm\n" +
			"0.508000 m\n" +
			"0.000508km\n" ;
	
	final String expectedOutput =
	normalizeExpectedOutput(unNormalizedExpectedOutput);
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
	System.setOut(new PrintStream(myOut));;
	UnitsConvertor.main(null);
	final String printResult = myOut.toString();
	assertEquals(expectedOutput, printResult);
	System.setOut(sysOut);
	System.setIn(sysIn);
	}
	
	@Test
	//Testing how system would respond to unknown units or units entered in wrong syntax
	public void mainTestBadInput() {
	
	String input = "20 LightYears\n";
	InputStream sysIn = System.in;
	PrintStream sysOut = System.out;
	InputStream myIn = new ByteArrayInputStream(input.getBytes());
	System.setIn(myIn);
	
	final String unNormalizedExpectedOutput =
			"Please Enter the input value followed by the unit:\n" +
			"Invalid input\n" ;
	
	final String expectedOutput =
	normalizeExpectedOutput(unNormalizedExpectedOutput);
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
	System.setOut(new PrintStream(myOut));;
	UnitsConvertor.main(null);
	final String printResult = myOut.toString();
	assertEquals(expectedOutput, printResult);
	System.setOut(sysOut);
	System.setIn(sysIn);
	}
	
	@Test
	//Testing how system would respond to unknown units or units entered in wrong syntax
	public void mainTestInvalidInput() {
	
	String input = "20 mm km\n";
	InputStream sysIn = System.in;
	PrintStream sysOut = System.out;
	InputStream myIn = new ByteArrayInputStream(input.getBytes());
	System.setIn(myIn);
	
	final String unNormalizedExpectedOutput =
			"Please Enter the input value followed by the unit:\n" +
					"Invalid input\n" ;
	
	final String expectedOutput =
	normalizeExpectedOutput(unNormalizedExpectedOutput);
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
	System.setOut(new PrintStream(myOut));;
	UnitsConvertor.main(null);
	final String printResult = myOut.toString();
	assertEquals(expectedOutput, printResult);
	System.setOut(sysOut);
	System.setIn(sysIn);
	}
	
	@Test
	//To test if it gives invalid input when input doesn't have a long to start with
	public void mainTestInvalidInputValueAfterUnit() {
	
	String input = "mil 10\n";
	InputStream sysIn = System.in;
	PrintStream sysOut = System.out;
	InputStream myIn = new ByteArrayInputStream(input.getBytes());
	System.setIn(myIn);
	
	final String unNormalizedExpectedOutput =
			"Please Enter the input value followed by the unit:\n" +
			"Invalid input\n" ;
	
	final String expectedOutput =
	normalizeExpectedOutput(unNormalizedExpectedOutput);
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
	System.setOut(new PrintStream(myOut));;
	UnitsConvertor.main(null);
	final String printResult = myOut.toString();
	assertEquals(expectedOutput, printResult);
	System.setOut(sysOut);
	System.setIn(sysIn);
	}
	
	
	@Test
	// To test how system would respond to zero lengths
	public void mainTestZeroInput() {
	String input = "0 km\n";
	InputStream sysIn = System.in;
	PrintStream sysOut = System.out;
	InputStream myIn = new ByteArrayInputStream(input.getBytes());
	System.setIn(myIn);
	
	final String unNormalizedExpectedOutput =
			"Please Enter the input value followed by the unit:\n" +
			"0 km is:\n" +
			"0.0 mil\n" +
			"0.0inch\n" +
			"0.0ft\n" +
			"0.0yard\n" +
			"0.0mile\n" ;
	
	final String expectedOutput =
	normalizeExpectedOutput(unNormalizedExpectedOutput);
	final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
	System.setOut(new PrintStream(myOut));;
	UnitsConvertor.main(null);
	final String printResult = myOut.toString();
	assertEquals(expectedOutput, printResult);
	System.setOut(sysOut);
	System.setIn(sysIn);
	}
	

}
