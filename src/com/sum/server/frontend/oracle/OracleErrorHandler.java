package com.sum.server.frontend.oracle;

import static com.sum.server.frontend.oracle.OracleErrorCode.*;
import static com.sum.server.frontend.oracle.OracleTokenType.*;
import static com.sum.server.message.MessageType.SYNTAX_ERROR;

import com.sum.server.frontend.Parser;
import com.sum.server.frontend.Token;
import com.sum.server.message.Message;

/**
 * 
 * @author pramit
 *Error handler Oracle syntax errors.
 */
public class OracleErrorHandler {

	private static final int MAX_ERRORS = 125;
	private static int errorCount = 0; //count of syntax errors
	
	/**
	 * flag an error in the source line
	 */
	public void flag(Token token, OracleErrorCode errorCode, Parser parser){
		//Notify the parsers listers
		parser.sendMessage(new Message(SYNTAX_ERROR,new Object[]{token.getLineNumber(),
				token.getPosition(), token.getText(), errorCode.toString()}));
		
		if(++errorCount > MAX_ERRORS)
			abortTranslation(TOO_MANY_ERRORS,parser);
	}
	
	/**
	 * Abort the translation
	 */
	public void abortTranslation(OracleErrorCode errorCode, Parser parser){
		//Notify the parsers listener and then abort
		String fatalText = "FATAL ERROR: "+errorCode.toString();
		parser.sendMessage(new Message(SYNTAX_ERROR, new Object[]{0,0,"",fatalText}));
		System.exit(errorCode.getStatus());
	}

	public static int getErrorCount() {
		return errorCount;
	}
}
