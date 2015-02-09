package playfairtests

import playfair.Coder
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
    val output = tCoder.decode("Let's Keep thiS oN tHe DOWN LOW!")
    assert(output != null)
    assert(output.isInstanceOf[String])
    assert(output.exists(c => !c.isLower))
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