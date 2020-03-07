package cc.ysf.dx.base.pojo.vo;

/**
 * >>> 数据传输对象（后端响应对象）
 */
public class ResponseDto<T> {

	private String errorCode;       //该错误码为自定义，一般0表示无错
	private String msg;             //对应的提示信息
	private T data;                 //具体返回数据内容(pojo、自定义VO、其他)
	private String success;         //判断系统是否出错做出相应的true或者false的返回，
										// 与业务无关，出现的各种异常


	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	/**
	 *  >>> 获取相应数据传输对象 -响应成功 （不携带参数）
	 */
	public static ResponseDto<Object> success(){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("true");
		return responseDto;
	}

	/**
	 *  >>> 获取相应数据传输对象 -响应成功 （携带参数 -后端返回的data）
	 */
	public static ResponseDto<Object> success(Object data){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("true");
		responseDto.setData(data);
		return responseDto;
	}

	/**
	 *  >>> 获得响应数据传输对象 -响应失败 （不携带参数）
	 */
	public static ResponseDto<Object> failure(){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("false");
		return responseDto;
	}

	/**
	 *  >>> 获得响应数据传输对象 -响应失败 （携带参数 -错误信息）
	 */
	public static ResponseDto<Object> failure(String msg){
		ResponseDto<Object> responseDto = new ResponseDto<Object>();
		responseDto.setSuccess("false");
		responseDto.setMsg(msg);
		return responseDto;
	}
}
