package model;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class FileInfo {

	File file;
	double size;
	String fileType;
	String filesStr="";
	
	@JsonIgnore
	public String getFilesStr() {
		return filesStr;
	}

	public FileInfo(File file) {
		super();
		this.file = file;
		size = file.length() ;
		
		if ( file.isFile())
			fileType = "File";
		if ( file.isDirectory())
			fileType = "Directory";
		
	
	}
	
	public void initializeFilesStr(){
		filesStr = "<tr><td> " + file.getAbsolutePath() + "</td>  <td>  "
				+ fileType + "</td>  <td> " + size + "  </tr>";
		
	}
	
	

	public File getFile() {
		return file;
	}


	public double getSize() {
		return size;
	}

	public String getFileType() {
		return fileType;
	}
	
}
