public class BadGrammarException extends Exception {
	  public BadGrammarException() { super(); }
	  public BadGrammarException(String message) { super(message); }
	  public BadGrammarException(String message, Throwable cause) { super(message, cause); }
	  public BadGrammarException(Throwable cause) { super(cause); }
	  
	  static public void Validate(String token, String expectedToken, String ErrorMessage) throws BadGrammarException
	  {
			if(!token.equals(expectedToken))
			{
				throw new BadGrammarException("ErrorMessage");
			}
	  }
	}