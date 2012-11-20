package logdog.Common.BlobStore;

import logdog.Common.ServiceType;


/**
 * 	BlobStore에 기록을 하기 위한 객체를 생산해주는 팩토리 클래스이
 * 
 * @since 2012. 11. 15.오전 5:55:10
 * TODO
 * @author Karuana
 */
public class BlobWriterFactory {

	
	/**
	 *  BlobStore에 데이터를 쓰기 위한 객체를 생성하여 리턴받는다.
	 * @since 2012. 11. 15.오전 5:55:39
	 * TODO
	 * @author Karuana
	 * @param backType
	 * @return BlobFileWriter blobStore에 파일을 쓰기 위한 객체를 리턴 
	 */
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
