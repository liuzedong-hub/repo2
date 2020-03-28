package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressCountLimitException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.DeleteException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
import cn.tedu.store.util.JsonResult;

/**
 * 控制器类的基类
 * @author lenovo
 *
 */

public abstract class BaseController {
	/**
	 * 操作结果的“成功”状态
	 */
	public static final Integer SUCCESS=2000;//这东西自己找自己的规律写，因为这个代号经常用，所以设置成常量
	/**
	 * 上传的头像的最大大小
	 */
	public static final long AVATAR_MAX_SIZE=1*1024*1024;
	/**
	 * 上传时允许的头像文件的类型
	 */
	public static final List<String>AVATAR_CONTENT_TYPES=new ArrayList<>();
	
	public static final String AVATAR_DIR="upload";
	
	static {
		AVATAR_CONTENT_TYPES.add("image/jpeg");
		AVATAR_CONTENT_TYPES.add("image/png");	
	}
	
	
	
	/**
	 * 方便子类从session中获取当前登录的用户的id
	 * @param session
	 * @return
	 */
	protected final Integer getUidFromSession(HttpSession session) {
		System.err.println(session);
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	/**
	 * 方便子类从session中获取当前登录的用户的username
	 * @param session
	 * @return 当前登录的用户名
	 */
	protected final String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
	
	
	@ExceptionHandler({ServiceException.class,FileUploadException.class})
	public JsonResult<Void> handleException(Throwable e) {
		JsonResult<Void> jr=new JsonResult<>();
		jr.setMessage(e.getMessage());
		if(e instanceof UsernameDuplicateException) {
			jr.setState(4000);                //四字头的表示可以很确定的错误
		}else if(e instanceof UserNotFoundException) {
			jr.setState(4001);
		}else if(e instanceof PasswordNotMatchException) {
			jr.setState(4002);
		}else if(e instanceof AddressCountLimitException) {
			jr.setState(4003);
		}
		
		else if(e instanceof InsertException) {
			jr.setState(5000);                //五字头的表示说不清的错误
		}		
		else if(e instanceof UpdateException) {
			jr.setState(5001);                
		}
		
		else if(e instanceof FileEmptyException) {
			jr.setState(6000);
		}
		else if(e instanceof FileSizeException) {
			jr.setState(6001);
		}
		else if(e instanceof FileTypeException) {
			jr.setState(6002);
		}
		else if(e instanceof FileUploadStateException) {
			jr.setState(6003);
		}
		else if(e instanceof FileUploadIOException) {
			jr.setState(6004);
		}else if(e instanceof AddressNotFoundException) {
			jr.setState(6005);
		}else if(e instanceof AccessDeniedException) {
			jr.setState(6006);
		}else if(e instanceof DeleteException) {
			jr.setState(6007);
		}else if(e instanceof CartNotFoundException) {
			jr.setState(6008);
		}
		
		return jr;
		 
	}
	
	
	

}
