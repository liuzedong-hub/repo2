package cn.tedu.store.controller.ex;
/**
 * 上传的文件为空，例如没有选择要上传的文件，或选择的文件是0字节的
 * @author lenovo
 *
 */
public class FileEmptyException extends FileUploadException {

	public FileEmptyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileEmptyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
