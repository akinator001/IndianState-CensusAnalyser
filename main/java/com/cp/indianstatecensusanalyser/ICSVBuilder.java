package com.cp.indianstatecensusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	public<E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CensusException;
	public<E> int getCount(Iterator<E> iterator);
}
