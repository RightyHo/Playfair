package playfair

class Coder(val keyword: String) {
  
  private val NumRows = 5
  private val NumCols = 5
  
  private val cipherTable = buildCodeBlock()
 
  private def buildCodeBlock() = {
  	var codeBlock = new Array[Array[Char]](NumRows)   
    // concatenate the keyword with the alphabet
    val tableElements: List[Char] = keyword.toList ++ List('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')
    // replace all instances of the character j with i
    val replaceJwithI = for(element <- tableElements) yield replaceJ(element)
    // remove any repeated characters in the list
    val distinctElements = replaceJwithI.distinct
    
    val rowElements = getRowArrays
    
    def getRowArrays(): Array[char] = {
      var result: Array[Char] = new Array[Char](NumRows)
      for(r <- 0 until NumRows){
        result(r) = distinctElements(r*NumCols+0) + distinctElements(r*NumCols+1) + distinctElements(r*NumCols+2) + distinctElements(r*NumCols+3) + distinctElements(r*NumCols+4)
      }
      return result
    }
    
    //initialise a 2D array
    for(r <- 0 until NumRows){
      codeBlock(r) = new Array[Char](NumCols)
    }
  	//populate the array
  	for(r <- 0 until NumRows){
  	  codeBlock(r) = rowElements
  	}
    
    def replaceJ(ch: Char):Char = {
      if(ch == 'j') return 'i'
      else return ch
    }
    
  }
    


  def encode(plainText: String): String = {
    val lowCasePT: String = plainText.toLowerCase()
    
    
  }
  
  
  
  
  def decode(secretTest: String): String = ???
		 
}