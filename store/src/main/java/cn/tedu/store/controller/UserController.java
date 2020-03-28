package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.controller.ex.FileUploadIOException;
import cn.tedu.store.controller.ex.FileUploadStateException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;
/**
 * 处理用户数据相关请求的控制器类
 * @author lenovo
 *
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService service;
	
	@RequestMapping("reg")
	@ResponseBody
	public JsonResult<Void> reg(User user) {
		//System.out.println("reg");
		//执行注册
		service.reg(user);
		//响应操作成功
		return  new JsonResult<Void>(SUCCESS);	
	}
	
	
	
	
	@RequestMapping("change_password")
	@ResponseBody
	public JsonResult<Void>changePassword(@RequestParam("new_password")String newPassword,
			@RequestParam("old_password")String oldPassword,HttpSession session){
		
		
		Integer uid=getUidFromSession(session);
		String username=session.getAttribute("username").toString();
		System.err.println(uid);
		System.err.println(username);
		System.err.println(newPassword);
		System.err.println(oldPassword);
		
		service.changePassword(uid, newPassword, oldPassword, username);
		System.err.println("修改完毕");
		
		return new JsonResult<Void>(SUCCESS);
	}
	
	
	@RequestMapping("login")
	@ResponseBody
	public JsonResult<User> login(String username,String password,HttpSession session){
		User user=service.login(username, password);
		session.setAttribute("uid",user.getUid());
		session.setAttribute("username",user.getUsername());
		
		return new JsonResult<>(SUCCESS,user);
	}
	
	@RequestMapping("get_info")
	@ResponseBody
	public JsonResult<User>getInfo(HttpSession session){
		Integer uid=getUidFromSession(session);
		User user=service.getByUid(uid);
		
		return new JsonResult<>(SUCCESS,user);
	
	}
	
	@RequestMapping("change_info")
	@ResponseBody
	public JsonResult<Void> changeInfo(User user,HttpSession session) {
		user.setUid(Integer.valueOf(getUidFromSession(session)));
		user.setUsername(getUsernameFromSession(session));
		user.setModifiedTime(new Date());
		user.setModifiedUser(getUsernameFromSession(session));
		service.changeInfo(user);
		return new JsonResult<>(SUCCESS);
	}
	
	
	
	@RequestMapping("change_avatar")
	
	public JsonResult<String>changeAvatar(HttpServletRequest request,@RequestParam("file") MultipartFile file,HttpSession session) {
		//检查文件夹是否为空
		if(file.isEmpty()) {
			throw new FileEmptyException("上传失败！不能上传空文件");
		}
		//检查文件大小
		if(file.getSize()>AVATAR_MAX_SIZE) {
			throw new FileSizeException("上传失败！不允许使用超过"+(AVATAR_MAX_SIZE/1024)+"KB字节的文件");
		}
		//检查文件类型
		if(!AVATAR_CONTENT_TYPES.contains(file.getContentType())) {
			throw new FileTypeException("上传失败！进行允许使用以下类型的图片文件："+AVATAR_CONTENT_TYPES);
		}
		
		
		//确定文件夹
		String dirPath=request.getServletContext().getRealPath(AVATAR_DIR);
		File dir=new File(dirPath);
		if(!dir.exists()) {
			dir.mkdirs();			
		}
		
		
		//确定文件名
		String originalFilename=file.getOriginalFilename();
		String suffix="";
		int beginIndex=originalFilename.lastIndexOf(".");
		
		
//		if(beginIndex!=-1) {
			suffix=originalFilename.substring(beginIndex);
//		}
		String filename=UUID.randomUUID().toString()+suffix;
		
		
		File dest=new File(dir,filename);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			throw new FileUploadStateException("上传失败！请检查源文件是否存在并可以被访问！");		
		} catch (IOException e) {
			throw new FileUploadIOException("上传失败！读出数据时出现未知错误");
		}
		
		//更新数据表
		String avatar="/"+AVATAR_DIR+"/"+filename;
		Integer uid=getUidFromSession(session);
		String username=getUsernameFromSession(session);
		service.changeAvatar(uid, username, avatar);
		
		
		return new JsonResult<>(SUCCESS,avatar);
	}
	
	
}
