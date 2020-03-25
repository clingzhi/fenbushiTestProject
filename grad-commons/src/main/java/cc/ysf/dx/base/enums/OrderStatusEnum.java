package cc.ysf.dx.base.enums;

/**
 * >>> 订单状态
 */
public enum OrderStatusEnum {
	ORDER_STATUS_PREPAY(0),     //预定
	ORDER_STATUS_CANCEL(1),     //取消
	ORDER_STATUS_PAYED(2),      //支付
	ORDER_STATUS_SUCCESS(3),    //成功
	ORDER_STATUS_COMMENT(4)     //评论
	;
	private int code;

	private OrderStatusEnum(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
