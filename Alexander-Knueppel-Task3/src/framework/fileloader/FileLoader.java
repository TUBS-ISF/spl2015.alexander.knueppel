package framework.fileloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;

import framework.DataSet;

public interface FileLoader {
	public DataSet loadFile(File file) throws FileNotFoundException ;
	public DataSet loadFile(Reader input);
}
