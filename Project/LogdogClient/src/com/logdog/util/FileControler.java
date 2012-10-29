package com.logdog.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import android.os.Environment;
import android.util.Log;

public class FileControler {

	public FileControler() {
		// TODO Auto-generated constructor stub
	}

	private File GetExternalStorageFile(String Directory, String FileName){
		File Exdirectory = Environment.getExternalStorageDirectory();
	    String State = Environment.getExternalStorageState();
        
        File file = null;
        
        File dir = new File(Exdirectory+"/"+Directory);
        
        if(!dir.exists())
        	dir.mkdir();
        
        if(State.equals("mounted") && Exdirectory != null)
            file = new File(Exdirectory+"/"+Directory, FileName);
        
        if(file == null)
            Log.e("LOGDOG", "ExternalStroage Access Fail");
        
        return file;
	}
	public String SaveStringtoFile(String Content, String Directory, String FileName){
		try{
	        File file = GetExternalStorageFile(Directory,FileName);
	        
	        if(file != null)
	        {
	            if(file.exists())
	            	file.delete();
	            
	            	file.createNewFile();
	            	FileOutputStream fileOutputStream = new FileOutputStream(file);
	              	PrintWriter writer = new PrintWriter(fileOutputStream);
	            	writer.print(Content);
	            	writer.close();
	           }
		}
		catch(Exception e){
			e.printStackTrace();
			Log.e("LOGDOG",e.getMessage());
		}
		return FileName;
	}
	
	
	public String FiletoString(String Directory, String FileName){
		StringBuilder Content = new StringBuilder();
		Content.append("");
		
		try{
	        File file = GetExternalStorageFile(Directory,FileName);
	        
	        if(file != null)
	        {
	            if(file.exists())
	            {
	            	char[] c = new char[(int)file.length()];
	            	BufferedReader br = new BufferedReader(new FileReader(file));
	            	br.read(c);
	            	Content.append(c);
	            }
	        }
		}
		catch(Exception e){
			e.printStackTrace();
			Log.e("LOGDOG",e.getMessage());
		}
		
		return Content.toString();
	}
	
	public boolean DeleteFile(String Directory,String FileName){
		boolean Success = false;
		try{
			File file = GetExternalStorageFile(Directory,FileName); 
			
			if(file.exists()){
				file.delete();
				Success = true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			Log.e("LOGDOG",e.getMessage());
		}
		
		return Success;
	}
	
}
