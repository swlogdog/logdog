package logdog.Common.BlobStore;

import java.io.IOException;

import com.google.appengine.api.blobstore.BlobKey;

public interface BlobFileWriter {
	
	public BlobKey TextWrite(String text);
	public boolean BlobDelete(BlobKey key);
}
