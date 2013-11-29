package edu.allegheny.TweetAnalyze.test;

import 	static org.junit.Assert.fail;
import 	static org.junit.Assert.*;

import 	edu.allegheny.TweetAnalyze.Parser.*;

import	net.lingala.zip4j.core.ZipFile;
import	net.lingala.zip4j.exception.ZipException;
import	java.io.File;

import 	org.junit.*;
import 	org.junit.rules.ExpectedException;
import 	org.junit.runner.RunWith;
import 	org.junit.runners.JUnit4;

/**
 * Tests for {@link edu.allegheny.TweetAnalyze.Parser}
 * 
 * @author	Hawk Weisman
 * @version	0.1
 * @see		edu.allegheny.TweetAnalyze.Parser.CSVParser
 * @see		edu.allegheny.TweetAnalyze.Parser.ZipParser
 */

@RunWith(JUnit4.class)
public class TestParser {

	private String		testTweetPath;
  	private ZipFile 	target;
  	private File 		temp = new File("TweetAnalyzeTemp/");
  	private ZipParser	parserUnderTest = new ZipParser();
	
	@Rule
  	public ExpectedException exception = ExpectedException.none();

  	@Before
  	public void setUp () throws ZipException {
  		// get the relative path to the target
  		testTweetPath = new File("").getAbsolutePath();
  		testTweetPath = testTweetPath + "/src/edu/allegheny/TweetAnalyze/test/tweets.zip";
  		target = new ZipFile (new File(testTweetPath));
  	}

  	/**
  	 * Generic test to insure correct instantiation of parser classes.
  	 * If this fails, you're definitely not going to space today.
  	 */
  	@Test
  	public void testParserInstantiation() {
  		org.junit.Assert.assertNotNull("FAIL: new ZipParser was not initialized", new ZipParser());
  		org.junit.Assert.assertNotNull("FAIL: new CSVParser was not initialized", new CSVParser());
  	}

  	/**
  	 * Tests the ZipParser extracting CSV files from the tweets zip
  	 */
  	@Test
  	public void testExtraction () throws ZipException {

  		// assert that a tweets.csv file exists in the temp directory
  		parserUnderTest.extractTweetsCSV(target,temp);
  		org.junit.Assert.assertTrue(temp.exists());
  	}

  	/**
  	 * Test ZipParsing
  	 */
  	@Test 
  	public void testZipParsing () {
  		org.junit.Assert.assertNotNull("FAIL: zip parser returned a null ArrayList<Tweet>",  parserUnderTest.parse(new File (testTweetPath)));
  	}

  	@After
  	public void tearDown() {

  		// delete the temp file
  		File temp = new File("TweetAnalyzeTemp/");
  		temp.delete();
  	}

}
