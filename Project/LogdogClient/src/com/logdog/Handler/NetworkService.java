package com.logdog.Handler;





import com.logdog.Worker.Worker;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;



/**
 * 서버에 전송할때 켜지는 서비스
 * @since 2012. 11. 12.오전 12:18:37
 * TODO
 * @author JeongSeungsu
 */
public class NetworkService extends Service implements Runnable {

	private Handler m_Handler;
	private boolean m_Running;
	
	/**
	 * 15분마다 체크
	 */
	private static final int CHECK_TIME = 10 * (60*1000);
	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		m_Running = false;
		m_Handler = new Handler();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		m_Running = false;
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		
		super.onStartCommand(intent, flags, startId);
		
        if (!m_Running) {
            // postDelayed : 일정시간마다 메소드 호출
        	m_Running = true;
        	m_Handler.postDelayed(this, 10);
      }
		//m_Handler.postDelayed(this, CHECK_TIME);
				
		return START_NOT_STICKY;
	}

	public void run() {
		// TODO Auto-generated method stub
       
		if (!m_Running) {
            // 서비스 종료 요청이 들어온 경우 그냥 종료
            return;
        } else {

        	if(Worker.getInstance().SendErrorReport())
        		return;
        	else
        		m_Handler.postDelayed(this, CHECK_TIME);
        }
		
	}

}
