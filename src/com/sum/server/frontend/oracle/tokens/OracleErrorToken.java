package com.sum.server.frontend.oracle.tokens;

import com.sum.server.frontend.Source;
import com.sum.server.frontend.oracle.OracleErrorCode;
import com.sum.server.frontend.oracle.OracleToken;

import static com.sum.server.frontend.oracle.OracleTokenType.ERROR;
public class OracleErrorToken extends OracleToken{

	public OracleErrorToken(Source source, OracleErrorCode errorCode, String tokenText) throws Exception{
		super(source);
		this.text = tokenText;
		this.type = ERROR;
		this.value = errorCode;
	}
	
	/**
	 * Do nothing. Do not consume any character.
	 */
	protected void extract() throws Exception{
	}
}
