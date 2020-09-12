package com.sum.server.frontend.oracle;


import com.sum.server.frontend.EofToken;
import com.sum.server.frontend.Parser;
import com.sum.server.frontend.Scanner;
import com.sum.server.frontend.Token;
import com.sum.server.frontend.TokenType;
import com.sum.server.message.Message;

import static com.sum.server.frontend.oracle.OracleErrorCode.*;
import static com.sum.server.frontend.oracle.OracleTokenType.*;
import static com.sum.server.message.MessageType.*;

/**
 * <h1>OracleParserTD</h1>
 * 
 * <p>
 * The top-down Oracle parser.
 * </p>
 */
public class OracleParserTD extends Parser {
	/**
	 * Constructor.
	 * 
	 * @param scanner
	 *            the scanner to be used with this parser.
	 */
	public OracleParserTD(Scanner scanner) {
		super(scanner);
	}

	protected static OracleErrorHandler errorHandler = new OracleErrorHandler();

	/**
	 * Parse a Oracle source program and generate the symbol table and the
	 * intermediate code.
	 */
	public void parse() throws Exception {
		Token token;
		long startTime = System.currentTimeMillis();
		try {
			// Loop over each token until the end of file.
			while (!((token = nextToken()) instanceof EofToken)) {
				TokenType tokenType = token.getType();
				if (tokenType != ERROR) {
					// Format each token.
					sendMessage(new Message(TOKEN, new Object[] {
							token.getLineNumber(), token.getPosition(),
							tokenType, token.getText(), token.getValue() }));
				} else {
					errorHandler.flag(token,
							(OracleErrorCode) token.getValue(), this);
				}
			}
			// Send the parser summary message.
			float elapsedTime = (System.currentTimeMillis() - startTime) / 1000f;
			sendMessage(new Message(PARSER_SUMMARY, new Number[] {
					token.getLineNumber(), getErrorCount(), elapsedTime }));
		} catch (java.io.IOException ex) {
			errorHandler.abortTranslation(IO_ERROR, this);
		}
	}

	/**
	 * Return the number of syntax errors found by the parser.
	 * 
	 * @return the error count.
	 */
	public int getErrorCount() {
		return errorHandler.getErrorCount();
	}
}