package com.sum.server.frontend;

/**
 * <h1>EofToken</h1>
 * 
 * <p>
 * The generic end-of-file token.
 * </p>
 */
public class EofToken extends Token {
	/**
	 * Constructor.
	 * 
	 * @param source
	 *            the source from where to fetch subsequent characters.
	 * @throws Exception
	 *             if an error occurred.
	 */
	public EofToken(Source source, TokenType type) throws Exception {
		super(source);
		this.type = type;
	}

	/**
	 * Do nothing. Do not consume any source characters.
	 * 
	 * @param source
	 *            the source from where to fetch the token's characters.
	 * @throws Exception
	 *             if an error occurred.
	 */
	protected void extract(Source source) throws Exception {
	}
}