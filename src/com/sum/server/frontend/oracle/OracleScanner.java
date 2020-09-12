package com.sum.server.frontend.oracle;


import com.sum.server.frontend.EofToken;
import com.sum.server.frontend.Scanner;
import com.sum.server.frontend.Source;
import com.sum.server.frontend.Token;
import com.sum.server.frontend.oracle.tokens.OracleErrorToken;
import com.sum.server.frontend.oracle.tokens.OracleNumberToken;
import com.sum.server.frontend.oracle.tokens.OracleStringToken;
import com.sum.server.frontend.oracle.tokens.OracleSymbolToken;
import com.sum.server.frontend.oracle.tokens.OracleWordToken;


import static com.sum.server.frontend.Source.EOF;
import static com.sum.server.frontend.oracle.OracleErrorCode.*;
import static com.sum.server.frontend.oracle.OracleTokenType.*;

/**
 * <h1>OracleScanner</h1>
 * 
 * <p>
 * The Oracle scanner.
 * </p>
 */
public class OracleScanner extends Scanner {
	/**
	 * Constructor
	 * 
	 * @param source
	 *            the source to be used with this scanner.
	 */
	public OracleScanner(Source source) {
		super(source);
	}

	/**
	 * Extract and return the next Oracle token from the source.
	 * 
	 * @return the next token.
	 * @throws Exception
	 *             if an error occurred.
	 */
	protected Token extractToken() throws Exception {

		skipWhiteSpace();
		Token token;
		char currentChar = currentChar();
		// Construct the next token. The current character determines the
		// token type.
		if (currentChar == EOF) {
			token = new EofToken(source, END_OF_FILE);
		}else if (Character.isDigit(currentChar)) {
			token = new OracleNumberToken(source);
		}
		else if (currentChar == '\'') {
		token = new OracleStringToken(source);
		} 
		else if (Character.isLetter(currentChar)) {
			token = new OracleWordToken(source);
		}else if(OracleTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))){
			token = new OracleSymbolToken(source);
		}
		else {
			token = new OracleErrorToken(source, INVALID_CHARACTER,
					Character.toString(currentChar));
			nextChar(); // consume character
		}
		return token;
	}

	/**
	 * Skip whitespace characters by consuming them. A comment is whitespace.
	 * 
	 * @throws Exception
	 *             if an error occurred.
	 */
	private void skipWhiteSpace() throws Exception {
		char currentChar = currentChar();
		while (Character.isWhitespace(currentChar) || (currentChar == '{')) {
			// Start of a comment?
			if (currentChar == '{') {
				do {
					currentChar = nextChar(); // consume comment characters
				} while ((currentChar != '}') && (currentChar != EOF));
				// Found closing '}'?
				if (currentChar == '}') {
					currentChar = nextChar(); // consume the '}'
				}
			}
			// Not a comment.
			else {
				currentChar = nextChar(); // consume whitespace character
			}
		}
	}
}