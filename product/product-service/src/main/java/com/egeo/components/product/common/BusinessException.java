package com.egeo.components.product.common;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * 
 * @author shaoyifeng
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -2589309723938600227L;

	protected int errNo;

	protected String sMsg = "";

	protected Throwable rootCause;

	public static final int ERR_UNKNOWN = 0;

	public static final int ERR_MYEXCEPTION = 1;

	public static final int ERR_DATACONVERT = 2;

	public static final int ERR_PARAM_INVALID = 10;

	public static final int ERR_OBJ_NULL = 20;

	public static final int ERR_NUMOP_FAIL = 30;

	public static final int ERR_DBOP_FAIL = 40;

	public static final int ERR_CONNECTION_GETFAIL = 41;

	public static final int ERR_FILEOP_FAIL = 50;

	public static final int ERR_FILEOP_OPEN = 51;

	public static final int ERR_FILEOP_CLOSE = 52;

	public static final int ERR_FILEOP_READ = 53;

	public static final int ERR_FILEOP_WRITE = 54;

	public static final int ERR_FILE_NOTFOUND = 55;

	public static final int ERR_URL_MALFORMED = 110;

	public static final int ERR_NET_OPENSTREAM = 111;

	public static final int ERR_USER_NOTLOGIN = 99;

	public BusinessException(int _errNo) {
		rootCause = null;
		errNo = _errNo;
	}

	public BusinessException(int _errNo, String _sMsg) {
		super(_sMsg);
		sMsg = _sMsg;
		rootCause = null;
		errNo = _errNo;
	}

	public BusinessException(ResponseCodeEnum responseCodeEnum) {
		super(responseCodeEnum.getMsg());
		sMsg = responseCodeEnum.getMsg();
		rootCause = null;
		errNo = responseCodeEnum.getCode();
	}

	public BusinessException(ResponseCodeEnum responseCodeEnum, String param) {
		super(String.format(responseCodeEnum.getMsg(), param));
		sMsg = String.format(responseCodeEnum.getMsg(), param);
		rootCause = null;
		errNo = responseCodeEnum.getCode();
	}

	public BusinessException(String _sMsg, Throwable _rootCause) {
		super(_sMsg);
		sMsg = _sMsg;
		errNo = 0;
		rootCause = _rootCause;
	}

	public BusinessException(int _errNo, String _sMsg, Throwable _rootCause) {
		super(_sMsg);
		sMsg = _sMsg;
		errNo = _errNo;
		rootCause = _rootCause;
	}

	public int getErrNo() {
		return errNo;
	}

	public Throwable getRootCause() {
		return rootCause;
	}

	public String getErrNoMsg() {
		return sMsg;
	}

	public String getMyMessage() {
		return super.getMessage();
	}

	public String getMessage() {
		if (rootCause == null)
			return String.valueOf(
					String.valueOf((new StringBuffer("[")).append(errNo).append("]").append(super.getMessage())));
		else
			return String.valueOf(String.valueOf((new StringBuffer("[")).append(errNo).append("]")
					.append(super.getMessage()).append(";\r\n <-- ").append(rootCause.toString())));
	}

	public String getLocalizedMessage() {
		return getMessage();
	}

	public void printStackTrace(PrintStream _ps) {
		if (rootCause == null)
			super.printStackTrace(_ps);
		else
			synchronized (_ps) {
				_ps.println(this);
				rootCause.printStackTrace(_ps);
			}
	}

	public void printStackTrace(PrintWriter _pw) {
		if (rootCause == null)
			super.printStackTrace(_pw);
		else
			synchronized (_pw) {
				_pw.println(this);
				rootCause.printStackTrace(_pw);
			}
	}

	/**
	 * Construct an Business resource Exception Description message with no
	 * replacement values.
	 * 
	 * @param key Message key for this message
	 */
	public BusinessException(String key) {

		this.key = key;
		this.values = null;

	}

	/**
	 * Construct an Business resource Exception Description message with the
	 * specified replacement values.
	 * 
	 * @param key    Message key for this message
	 * @param value0 First replacement value
	 */
	public BusinessException(String key, Object value0) {

		this.key = key;
		this.values = new Object[] { value0 };

	}

	/**
	 * Construct an Business resource Exception Description message with the
	 * specified replacement values.
	 * 
	 * @param key    Message key for this message
	 * @param value0 First replacement value
	 * @param value1 Second replacement value
	 */
	public BusinessException(String key, Object value0, Object value1) {

		this.key = key;
		this.values = new Object[] { value0, value1 };

	}

	/**
	 * Construct an Business resource Exception Description message with the
	 * specified replacement values.
	 * 
	 * @param key    Message key for this message
	 * @param value0 First replacement value
	 * @param value1 Second replacement value
	 * @param value2 Third replacement value
	 */
	public BusinessException(String key, Object value0, Object value1, Object value2) {

		this.key = key;
		this.values = new Object[] { value0, value1, value2 };

	}

	/**
	 * Construct an Business resource Exception Description message with the
	 * specified replacement values.
	 * 
	 * @param key    Message key for this message
	 * @param value0 First replacement value
	 * @param value1 Second replacement value
	 * @param value2 Third replacement value
	 * @param value3 Fourth replacement value
	 */
	public BusinessException(String key, Object value0, Object value1, Object value2, Object value3) {

		this.key = key;
		this.values = new Object[] { value0, value1, value2, value3 };
	}

	/**
	 * Construct an Business resource Exception Description message with the
	 * specified replacement values.
	 * 
	 * @param key    Message key for this message
	 * @param values Array of replacement values
	 */
	public BusinessException(String key, Object[] values) {

		this.key = key;
		this.values = values;

	}

	// ----------------------------------------------------- Instance Variables

	/**
	 * The message key for this message.
	 */
	protected String key = null;

	/**
	 * The replacement values for this mesasge.
	 */
	protected Object values[] = null;

	// --------------------------------------------------------- Public Methods

	/**
	 * Get the message key for this message.
	 */
	public String getKey() {

		return (this.key);

	}

	/**
	 * Get the replacement values for this message.
	 */
	public Object[] getValues() {

		return (this.values);

	}

}
