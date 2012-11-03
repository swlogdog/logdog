package com.logdog.common.File;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;

import android.os.Environment;
import android.util.Log;

/**
 * 파일에 대한 전반적인 컨트롤 제공
 * @since 2012. 11. 2.오전 3:18:36
 * TODO
 * @author JeongSeungsu
 */
public class FileControler {

	
	/**
	 * 외부 스토리지에 파일 생성해서 리턴
	 * @since 2012. 11. 2.오전 3:11:40
	 * TODO
	 * @author JeongSeungsu
	 * @param Directory 저장될 디렉토리
	 * @param FileName 저장될 파일
	 * @return 실패시 null값 리턴 성공시 파일인스턴스 리턴
	 */
	static private File CreateExternalStorageFile(String Directory, String FileName){
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
	/**
	 * 폴더 내부에 파일 리스트를 가져옴
	 * @since 2012. 11. 2.오전 3:57:13
	 * TODO
	 * @author JeongSeungsu
	 * @param Directory 디렉토리명
	 * @return 있으면 리스트를 가져오고 아니면 null
	 */
	static public File[] ExternalStorageDirectoryFileList(String Directory){
		File Exdirectory = Environment.getExternalStorageDirectory();
		File[] ListFiles = null;
		try{
			File Dir = new File(Exdirectory + "/" + Directory);
			if(Dir.exists())
				ListFiles =Dir.listFiles();
			else
				return null;
		}
		catch(Exception e){
			e.printStackTrace();
			Log.e("LOGDOG",e.getMessage());
		}
		return ListFiles;
	}
	
	/**
	 * 스트링 데이터를 파일로 저장
	 * @since 2012. 11. 2.오전 3:10:24
	 * TODO
	 * @author JeongSeungsu
	 * @param Content 저장할 스트링
	 * @param Directory 저장 할 폴더 루트는 외부 메모리
	 * @param FileName 저장할 파일 이름
	 * @return 성공시 저장된 파일 이름을 리턴, 실패시 null리턴
	 */
	static public String SaveStringtoFile(String Content, String Directory, String FileName){
		try{
	        File file = CreateExternalStorageFile(Directory,FileName);
	        
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
			return null;
		}
		return FileName;
	}
	
	
	/**
	 * 파일을 스트링으로 변환시켜줌
	 * @since 2012. 11. 2.오전 3:16:55
	 * TODO
	 * @author JeongSeungsu
	 * @param Directory 현재 파일 디렉토리
	 * @param FileName 현재 파일 이름
	 * @return 성공시 파일내의 스트링,실패시 null
	 */
	static public String FiletoString(String Directory, String FileName){
		StringBuilder Content = new StringBuilder();
		Content.append("");
		
		try{
	        File file = CreateExternalStorageFile(Directory,FileName);
	        
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
	
	
	/**
	 * 파일을 스트링으로 변환
	 * @since 2012. 11. 4.오전 1:09:49
	 * TODO
	 * @author JeongSeungsu
	 * @param file 파일객체
	 * @return
	 */
	static public String FiletoString(File file){
		StringBuilder Content = new StringBuilder();
		Content.append("");
		
		try{
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
	
	
	/**
	 * 파일 삭제
	 * @since 2012. 11. 2.오전 3:17:33
	 * TODO
	 * @author JeongSeungsu
	 * @param Directory
	 * @param FileName
	 * @return 성공시 true 실패시 false
	 */
	static public boolean DeleteFile(String Directory,String FileName){
		boolean Success = false;
		try{
			File file = CreateExternalStorageFile(Directory,FileName); 
			
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
	
	/**
	 * 파일 삭제
	 * @since 2012. 11. 4.오전 2:05:25
	 * TODO
	 * @author JeongSeungsu
	 * @param file
	 * @return 성공시 true 실패시 false
	 */
	static public boolean DeleteFile(File file){
		boolean Success = false;
		try{
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
	
	/**
	 * 파일이 외부 스토리지에 존재하냐 안하냐
	 * @since 2012. 11. 2.오전 3:17:48
	 * TODO
	 * @author JeongSeungsu
	 * @param Directory
	 * @param Filename
	 * @return true 존재 false 없음
	 */
	static public boolean ExistsExternalStorageFile(String Directory, String Filename){
		boolean Success = false;
		
		try{
			File Exdirectory = Environment.getExternalStorageDirectory();
		    File file = new File(Exdirectory+"/"+Directory, Filename);
	        
	        if(file.exists()){
	        	Success = true;
	        }
	    }
		catch(Exception e){
			e.printStackTrace();
			Log.e("LOGDOG",e.getMessage());
		}
		return Success;
	}
	/**
	 * 외부 스토리지 파일을 가져옴
	 * @since 2012. 11. 4.오전 2:08:00
	 * TODO
	 * @author JeongSeungsu
	 * @param Directory
	 * @param Filename
	 * @return 실패시 null값 리턴 성공시 파일인스턴스 리턴
	 */
	static public File GetExternalStorageFile(String Directory,String Filename){
		File Exdirectory = Environment.getExternalStorageDirectory();
	    String State = Environment.getExternalStorageState();
        
        File file = null;
        
        if(State.equals("mounted") && Exdirectory != null)
            file = new File(Exdirectory+"/"+Directory, Filename);
        
        if(file == null)
            Log.e("LOGDOG", "ExternalStroage Access Fail");
        
        if(!file.exists())
        	return null;
        
        return file;
	}
}
