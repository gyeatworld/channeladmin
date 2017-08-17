package cn.com.bestv.channeladmin.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.com.bestv.infrastructure.common.IResultInfo;
import cn.com.bestv.infrastructure.common.ResultInfo;
import cn.com.bestv.utility.DruidUtil;

/** 
 * @author Vem
 * @date 创建时间：2017年8月16日 下午6:07:34 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class UserService {
	
	private static final Logger logger = Logger.getLogger(UserService.class);
	
	private UserService() {  
		 
        logger.info("Initial UserService");
    }  
    private static UserService _instance = new UserService();  
	  
    public static UserService getInstance() {  
        return _instance;  
    }
    
    public IResultInfo<Map<String,Object>> login(String strUsername,String strPassword) {
		IResultInfo<Map<String,Object>> ri=null;
		Connection readConnection=null;
			
		try {
			readConnection = DruidUtil.getRandomReadConnection();
			
			
			Map<String,Object> mapAdmin=DruidUtil.queryUniqueResult(readConnection, 
					  "SELECT * FROM x_user u INNER JOIN x_admin a ON u.id=a.x_user_id WHERE a.username=?"
					,strUsername );
			

			if (null==mapAdmin || mapAdmin.isEmpty()){ //没记录 
			
				ri=new ResultInfo<Map<String,Object>>("failure",null,"无此用户");
			}else if (null!=mapAdmin && !mapAdmin.isEmpty()){
				
				if (mapAdmin.get("password").toString().equals(strPassword)){
					List<Map<String,Object>> listAdmin=new ArrayList<Map<String,Object>>();
					listAdmin.add(mapAdmin);
			
					ri=new ResultInfo<Map<String,Object>>("success",listAdmin,1,"");	
				}else{
					ri=new ResultInfo<Map<String,Object>>("failure",null,"错误的密码");
				}
			}else{
				ri=new ResultInfo<Map<String,Object>>("failure",null,"诡异的错误");
			}
				
			
		}catch(Exception ex){

			ri=new ResultInfo<Map<String,Object>>("failure",null,ex.getMessage());
		}finally{
			DruidUtil.close(readConnection);
			readConnection=null;
		}
		return ri;
	}
}
