package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public final class  DirInfo extends FileInfo {

	/**
	 * 
	 */
	private List <DirInfo> fileList;
	private long lastFetchedfromDisk;
	private String permission = "";
	
	
	
	
	public String getFilesStr() {
		return filesStr;
	}

	public void setFilesStr(String filesStr) {
		this.filesStr = filesStr;
	}

	public DirInfo(long lastFetchedfromDisk, File file) {
		super(file);
		this.lastFetchedfromDisk = lastFetchedfromDisk;
		this.file = file;
		fileList = new ArrayList <DirInfo> ();
		
		if (file.canRead())
			permission = permission+" " +"readable";
		if ( file.canWrite())
			permission = permission+" " +"writable";
		if ( file.canExecute())
			permission = permission+" " +"executable";
			
	}

	@JsonIgnore
	public long getLastFetchedfromDisk() {
		return lastFetchedfromDisk;
	}

	public void setLastFetchedfromDisk(long lastFetchedfromDisk) {
		this.lastFetchedfromDisk = lastFetchedfromDisk;
	}

	
	public void setFileList( ArrayList <DirInfo>fileList) {
		this.fileList = fileList;
	}

	public  List  <DirInfo> getFileList(){
		return fileList;
	}
	
	public void addToFileList( DirInfo fileInfo){
		fileList.add(fileInfo);
		//filesStr = filesStr + "<tr><td> Path : " + fileInfo.getFile().getAbsolutePath() + " Type : " + fileInfo.getFileType() + " Size : " + fileInfo.getSize() + " Permission :" + fileInfo.getPermission();
		filesStr = filesStr + "<tr><td> " + fileInfo.getFile().getAbsolutePath() + "</td>  <td>  "
		+ fileInfo.getFileType() + "</td>  <td> " + fileInfo.getSize() + " </td>  <td>" + fileInfo.getPermission() + "</td>  </tr>";
		if( fileInfo.getFile().isDirectory() && !fileInfo.getFilesStr().equals(""))
			filesStr = filesStr +  fileInfo.getFilesStr();
	}



	public String getPermission() {
		return permission;
	}

	
}
