package com.sum.server.frontend.oracle.tokens;

import com.sum.server.frontend.Source;
import com.sum.server.frontend.oracle.OracleToken;

import static com.sum.server.frontend.Source.EOF;
import static com.sum.server.frontend.oracle.OracleErrorCode.UNEXPECTED_EOF;
import static com.sum.server.frontend.oracle.OracleTokenType.ERROR;
import static com.sum.server.frontend.oracle.OracleTokenType.STRING;

public class OracleStringToken extends OracleToken {

	public OracleStringToken(Source source) throws Exception {
		super(source);
	}

	protected void extract() throws Exception {
		StringBuilder textBuffer = new StringBuilder();
		StringBuilder valueBuffer = new StringBuilder();
		char currentChar = nextChar(); // consume initial quote
		textBuffer.append('\'');
		// Get string characters.
		do {
			// Replace any whitespace character with a blank.
			if (Character.isWhitespace(currentChar)) {
				currentChar = ' ';
			}
			if ((currentChar != '\'') && (currentChar != EOF)) {
				textBuffer.append(currentChar);
				valueBuffer.append(currentChar);
				currentChar = nextChar(); // consume character
			}
			// Quote? Each pair of adjacent quotes represents a single-quote.
			if (currentChar == '\'') {
				while ((currentChar == '\'') && (peekChar() == '\'')) {
					textBuffer.append("''");
					valueBuffer.append(currentChar); // append single-quote
					currentChar = nextChar(); // consume pair of quotes
					currentChar = nextChar();
				}
			}
		} while ((currentChar != '\'') && (currentChar != EOF));
		if (currentChar == '\'') {
			nextChar(); // consume final quote
			textBuffer.append('\'');
			type = STRING;
			value = valueBuffer.toString();
		} else {
			type = ERROR;
			value = UNEXPECTED_EOF;
		}
		text = textBuffer.toString();
	}
}
