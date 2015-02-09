package playfairtests

import playfair.Coder
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class CoderTest extends FunSuite with BeforeAndAfter {

  // encode(String) method test suite
  
  test("encode method accepts mixed case string as input"){
    ???
  }
  
  test("encode method accepts string with spaces as input"){
    ???
  }
  
  test("encode method accepts string with newlines as input"){
    ???
  }
  
  test("encode method accepts string with punctuation marks as input"){
    ???
  }
  
  test("encode method should return a string with only lowercase letters"){
    ???
  }
  
  test("encode returns string with letters in blocks of 5, with a single space between blocks. Last block may contain fewer than 5 characters"){
    ???
  }
  
  test("encode method returns string with ten blocks per line - the last line may have fewer blocks"){
    ???
  }
  
  test("encode method returns string with no whitespace at the beginning or the end, and only a single space or a single newline between blocks"){
	???
  }
  
  test("encode method returns string with no punctuation"){
	???
  }
  
    // decode(String) method test suite
  
  test("decode method should return a string with only lowercase letters"){
    ???
  }
  
  test("decode returns string with letters in blocks of 5, with a single space between blocks. Last block may contain fewer than 5 characters"){
    ???
  }
  
  test("decode method returns string with ten blocks per line - the last line may have fewer blocks"){
    ???
  }
  
  test("decode method returns string with no whitespace at the beginning or the end, and only a single space or a single newline between blocks"){
	???
  }
  
  test("decode method returns string with no punctuation"){
	???
  }
}