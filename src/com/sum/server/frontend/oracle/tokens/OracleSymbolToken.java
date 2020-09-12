package com.sum.server.frontend.oracle.tokens;

import com.sum.server.frontend.Source;
import com.sum.server.frontend.oracle.OracleToken;

import static com.sum.server.frontend.oracle.OracleErrorCode.INVALID_CHARACTER;
import static com.sum.server.frontend.oracle.OracleTokenType.ERROR;
import static com.sum.server.frontend.oracle.OracleTokenType.SPECIAL_SYMBOLS;

public class OracleSymbolToken extends OracleToken {

	public OracleSymbolToken(Source source) throws Exception {
		super(source);
	}

	public void extract() throws Exception {
		char currentChar = currentChar();
		text = Character.toString(currentChar);
		type = null;
		switch (currentChar) {
		// Single-character special symbols.
		case '+':
		case '-':
		case '*':
		case '/':
		case ',':
		case ';':
		case '\'':
		case '=':
		case '(':
		case ')':
		case '[':
		case ']':
		case '{':
		case '}':
		case '^': {
			nextChar(); // consume character
			break;
		}
			// : or :=
		case ':': {
			currentChar = nextChar(); // consume ':';
			if (currentChar == '=') {
				text += currentChar;
				nextChar(); // consume '='
			}
			break;
		}
			// < or <= or <>
		case '<': {
			currentChar = nextChar(); // consume '<';
			if (currentChar == '=') {
				text += currentChar;
				nextChar(); // consume '='
			} else if (currentChar == '>') {
				text += currentChar;
				nextChar(); // consume '>'
			}
			break;
		}
			// > or >=
		case '>': {
			currentChar = nextChar(); // consume '>';
			if (currentChar == '=') {
				text += currentChar;
				nextChar(); // consume '='
			}
			break;
		}
			// . or ..
		case '.': {
			currentChar = nextChar(); // consume '.';
			if (currentChar == '.') {
				text += currentChar;
				nextChar(); // consume '.'
			}
			break;
		}
		default: {
			nextChar(); // consume bad character
			type = ERROR;
			value = INVALID_CHARACTER;
		}
		}
		// Set the type if it wasn't an error.
		if (type == null) {
			type = SPECIAL_SYMBOLS.get(text);
		}
	}
}
