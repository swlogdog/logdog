package logdog.Setting.Controller;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import logdog.Common.DataStore.PMF;
import logdog.Setting.DAO.DeveloperInfo;
import logdog.Setting.DTO.LogInResult;
import logdog.Setting.DTO.LoginStateInfo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * 
 * @since 2012. 11. 19.오전 8:11:29
 * TODO
 * @author Karuana
 */
public class DeveloperChecker {


	public DeveloperChecker() {
		super();

	}
	
	/**
	 *	개발자 로그인 체크 
	 * @since 2012. 11. 19.오전 8:12:26
	 * TODO
	 * @author Karuana
	 * @return
	 */
	public LoginStateInfo DevleoprLogin()
	{
		LogInResult result;
		LoginStateInfo LoginInfo;
		UserService userService = UserServiceFactory.getUserService();
		if(userService.isUserLoggedIn())
		{
			User user= userService.getCurrentUser();
			if(this.PermisionCheck(user))
			{
				result=LogInResult.AUTHORIZED_DEVELOPER;
				LoginInfo = new LoginStateInfo(result,userService.getCurrentUser().getNickname());
			}
			else if(this.IsFirstLogin())
			{
				result=LogInResult.FIRST_LOGIN;
				LoginInfo = new LoginStateInfo(result,userService.getCurrentUser().getNickname());
			}
			else
			{
				result=LogInResult.UNAUTHORIZED_DEVELOPER;
				LoginInfo = new LoginStateInfo(result,null);
			}
		}
		else
		{
			result=LogInResult.UNAUTHORIZED_DEVELOPER;
			LoginInfo = new LoginStateInfo(result,null);
		}

		
		return LoginInfo;
	}
	
	/**
	 *	새로운 개발자를 추가한다. 
	 * @since 2012. 11. 19.오전 8:12:35
	 * TODO
	 * @author Karuana
	 * @param user
	 * @return
	 */
	public boolean insertDeveloper(String user)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();

	
		
		try{	
		
				DeveloperInfo userinfo = new DeveloperInfo(user);
				jdoConnector.makePersistent(userinfo);


		}
		catch(Exception e){
			e.printStackTrace();
			  System.out.println(e.getClass() + "  " + e.getCause());
			return false;
		}
		finally{

			jdoConnector.close();
			
		}
		
		return true;
	}
	
	/**
	 *	처음 로그인한 개발자인지 확인한다. 
	 * @since 2012. 11. 19.오전 8:12:46
	 * TODO
	 * @author Karuana
	 * @return
	 */
	private boolean IsFirstLogin()
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		boolean IsUser = false;
		Query SearchQuery = jdoConnector.newQuery(DeveloperInfo.class);
		List<DeveloperInfo> users=null;
		

		try{	
			users = (List<DeveloperInfo>) SearchQuery.execute();
			if(users.size()==0)
			{	
				IsUser=true;
			}
		}
		catch(Exception e){
				
			  e.printStackTrace();
			  System.out.println(e.getClass() + "  " + e.getCause());
		}
		finally{
			jdoConnector.close();
		}
		
		return IsUser;
	}
	
	/**
	 *	권한이 있는 유저인지 확인한다. 
	 * @since 2012. 11. 19.오전 8:13:00
	 * TODO
	 * @author Karuana
	 * @param user
	 * @return
	 */
	private boolean PermisionCheck(User user)
	{
		PersistenceManager jdoConnector = PMF.getPMF().getPersistenceManager();
		boolean IsPermision = false;
		Query SearchQuery = jdoConnector.newQuery(DeveloperInfo.class);
		List<DeveloperInfo> users=null;
		

		try{
			
			SearchQuery.setFilter("UserId == user");
			SearchQuery.declareParameters("String user");
			
			users = (List<DeveloperInfo>) SearchQuery.execute(user.getEmail());
			if(users.size()>0)
			{	
				IsPermision=true;
			}
		}
		catch(Exception e){
				
			  e.printStackTrace();
			  System.out.println(e.getClass() + "  " + e.getCause());
		}
		finally{
			jdoConnector.close();
		}
		
		return IsPermision;
	}

}
