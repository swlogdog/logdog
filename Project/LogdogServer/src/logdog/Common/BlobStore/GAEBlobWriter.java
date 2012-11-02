package logdog.Common.BlobStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.files.AppEngineFile;
import com.google.appengine.api.files.FileReadChannel;
import com.google.appengine.api.files.FileService;
import com.google.appengine.api.files.FileServiceFactory;
import com.google.appengine.api.files.FileWriteChannel;

public class GAEBlobWriter implements BlobFileWriter{

	public BlobKey TextWrite(String text)
	{
		BlobKey blobKey =null;
		try{
		// Get a file service
		  FileService fileService = FileServiceFactory.getFileService();

		  // Create a new Blob file with mime-type "text/plain"
		  AppEngineFile file = fileService.createNewBlobFile("text/plain");

		  // Open a channel to write to it
		  boolean lock = false;
		  FileWriteChannel writeChannel = fileService.openWriteChannel(file, lock);

		  // Different standard Java ways of writing to the channel
		  // are possible. Here we use a PrintWriter:
		  PrintWriter out = new PrintWriter(Channels.newWriter(writeChannel, "UTF8"));
		  out.println(text);

		  // Close without finalizing and save the file path for writing later
		  out.close();

		  // Now finalize
		  writeChannel.closeFinally();

		  blobKey = fileService.getBlobKey(file);
		}
		catch(Exception e)
		{
			
		}
		return blobKey;
	}

}
