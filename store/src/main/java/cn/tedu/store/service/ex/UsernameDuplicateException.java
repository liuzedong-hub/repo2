package cn.tedu.store.service.ex;
/**
 * 用户名冲突异常，例如用户名已经被占用
 * @author lenovo
 *
 */
public class UsernameDuplicateException extends ServiceException {

	
	public UsernameDuplicateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameDuplicateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
