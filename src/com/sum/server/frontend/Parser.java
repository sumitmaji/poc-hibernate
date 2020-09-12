package com.sum.server.frontend;

import com.sum.server.message.Message;
import com.sum.server.message.MessageHandler;
import com.sum.server.message.MessageListener;
import com.sum.server.message.MessageProducer;

/**
 * <h1>Parser</h1>
 * 
 * <p>
 * A language-independent framework class. This abstract parser class will be
 * implemented by language-specific subclasses.
 * </p>
 */
public abstract class Parser implements MessageProducer {
	protected static MessageHandler messageHandler; // message handler delegate
	static {
		messageHandler = new MessageHandler();
	}
	protected Scanner scanner; // scanner used with this parser

	/**
	 * Constructor.
	 * 
	 * @param scanner
	 *            the scanner to be used with this parser.
	 */
	protected Parser(Scanner scanner) {
		this.scanner = scanner;
	}

	/**
	 * Parse a source program and generate the intermediate code and the symbol
	 * table. To be implemented by a language-specific parser subclass.
	 * 
	 * @throws Exception
	 *             if an error occurred.
	 */
	public abstract void parse() throws Exception;

	/**
	 * Return the number of syntax errors found by the parser. To be implemented
	 * by a language-specific parser subclass.
	 * 
	 * @return the error count.
	 */

	public abstract int getErrorCount();

	/**
	 * Call the scanner's currentToken() method.
	 * 
	 * @return the current token.
	 */
	public Token currentToken() {
		return scanner.currentToken();
	}

	/**
	 * Call the scanner's nextToken() method.
	 * 
	 * @return the next token.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public Token nextToken() throws Exception {
		return scanner.nextToken();
	}

	/**
	 * Add a parser message listener.
	 * 
	 * @param listener
	 *            the message listener to add.
	 */
	public void addMessageListener(MessageListener listener) {
		messageHandler.addListener(listener);
	}

	/**
	 * Remove a parser message listener.
	 * 
	 * @param listener
	 *            the message listener to remove.
	 */
	public void removeMessageListener(MessageListener listener) {
		messageHandler.removeListener(listener);
	}

	/**
	 * Notify listeners after setting the message.
	 * 
	 * @param message
	 *            the message to set.
	 */
	public void sendMessage(Message message) {
		messageHandler.sendMessage(message);
	}
}