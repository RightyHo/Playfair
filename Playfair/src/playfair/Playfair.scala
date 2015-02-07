package playfair

import java.io._
import scala.io.Source

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

	def runEncode = {
		val key: String = readLine("Please enter the keyword: ")
				//check that key is a letter
				if(validKey(key)){
					val code = new Coder(key)
					val plainText = readLine("Please enter the name of a file to be encoded") 
					val file = new File(plainText)
					val plain = Source.fromFile(file).toString
					code.encode(plain)
				}
	}

	def runDecode = {
		???
	}

	def validKey(key: String): Boolean = {
		???
	}
}