package cn.tedu.store.controller.ex;
/**
 * 上传时文件状态异常，例如上传过程中原文件被移除，导致源文件不可被访问
 * @author lenovo
 *
 */
public class FileUploadStateException extends FileUploadException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileUploadStateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileUploadStateException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileUploadStateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileUploadStateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileUploadStateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
