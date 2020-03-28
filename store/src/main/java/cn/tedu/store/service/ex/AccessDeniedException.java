package cn.tedu.store.service.ex;
/**
 * 拒绝访问，可能因为尝试访问他人的数据
 * @author lenovo
 *
 */
public class AccessDeniedException extends ServiceException{
	public AccessDeniedException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AccessDeniedException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
