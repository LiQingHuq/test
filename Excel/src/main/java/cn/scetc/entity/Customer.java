package cn.scetc.entity;

public class Customer {
private String userName;//用户名称
private String userState;//用户状态
private String ceMing;//册名
private String userAddress;//用户地址
private String userType;//用户类型
private String meterId;//水表编号
private String meterButton;//水表底度
private String meterState;//水表状态

	private String userTel;//用户电话号码
	private String userIDcard;//用户证件
	private String IcardNumber;//证件号码
	private String userBalance;//用户余额
	private String  userWater;//用水性质

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserState() {
	return userState;
}
public void setUserState(String userState) {
	this.userState = userState;
}
public String getCeMing() {
	return ceMing;
}
public void setCeMing(String ceMing) {
	this.ceMing = ceMing;
}
public String getUserAddress() {
	return userAddress;
}
public void setUserAddress(String userAddress) {
	this.userAddress = userAddress;
}
public String getUserType() {
	return userType;
}
public void setUserType(String userType) {
	this.userType = userType;
}
public String getMeterId() {
	return meterId;
}
public void setMeterId(String meterId) {
	this.meterId = meterId;
}
public String getMeterButton() {
	return meterButton;
}
public void setMeterButton(String meterButton) {
	this.meterButton = meterButton;
}
public String getMeterState() {
	return meterState;
}
public void setMeterState(String meterState) {
	this.meterState = meterState;
}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserIDcard() {
		return userIDcard;
	}

	public void setUserIDcard(String userIDcard) {
		this.userIDcard = userIDcard;
	}

	public String getIcardNumber() {
		return IcardNumber;
	}

	public void setIcardNumber(String icardNumber) {
		IcardNumber = icardNumber;
	}

	public String getUserBalance() {
		return userBalance;
	}

	public void setUserBalance(String userBalance) {
		this.userBalance = userBalance;
	}

	public String getUserWater() {
		return userWater;
	}

	public void setUserWater(String userWater) {
		this.userWater = userWater;
	}
}
