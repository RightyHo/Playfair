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
    
    // put an 'x' between double pairs or a 'q' between a double 'xx'.  If a final letter is needed to complete a pair, use ’z’.
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
    		    return letters(0) :: removeDoublePairs(letters.drop(1))
    		  }
    	  }
      }
    }
 
    val noDoublePairs: List[Char] = removeDoublePairs(letterList)
    val digraphs = noDoublePairs.grouped(2).toList
    
    // find co-ordinates of Character in cipherTable: Array[Array[Char]]
    def getCoordinates(c: Char): Tuple2[Int,Int] = {
      var row: Int = -1
      var col: Int = -1
      for(i <- 0 until NumRows)
        if(cipherTable(i).contains(c))
          row = i
      for(j <- 0 until NumCols)
        if(cipherTable(row)(j) == c)
          col = j
        return Tuple2(row,col)
    }
     
    for(pair <- digraphs){
      val char1Coords = getCoordinates(pair(0))
      val char2Coords = getCoordinates(pair(1))
      
      // get co-ordinates for the encoded char that the input char maps to
      
      // If the letters appear on the same row of your table, replace them with the letters to their immediate 
      // right respectively (wrapping around to the left side of the row if a letter in the original pair was on the right side of the row).
      
      // If the letters appear on the same column of your table, replace them with the letters immediately 
      // below respectively (wrapping around to the top side of the column if a letter in the original pair was on the bottom side of the column).
      
      // If the letters are not on the same row or column, replace them with the letters on the same row respectively but at the other pair of 
      // corners of the rectangle defined by the original pair. The order is important – the first letter of the encrypted pair is the one that 
      // lies on the same row as the first letter of the plaintext pair.
    }
    
    
    return ???
  }

    

  
  
  
  
  def decode(secretTest: String): String = ???
		 
}