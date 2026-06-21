package com.utils.exception;



public interface NullObjectException {

	@SafeVarargs
	static  void isNullObject( Object ...object ) 
	{
		if ( object == null )
			throw new NullPointerException();
		
		for (int i = 0; i < object.length; i++) {
			if ( object[ i ] == null )
				throw new NullPointerException();
		}
	}
}
