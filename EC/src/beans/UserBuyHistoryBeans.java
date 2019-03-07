package beans;

import java.io.Serializable;
import java.util.Date;

// 追加:BuyDAO.javaで使うBEANS　
//これいらんかも

public class UserBuyHistoryBeans implements Serializable {

		private int buyId;
		private int userId;
		private int totalPrice;
		private Date createDate;

		private int deliveryMethodId;
		private String deliveryMethodName;
		private int DeliveryMethodPrice;


		public int getBuyId() {
			return buyId;
		}
		public void setBuyId(int buyId) {
			this.buyId = buyId;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public int getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(int totalPrice) {
			this.totalPrice = totalPrice;
		}
		public Date getCreateDate() {
			return createDate;
		}
		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}
		public int getDeliveryMethodId() {
			return deliveryMethodId;
		}
		public void setDeliveryMethodId(int deliveryMethodId) {
			this.deliveryMethodId = deliveryMethodId;
		}
		public String getDeliveryMethodName() {
			return deliveryMethodName;
		}
		public void setDeliveryMethodName(String deliveryMethodName) {
			this.deliveryMethodName = deliveryMethodName;
		}
		public int getDeliveryMethodPrice() {
			return DeliveryMethodPrice;
		}
		public void setDeliveryMethodPrice(int deliveryMethodPrice) {
			DeliveryMethodPrice = deliveryMethodPrice;
		}
}
