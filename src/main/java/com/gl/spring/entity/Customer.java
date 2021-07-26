package com.gl.spring.entity;

import java.time.LocalDate;
import java.time.Period;

import org.junit.platform.commons.util.ToStringBuilder;

import com.gl.spring.exception.DLDataInvalidateException;

public class Customer {

	// 必要欄位
	private String id;
	private String email;
	private String password;
	private String name;
	private char gender;
	private LocalDate birthday;
	// 非必要欄位
	private String phone = "";
	private String address = "";
	private boolean subscribed = true;
	// public boolean active;
	private int discount;

	public Customer() {
	}

	public Customer(String id, String password, String name, char gender, String email) {
		this.setId(id);
		this.setPassword(password);
		this.setName(name);
		this.setGender(gender);
		this.setEmail(email);
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public char getGender() {
		return gender;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

//	public boolean isActive() {
//		return this.active;
//	}
//
//	public boolean getActive() {
//		return this.active;
//	}
	public int getDiscount() {
		return discount;
	}

	// 檢查ID
	public void setId(String id) {
		if (checkID(id)) {
			this.id = id;
		} else {
			throw new DLDataInvalidateException("帳號:" + id + "不正確");
		}
	}

	public static final String ID_PATTEN = "[A-Z][1289][0-9]{8}";

	public static boolean checkID(String id) {
		if (id != null && id.matches(ID_PATTEN)) {
			char firstChar = id.charAt(0);
			int firstNum = 0;
			if (firstChar >= 'A' && firstChar <= 'H') {
				firstNum = firstChar - 'A' + 10;
			} else if (firstChar >= 'J' && firstChar <= 'N') {
				firstNum = firstChar - 'J' + 18;
			} else if (firstChar >= 'P' && firstChar <= 'V') {
				firstNum = firstChar - 'P' + 23;
			} else {
				switch (firstChar) {
				case 'X':
					firstNum = 30;
					break;
				case 'Y':
					firstNum = 31;
					break;
				case 'W':
					firstNum = 32;
					break;
				case 'I':
					firstNum = 34;
					break;
				case 'O':
					firstNum = 35;
					break;
				default:
					firstNum = 33;
				}
			}
			int sum = firstChar / 10 + firstChar % 10 * 9;
			for (int i = 1; i < 10; i++) {
				int n = id.charAt(i) - '0';
				sum = sum + n * (i < 9 ? (9 - i) : 1);
			}
			if (sum % 10 == 0) {
				return true;
			}
		}
		return false;
	}

	// 檢查Email
	public static final String EMAIL_FORMAT = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

	public void setEmail(String email) {
		if (email != null && email.matches(EMAIL_FORMAT)) {
			this.email = email;
		} else {
			throw new DLDataInvalidateException("E-mail輸入錯誤");
		}
	}

	// 檢查密碼長度
	public static final int MIN_PWD = 4;
	public static final int MAX_PWD = 12;

	public void setPassword(String password) {
		if (password != null && password.length() >= MIN_PWD && password.length() <= MAX_PWD) {
			this.password = password;
		} else {
			throw new DLDataInvalidateException("密碼不正確");
		}
	}

	public void setName(String name) {
		this.name = name;
	}

	// 檢查性別
	public static final char FEMALE = 'F';
	public static final char MALE = 'M';

	public void setGender(char gender) {
		if (gender == FEMALE || gender == MALE) {
			this.gender = gender;
		} else {
			throw new DLDataInvalidateException("性別必須填寫");
		}
	}

	// 檢查生日
	public static final int MIN_CU_AGE = 12;

	public void setBirthday(LocalDate birthday) {
		if (birthday != null) {
			int age = Period.between(birthday, LocalDate.now()).getYears();
			if (age >= MIN_CU_AGE) {
				this.birthday = birthday;
				return;
			}
		} else {
			throw new DLDataInvalidateException("未滿12遂無法註冊" + birthday);
		}
	}

	/**
	 * 指派客戶物件的生日
	 * 
	 * @param dataString 客戶生日的日期字串(須符合iso 8601規格 1990-10-05, +19990-01-03)
	 */
	public void setBirthday(String dataString) {
		try {
			this.setBirthday(LocalDate.parse(dataString));
		} catch (java.time.format.DateTimeParseException e) {
			throw new DLDataInvalidateException();
		}
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public void setDiscount(int discount) {
		if (discount >= 0 && discount <= 95) {
			this.discount = discount;
		} else {
			System.err.println("VIP折扣不正確");
		}
	}

	public String getDiscountString() {
		int discount = 100 - this.discount;
		return discount + "折";// 8折
	}

//	public void setActive(boolean active) {
//		this.active = active;
//	}

	public boolean isCorrectPWD(String password) {
		return this.password != null && this.password.equals(password);
	}

	@Override
	public String toString() {
		return id + ",\n email =" + email + ",\n password =" + password + ",\n name =" + name + ",\n gender =" + gender
				+ ",\n birthday =" + birthday + ",\n phone =" + phone + ",\n address =" + address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
