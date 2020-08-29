package JavaBean;

//设置泛型通用
public class ResponseSet<T> {
	
	private int stutasCode;   //响应状态码
	
	private String message;   //响应信息
	
	private T data;           //响应的数据,泛型通用

	public int getStutasCode() {
		return stutasCode;
	}

	public void setStutasCode(int stutasCode) {
		this.stutasCode = stutasCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ResponseSet(int stutasCode, String message, T data) {
		super();
		this.stutasCode = stutasCode;
		this.message = message;
		this.data = data;
	}
	
	

}
