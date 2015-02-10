package playfairtests

import playfair.Coder
import java.util.Scanner
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import scala.reflect._

class CoderTest extends FunSuite with BeforeAndAfter {

  var tCoder: Coder = _
  
  before {
    tCoder = new Coder("Islington")
  }
  
  // encode(String) method test suite
  
  test("encode method accepts mixed case string as input"){
    val output = tCoder.encode("LetShoPetHiswORKs")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.size != 0)
  }
  
  test("encode method accepts string with spaces as input"){
    val output = tCoder.encode("if this does not work we are in trouble")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.size != 0)
  }
  
  test("encode method accepts string with newlines as input"){
    val output = tCoder.encode("if this does not work we are in trouble \n i mean this thing could run \n over a lot of different lines")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.size != 0)
  }
  
  test("encode method accepts string with punctuation marks as input"){
    val output = tCoder.encode("once this big-test passes i'll be really happy!")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.size != 0)
  }
  
  test("encode method should return a string with only lowercase letters"){
    val output = tCoder.encode("Let's Keep thiS oN tHe DOWN LOW!")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.exists(c => !c.isLower))
  }
  
  test("encode returns string with letters in blocks of 5, with a single space between blocks. Last block may contain fewer than 5 characters"){
    val output = tCoder.encode("I'm going to look for a space in this output believe you me!")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.charAt(0) != ' ')
    assert(output.charAt(1) != ' ')
    assert(output.charAt(2) != ' ')
    assert(output.charAt(3) != ' ')
    assert(output.charAt(4) != ' ')
    assert(output.charAt(5) == ' ')
    assert(output.charAt(6) != ' ')
    assert(output.charAt(11) == ' ')
    assert(output.charAt(17) == ' ')
  }
  
  test("encode method returns string with ten blocks per line - the last line may have fewer blocks"){
    val output = tCoder.encode("oneon twotw three fourf fivef sixsi seven eight ninen tente\neleve twelv thirt fourt fifte sixte seven eight ninet twent\ntweon twetw tweth twfou twfiv")
    assert(output != null)
    assert(output.isInstanceOf[String])
    val fileScanner: Scanner = new Scanner(output)
    while(fileScanner.hasNextLine()){
      var blockCount = 0
      var line: String = fileScanner.nextLine()
      val lineScanner: Scanner = new Scanner(line)
      while(lineScanner.hasNext()){
        val token: String = lineScanner.next()
        blockCount += 1
      }
      lineScanner.close()
      // makes sure that this is not the last line and then checks that there are ten blocks on the line
      if(fileScanner.hasNextLine()) assert(blockCount == 10)
    }
     fileScanner.close()
  }
  
  test("encode method returns string with no whitespace at the beginning or the end, and only a single space or a single newline between blocks"){
	val output = tCoder.encode("The cold hard truth is that I am running out of ideas on what to type in this here test suit.  But soon this TDD stage will be at it's end and I will move on to coding the source!")
	assert(output != null)
    assert(output.isInstanceOf[String])
    //check for whitespace at beginning
    val outputArray: Array[Char] = output.toCharArray()
    val firstChar: Char = outputArray(0)
    assert(!firstChar.isWhitespace)
	//check for whitespace at end
    val lastChar: Char = outputArray(outputArray.length - 1)
	assert(!lastChar.isWhitespace)
	//check for double spaces
	var noTwoSpaces: Boolean = true
	for(i <- 0 to (outputArray.length -2)){
	  if(outputArray(i).isSpaceChar){
	    if(outputArray(i+1).isSpaceChar) noTwoSpaces = false 
	  }
	}
	assert(noTwoSpaces)
	// check for double newline between blocks
	var allValidChars:  Boolean = true
	var noTwoNewLines:  Boolean = true
	for(i <- 0 to (outputArray.length - 2)){
	  if(!(outputArray(i).isLetter || outputArray(i).isSpaceChar)){
	    if(outputArray(i) != '\n'){
	      allValidChars = false
	    } else {
	      if(outputArray(i+1) == '\n') noTwoNewLines = false
	    }
	  }
	}
	assert(allValidChars)
	assert(noTwoNewLines)
  }
  
  test("encode method returns string with no punctuation"){
    val output = tCoder.encode("I'm going to look!  For a sign?  No I don't need such-help.")
	assert(output != null)
    assert(output.isInstanceOf[String])
    var containsOnlyLetters: Boolean = true
    for(c: Char <- output){
      if(!(c.isLetter || c.isSpaceChar)) containsOnlyLetters = false
    }
    assert(containsOnlyLetters)
  }
  
    // decode(String) method test suite
  
  test("decode method should return a string with only lowercase letters"){
    val output = tCoder.decode("Let's Keep thiS oN tHe DOWN LOW!")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.exists(c => !c.isLower))
  }
  
  test("decode returns string with letters in blocks of 5, with a single space between blocks. Last block may contain fewer than 5 characters"){    
    val output = tCoder.decode("I'm going to look for a space in this output believe you me!")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.charAt(0) != ' ')
    assert(output.charAt(1) != ' ')
    assert(output.charAt(2) != ' ')
    assert(output.charAt(3) != ' ')
    assert(output.charAt(4) != ' ')
    assert(output.charAt(5) == ' ')
    assert(output.charAt(6) != ' ')
    assert(output.charAt(11) == ' ')
    assert(output.charAt(17) == ' ')
  }
  
  test("decode method returns string with ten blocks per line - the last line may have fewer blocks"){
    val output = tCoder.decode("oneon twotw three fourf fivef sixsi seven eight ninen tente\neleve twelv thirt fourt fifte sixte seven eight ninet twent\ntweon twetw tweth twfou twfiv")
    assert(output != null)
    assert(output.isInstanceOf[String])
    val fileScanner: Scanner = new Scanner(output)
    while(fileScanner.hasNextLine()){
      var blockCount = 0
      var line: String = fileScanner.nextLine()
      val lineScanner: Scanner = new Scanner(line)
      while(lineScanner.hasNext()){
        val token: String = lineScanner.next()
        blockCount += 1
      }
      lineScanner.close()
      // makes sure that this is not the last line and then checks that there are ten blocks on the line
      if(fileScanner.hasNextLine()) assert(blockCount == 10)
    }
     fileScanner.close()
  }
  
  test("decode method returns string with no whitespace at the beginning or the end, and only a single space or a single newline between blocks"){
	val output = tCoder.decode("The cold hard truth is that I am running out of ideas on what to type in this here test suit.  But soon this TDD stage will be at it's end and I will move on to coding the source!")
	assert(output != null)
    assert(output.isInstanceOf[String])
    //check for whitespace at beginning
    val outputArray: Array[Char] = output.toCharArray()
    val firstChar: Char = outputArray(0)
    assert(!firstChar.isWhitespace)
	//check for whitespace at end
    val lastChar: Char = outputArray(outputArray.length - 1)
	assert(!lastChar.isWhitespace)
	//check for double spaces
	var noTwoSpaces: Boolean = true
	for(i <- 0 to (outputArray.length -2)){
	  if(outputArray(i).isSpaceChar){
	    if(outputArray(i+1).isSpaceChar) noTwoSpaces = false 
	  }
	}
	assert(noTwoSpaces)
	// check for double newline between blocks
	var allValidChars:  Boolean = true
	var noTwoNewLines:  Boolean = true
	for(i <- 0 to (outputArray.length - 2)){
	  if(!(outputArray(i).isLetter || outputArray(i).isSpaceChar)){
	    if(outputArray(i) != '\n'){
	      allValidChars = false
	    } else {
	      if(outputArray(i+1) == '\n') noTwoNewLines = false
	    }
	  }
	}
	assert(allValidChars)
	assert(noTwoNewLines)
  }
  
  test("decode method returns string with no punctuation"){
	val output = tCoder.decode("I'm going to look!  For a sign?  No I don't need such-help.")
	assert(output != null)
    assert(output.isInstanceOf[String])
    var containsOnlyLetters: Boolean = true
    for(c: Char <- output){
      if(!(c.isLetter || c.isSpaceChar)) containsOnlyLetters = false
    }
    assert(containsOnlyLetters)
  }
}