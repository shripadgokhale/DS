package service;

import java.io.File;

import model.DirInfo;
import model.FileInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DirectoryService {

	
	@Autowired
	DirectoryWalker walker;	

	public FileInfo getList( String path) {
		File file = new File(path);
		if(!file.exists() )
			return null;
		
		if( file.isFile()){
			FileInfo fileInfo = new FileInfo(file);
			fileInfo.initializeFilesStr();
			return fileInfo;
		}
		
        walker.resetFilesList();

		
        DirInfo  fileInfo = walker.walk(path);
        
        walker.resetFilesList();
		if ( System.currentTimeMillis() - 60000 > fileInfo.getLastFetchedfromDisk() ){
			walker.clear(path);
			fileInfo = walker.walk(path);
		}
		return fileInfo;
	}
	
}
