package playfair

class Coder(val keyword: String) {
  
  private val NumRows = 5
  private val NumCols = 5
  
  private val cipherTable = buildCodeBlock()
 
  private def buildCodeBlock(): Array[Array[Char]] = {
  	var codeBlock = new Array[Array[Char]](NumRows)   
  	
    // concatenate the keyword with the alphabet
    val tableElements: List[Char] = keyword.toLowerCase().toList ++ List('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')

    // replace all instances of the character j with i
    def replaceJ(ch: Char):Char = {
      if(ch == 'j') return 'i'
      else return ch
    }  
  	
    val replaceJwithI = for(element <- tableElements) yield replaceJ(element)
  	
    // remove any repeated characters in the list
    val distinctElements = replaceJwithI.distinct
    
    //build the row elements to populate the block from distinctElements list
    def getRowArrays(): Array[String] = {
      var result: Array[String] = new Array[String](NumRows)
      for(r <- 0 until NumRows){
        result(r) = distinctElements(r*NumCols+0).toString ++ distinctElements(r*NumCols+1).toString ++ distinctElements(r*NumCols+2).toString ++ 
        			distinctElements(r*NumCols+3).toString ++ distinctElements(r*NumCols+4).toString
      }
      return result
    }
    
    val rowElements = getRowArrays
        
    //initialise a 2D array
    for(r <- 0 until NumRows){
      codeBlock(r) = new Array[Char](NumCols)
    }
  	//populate the array
  	for(r <- 0 until NumRows){
  	  codeBlock(r) = rowElements(r).toCharArray()
  	}
    return codeBlock 
  }
    


  def encode(plainText: String): String = {
    //clean up plainText string
    val lowCasePT: String = plainText.toLowerCase()
    val PTchars: Array[Char] = lowCasePT.toCharArray()
    val onlyLetters: Array[Char] = PTchars.filter(_.isLetter)
    
    // put an 'x' between double pairs or a 'q' between a double 'xx' 
    val noDoublePairs: List[Char] = {
      val chList: List[Char] = onlyLetters.toList
      for(ch <- 0 until chList.size){
        if(ch)
      }
    }
    
    //map input char array to an output char array using the cipherTable to encode

}
    
    
    return ???
  }
  
  
  
  
  def decode(secretTest: String): String = ???
		 
}