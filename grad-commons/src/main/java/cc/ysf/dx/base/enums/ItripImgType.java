package cc.ysf.dx.base.enums;

import org.aspectj.apache.bcel.classfile.Code;

/**
 * >>> 图片类型
 */
public enum  ItripImgType {
	IMG_TYPE_HOTEL(0),
	IMG_TYPE_ROOM(1),
	IMG_TYPE_COMMENT(2)
	;
	private int code;

	private ItripImgType(int code){
		this.code=code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
