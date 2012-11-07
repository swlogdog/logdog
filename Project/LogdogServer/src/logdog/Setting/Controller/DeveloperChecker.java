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

public class DeveloperChecker {
	private PersistenceManager jdoConnector;

	public DeveloperChecker() {
		super();
		this.jdoConnector = null;
	}
	
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
	
	public boolean insertDeveloper(String user)
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();

	
		
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
	
	private boolean IsFirstLogin()
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();
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
	
	private boolean PermisionCheck(User user)
	{
		jdoConnector = PMF.getPMF().getPersistenceManager();
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
