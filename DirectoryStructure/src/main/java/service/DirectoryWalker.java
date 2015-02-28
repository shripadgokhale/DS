package service;

import java.io.File;
import java.util.ArrayList;

import model.DirInfo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
public class DirectoryWalker {

	
	private ArrayList <DirInfo> filesList = new ArrayList<DirInfo>();
	
	ArrayList <DirInfo> getFilesList() {
		return filesList;
	}

	public void resetFilesList() {
		filesList = new ArrayList<DirInfo>();
	}

	@Cacheable(value = { "DirectoryStructure" } , key="#path")
	public DirInfo walk( String path ) {

	    File root = new File( path );
	    System.out.println( "Dir:" + root.getAbsoluteFile() );
	    File[] list = root.listFiles();
	    DirInfo fileInfo = new DirInfo(System.currentTimeMillis(), root);
	    if (list == null) return fileInfo;

	    for ( File f : list ) {
	        if ( f.isDirectory() ) {
	            DirInfo dirInfo = walk( f.getAbsolutePath() );
	            fileInfo.addToFileList(dirInfo);
	            System.out.println( "Dir:" + f.getAbsoluteFile() );
	        }
	        else {
	        	fileInfo.addToFileList(new DirInfo(System.currentTimeMillis(), f));
	            System.out.println( "File:" + f.getAbsoluteFile() );
	        }
	    }
	    return fileInfo;
	    
	}
	
	@CacheEvict(value = { "DirectoryStructure" } , key="#path")
	public void clear(String path) {
		
		
	}
	
	
}
