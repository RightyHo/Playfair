package playfairtests

import playfair.Playfair
import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter

class PlayfairTest extends FunSuite with BeforeAndAfter {

  test("validKey method recognises a valid key"){
    assert(Playfair.validKey("Andrew"))
  }
  
  test("validKey method recognises a null key"){
    assert(!Playfair.validKey(null))
  }
  
  test("validKey method recognises a key containing non letter characters"){
    assert(!Playfair.validKey("MichaelJordan23"))
  }
  
    test("validKey method recognises an empty string key"){
    assert(!Playfair.validKey(""))
  }
}