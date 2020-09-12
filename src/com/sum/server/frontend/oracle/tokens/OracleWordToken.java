package com.sum.server.frontend.oracle.tokens;

import com.sum.server.frontend.Source;
import com.sum.server.frontend.oracle.OracleToken;
import com.sum.server.frontend.oracle.OracleTokenType;

import static com.sum.server.frontend.oracle.OracleTokenType.*;
public class OracleWordToken extends OracleToken {

	public OracleWordToken(Source source) throws Exception {
		super(source);
	}
	
	/**
	 * Extract pascal word token from the source
	 */
	public void extract() throws Exception{
		StringBuilder textBuffer = new StringBuilder();
		char currentChar = currentChar();
		
		//Get the word character(letter or digit). The scanner has already determined
		//that the first character is a letter.
		while(Character.isLetterOrDigit(currentChar)){
			textBuffer.append(currentChar);
			currentChar = nextChar();
		}
		
		text = textBuffer.toString();
		
		//Is it a reserved word or identifier?
		type = (RESERVED_WORDS.contains(text.toLowerCase())) ? 
				OracleTokenType.valueOf(text.toUpperCase()) : //reserved word
					IDENTIFIER; //Identifier
	}

}
