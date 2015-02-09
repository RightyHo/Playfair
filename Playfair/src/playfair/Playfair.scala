package playfair

import java.io._
import scala.io.Source

/*
 * @author Andrew Ho
 * Main method for the project.  Handles the user interface.
 * Scala features yet to be incorporated in my code:
 * - map
 * - foldLeft (or /:)
 * - foldRight (or :\)
 * - yield
 * - Some
 * - Array
 * - for
 * - List
 */
object Playfair {
	def main(args: Array[String]) = {
		var readyToQuit = false
		println("Welcome to the Playfair Cipher! \n")
		do {
			var choice: String = readLine("Would you like to encode, decode or quit?").toLowerCase()
			choice match {
				case "encode" => runEncode
				case "decode" => runDecode
				case "quit" => {
								println("Good Bye!")
								readyToQuit = true
								}
				case _ => println("Sorry, I didn't understand that command, please try again...")
					}
		} while(!readyToQuit)
	}

	/*
	 * Helper method to make the main method easier to read.  
	 * Requests key word and file name.
	 * Calls encode method with the user supplied input parameter  
	 */
	def runEncode = {
		val key: String = readLine("Please enter the key word: ")
				if(validKey(key)){
					val code = new Coder(key)
					val plainText = readLine("Please enter the name of a file to be encoded") 
					try{
						val file = new File(plainText)
						val plain = Source.fromFile(file).toString
						println("The encoded version of your file is: \n")
						println(code.encode(plain))
					} catch {
					  	case ex: FileNotFoundException => println("Missing file exception")
					  	case ex: IOException => println("I/O exception")
					}
				} else {
				  println("The key word you entered is invalid!  It must be comprised of only letters.")
				}
	}
	
	/*
	 * Helper method to make the main method easier to read.  
	 * Requests key word and file name.
	 * Calls decode method with the user supplied input parameter   
	 */
	def runDecode = {
		val key: String = readLine("Please enter the key word: ")
				if(validKey(key)){
					val code = new Coder(key)
					val codedText = readLine("Please enter the name of a file to be decoded") 
					try{
						val file = new File(codedText)
						val jibberish = Source.fromFile(file).toString
						println("The decoded version of your file is: \n")
						println(code.decode(jibberish))
					} catch {
					  	case ex: FileNotFoundException => println("Missing file exception")
					  	case ex: IOException => println("I/O exception")
					}
				} else {
				  println("The key word you entered is invalid!  It must be comprised of only letters.")
				}
	}

	/*
	 * Checks that the key provided is a string containing only letters
	 * @param key the string to be tested
	 */
	def validKey(key: String): Boolean = {
		if(key == null) return false
		else if(key.length() == 0) return false
		else {
		val existsNonLetter = key.filter(c => !c.isLetter)
		existsNonLetter.length() == 0
		}
	}
}