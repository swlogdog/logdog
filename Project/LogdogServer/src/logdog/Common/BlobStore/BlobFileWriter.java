package logdog.Common.BlobStore;

import java.io.IOException;

import com.google.appengine.api.blobstore.BlobKey;

/**
 * 	BlobStore의 작업 중 쓰기와 삭제 작업을 추상화한 인터페이스이다. 
 * @since 2012. 11. 15.오전 5:52:48
 * TODO
 * @author Karuana
 */
public interface BlobFileWriter {
	
	/**
	 *	파라미터로 넘겨져오는 데이터를 BlobStore에 기록한다.
	 *  기록 후 저장된 요소의 Key를 리턴한다. 
	 * @since 2012. 11. 15.오전 5:53:21
	 * TODO
	 * @author Karuana
	 * @param text
	 * @return BlobKey(저장된 데이터의 키 값)
	 */
	public BlobKey TextWrite(String text);
	/**
	 * Key가 가르키는 요소를 삭제한다.
	 * @since 2012. 11. 15.오전 5:54:30
	 * TODO
	 * @author Karuana
	 * @param key
	 * @return 작업의 결과
	 */
	public boolean BlobDelete(BlobKey key);
}
