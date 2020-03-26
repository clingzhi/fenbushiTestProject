package cc.ysf.dx.controller;

import cc.ysf.dx.base.controller.BaseController;
import cc.ysf.dx.base.enums.OrderStatusEnum;
import cc.ysf.dx.base.pojo.vo.ResponseDto;
import cc.ysf.dx.pojo.entity.HotelOrder;
import cc.ysf.dx.pojo.entity.HotelRoom;
import cc.ysf.dx.transport.HotelOrderTransport;
import cc.ysf.dx.transport.HotelRoomTransprot;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * >>> 支付模块控制层
 */
@RestController("tradeController")
@RequestMapping("/trade/api")
public class TradeController extends BaseController {
@Autowired
private HotelOrderTransport hotelOrderTransport;
@Autowired
private HotelRoomTransprot hotelRoomTransprot;

	@GetMapping("/prepay/{tradeNo}")
	public void TextTrade(@PathVariable("tradeNo") String tradeNo) throws Exception{

		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByOrderNo(tradeNo);
		HotelRoom hotelRoom = hotelRoomTransprot.queryHotelRoomById(hotelOrder.getRoomId());

		//查询此时订单是否存在 和支付状态
		if(hotelOrder != null && hotelOrder.getOrderStatus() == 0){
			String orderNo = hotelOrder.getOrderNo();
			String totalAmount = hotelOrder.getPayAmount().toString();
			String subject = hotelOrder.getHotelName()+hotelRoom.getRoomTitle();
			String body = hotelOrder.getCount()+"间，"+hotelOrder.getBookingDays()+"天";

			//获得初始化的AlipayClient
		AlipayClient alipayClient =  new DefaultAlipayClient(
				"https://openapi.alipaydev.com/gateway.do" ,
				"2016101900722301",
				"MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDiowsyVYB7oLImbZQdx5/HnERt0Fw2e7A6xPaizoMrELQywYUASfGP/xNe8K8funV0ppkX8Atk5MLvF6k7UXmZ2lGWCELzErjFksloanUKnrevxk1yswnZYQiswSCXEJlJMtOH5rZjV/xWYyLtHLdSvR6JmJdyrXHuzIPaqmiGb2UNhAZcGTbODvLVhRFUFFGUptVt75g9VPgghjf1X0sDQ2LvnLrls73E8JAPEk4FWTBvSl6Og9tSb6XP01038U/LQHjrG4yTNysDynClaYQOwQRq7qtPZTstL8WbrBWffB3ULStOeA0W0kXEvHQXfzxTG6czXDzDylnJSIlzRYlzAgMBAAECggEBALV2iq6Am2FhZ4MxVdkKTzrKOlga7tqI6rADqN7ur0O6Gi5RmKaEjjAXsnDgEbg72XPPOOFbyCO+rv41WcEvGUhA7/ZLIx/xK5S2VSGPq6sXLkhFZRmjlQ1CjmmUMP4DwlhDo+86oWqltluNARgNWBOdbWgKj6i4i3MCuchJjWulH3JT+Z0iXDby+sBiMrOEp6EWUxce7655OQOQkKOS8rZuKuAVbp80lzPs2tlMIbFVIMtXhj77VNylKqKubuXVFKSpVnj5wogOV8ka/3nRAy0FvPb1IglUcxECeql9bLkpZ6kUig/OCKMDLDcUQ0SejrkMCfJTKUthuddUF+O7EIECgYEA/Vnyq5O/MF0+B1cThZ97rdy1zP2RKX2tu9w57sB9ON9cAsMvC8Sn8PwrvBDqHSqox2ncIASsW6h7uTSV9V7A561IZGM1fgev3bcGX1+l1y7x/jk9f/nXirNeCdh8s2jV4QIr4bH4W8WQMyD66efTERKLVKz3C1PV6Ep21/8G2aECgYEA5QGZWxbluOxJrTd8BQmsKvlppBk1Itwj0Wlln9wVr2P5ooL5MvSnlOR1pQBWe+ZL0pAMwJICuVHB2o5p5o3kjUOXzNzTBdyszLjVPMGmOSJPNGuewyd3wNJjQQ5OMiWGW1QqT6E9ht5Rrak6PB1M8Rdk9NqdOIw07rvcTZcBUpMCgYEApTA/vMWR1EwHhKuu6+Intx4AX885Fe9/Fs0p7NUPEMT57kcrZqXrxoH1ViNFMo09DQCl6S6C3ySEvyZdXGZSqvxeBi51k14Zuht7CTaJIDvYxyS1R7s0YZhACXVhIW2KfrJ2wCrVP6kaM2wejeJ8yVXpCOXaEkWwZEwhHwaGheECgYB9TkavhPPmH8Sgtz9xMj22tgqL9A8TKAmhSF6G/lIPSlEKD0h0DVK1Mdz9kBXCbd0HNjMEG+DJrma4RDzGHtX0RgwF2L7O8lTLIEftmrhtgsx8DncsqafpJjWRH1bCMa42wvLz53JXHcbrkzF+OzoioX5oTEbC7lT4XGrLOdlLaQKBgQDFrmePjaC7nM218pXiVdzorLGT3ktUWJ/yYobNZBNeXNeUU+b0vKWqYsxjJY/3vA0C1fDVPH2nbvfWDp6S+E9JCVJgIVFAYVMfYdbso98KZUx4UeNvOcrotLv7/VNevNXnYmqs03mef9hhVVFDISWlRIMhKHYltf+4KpigqVf1+Q==",
				"json",
				"UTF-8",
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA4qMLMlWAe6CyJm2UHcefx5xEbdBcNnuwOsT2os6DKxC0MsGFAEnxj/8TXvCvH7p1dKaZF/ALZOTC7xepO1F5mdpRlghC8xK4xZLJaGp1Cp63r8ZNcrMJ2WEIrMEglxCZSTLTh+a2Y1f8VmMi7Ry3Ur0eiZiXcq1x7syD2qpohm9lDYQGXBk2zg7y1YURVBRRlKbVbe+YPVT4IIY39V9LA0Ni75y65bO9xPCQDxJOBVkwb0pejoPbUm+lz9NdN/FPy0B46xuMkzcrA8pwpWmEDsEEau6rT2U7LS/Fm6wVn3wd1C0rTngNFtJFxLx0F388UxunM1w8w8pZyUiJc0WJcwIDAQAB",
				"RSA2");

		//创建API对应的request
		AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest();
		//支付完成返回地址
		alipayRequest.setReturnUrl("http://itrip.project.bdqn.cn/trade/api/notify/"+hotelOrder.getId());
		//在公共参数中设置回跳和通知地址
		alipayRequest.setNotifyUrl( "http://localhost/itrip");
		//填充业务参数
			alipayRequest.setBizContent( "{"  +
					"    \"out_trade_no\":\""+orderNo+"\","  +
					"    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
					"    \"total_amount\":"+totalAmount+","  +
					"    \"subject\":\""+subject+"\","  +
					"    \"body\":\""+body+"\","  +
					"    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
					"    \"extend_params\":{"  +
					"    \"sys_service_provider_id\":\"2088511833207846\""  +
					"    }" +
					"  }" );

		String form= "" ;
		try  {
			form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
		}  catch  (AlipayApiException e) {
			e.printStackTrace();
		}
		response.setContentType( "text/html;charset=UTF-8");
		response.getWriter().write(form); //直接将完整的表单html输出到页面
		response.getWriter().flush();
		response.getWriter().close();
		}
	}

	/**
	 * >>> 支付完成，修改订单状态
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/notify/{orderId}")
	public ResponseDto<Object> updateHotelOrder(@PathVariable("orderId") Long orderId) throws Exception{
		//修改订单状态
		HotelOrder upOrder = new HotelOrder();
		upOrder.setId(orderId);

		HotelOrder order = hotelOrderTransport.queryOrderById(orderId);
		order.setOrderStatus(OrderStatusEnum.ORDER_STATUS_PAYED.getCode());

		boolean flog = hotelOrderTransport.updateOrderStatus(order);
		if (flog){
			return ResponseDto.success("支付成功");
		}
		return ResponseDto.success("支付失败");
	}
}
