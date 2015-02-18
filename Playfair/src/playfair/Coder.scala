package playfair

import scala.collection.mutable.ListBuffer

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
    val letterList: List[Char] = onlyLetters.toList
    
    // put an 'x' between double pairs or a 'q' between a double 'xx' 
    def removeDoublePairs(letters: List[Char]): List[Char] = {
      if(letters.size <= 0){
        return List()
      } else {
    	  if(letters.size <= 1){
    	    return letters(0) :: List('z')
    	  } else {
    		  if(letters(0) == letters(1)){
    		    if(letters(0) == 'x'){
    		      return List('x','q') ::: removeDoublePairs(letters.drop(1))
    		    } else {
    		      return List(letters(0),'x') ::: removeDoublePairs(letters.drop(1))
    		    }
    		  } else {
    		    return List(letters(0),letters(1)) ::: removeDoublePairs(letters.drop(2))
    		  }
    	  }
      }
    }
 
    val noDoublePairs: List[Char] = removeDoublePairs(letterList)
    val preprocessedTuples = noDoublePairs.grouped(2).toList

    //map input char tuples to an output list of char tuples using the cipherTable to encode
    def f(c: List[Char]) = {
      //if(codeBlock.contains(c._1)){int xIndex = codeBlock.indexOf(c._1}
    } 
    
    preprocessedTuples.map(x => f(x))
    
    return ???
  }

    

  
  
  
  
  def decode(secretTest: String): String = ???
		 
}