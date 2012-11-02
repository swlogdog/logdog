package logdog.Common.BlobStore;

import logdog.Common.ServiceType;


public class BlobWriterFactory {

	
	public static BlobFileWriter GetBlobService(ServiceType backType)
	{
		
		BlobFileWriter product=null;
		
		switch(backType)
		{
			case GOOGLE_APP_ENGINE:
				product = new GAEBlobWriter();
				break;
			case NON:
				product = null;
				break;
		}
		return product;
	}
}
