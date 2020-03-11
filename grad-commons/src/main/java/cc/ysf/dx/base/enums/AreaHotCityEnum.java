package cc.ysf.dx.base.enums;

/**
 * >>> 是否是热门城市枚举类
 */
public enum AreaHotCityEnum {
	AREA_HOT_YES(1),
	AREA_HOT_NO(0)
	;
	private int code;

	private AreaHotCityEnum(int code){
		this.code=code;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
