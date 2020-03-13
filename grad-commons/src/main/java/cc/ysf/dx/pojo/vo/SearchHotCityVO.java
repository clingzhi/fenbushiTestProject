package cc.ysf.dx.pojo.vo;

import java.io.Serializable;

/**
 * >>> 爱旅行 热门城市酒店实体类
 */
public class SearchHotCityVO implements Serializable {
	private static final long serialVersionUID = 5928495820556209993L;
	private Integer cityId;
	private Integer count;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
