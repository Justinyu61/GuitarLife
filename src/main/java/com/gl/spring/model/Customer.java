package com.gl.spring.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

import com.gl.spring.exception.*;

@Component
 public class Customer {
	// 以下為必要欄位
	private String id;// Pkey,必要,符合R.O.C_ID
	private String email;// Unique_Index,Required,符合email格式
	private String password;// 必要,6~20個字元
	private String name;// 必要,2~20個字元
	private char gender;// 必要,'M'-男˙,'F'-女
	private LocalDate birthday;// 必要,必須年滿12歲
	// 以下為非必要的欄位
     private String phone = "";
	// 若沒輸入,使用者在畫面上會看到NULL,會導致頁面當掉//=""代表空字串//=" "代表空白格一字串
	private String address = "";
	private boolean subscribed = true;// 是否訂閱電子報//true偷偷幫顧客訂閱

	

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}
     
	public Customer() {}//這個超級重要的耶!建構子
	
	public Customer(String id, String password, String name) {
		this.setId(id);
		this.setPassword(password);
		this.setName(name);
		
	}
	public Customer(String id, String password, 
			String name, char gender, String email) {
		//super();				
		this(id,password,name);
		this.setEmail(email);
		this.setGender(gender);		
	}

	

	public String getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
     
	public static final String EMAIL_FORMAT="^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	public void setEmail(String email) {
		if(email!=null && email.matches(EMAIL_FORMAT)) {
		this.email = email;
		}else {
			//System.err.println("E-mail輸入錯誤!");
			throw new DLDataInvalidateException("E-mail輸入錯誤!");
		}
	}

	
	public String getPassword() {
		return password;
	}
	
	public void setId(String id) {
		if(checkID(id)) {
			this.id = id;
		}else{
			//System.out.println("id:"+id+"不正確");
 			//System.err.println("id:"+id+"不正確");
 		
			throw new DLDataInvalidateException("帳號:"+id+"不正確");
		}
	}
	public static final int MIN_PWD =6;
	public static final int MAX_PWD =20;
	public void setPassword(String password) {
		if(password!=null &&
				password.length()>MIN_PWD &&
				password.length()<=MAX_PWD) {
			this.password = password;
		}else {
			//System.err.println("密碼錯誤!"+password);
			throw new DLDataInvalidateException("密碼不正確");
		}
		
	}
	public static final int MIN_CU_AGE = 12;
	public void setBirthday(LocalDate birthday) {
		if(birthday!=null) {
			//改用Period
			int age = Period.between(birthday, LocalDate.now()).getYears();
			if(age>=MIN_CU_AGE) {
				this.birthday = birthday;
				return;
			}
//			int theYear = LocalDate.now().getYear();
//			int birthYear = birthday.getYear();
//			if(theYear-birthYear>=12) {
//				this.birthday = birthday ;
//			}else {
				
			}
		///System.err.println("還沒12歲喔"+birthday);
		throw new DLDataInvalidateException("還沒12歲喔"+birthday);
		}
	
	
	/**
	 *  指派客戶物件的生日
	 * @param dateString 客戶生日的日期字串(須符合iso 8601規格 1990-10-05, +19990-01-03)
	 */
	public void setBirthday(String dateString) {
		try {
		this.setBirthday(LocalDate.parse(dateString));		
	}catch(java.time.format.DateTimeParseException e) {
		throw new DLDataInvalidateException();
	    }
	}
	
	/**
	 * 計算客戶年齡
	 * @return 今年減客戶生日屬性紀錄的年度
	 */
//	public int getAge() {
//		int thisYear = LocalDate.now().getYear();
//		if (getBirthday() != null) {
//
//			int birthYear = getBirthday().getYear();
//			int age = thisYear - birthYear;
//
//			return age;
//
//		} else {
//			System.out.println("你是要不要給我生日啊?蛤");
//			
//			
//		}

/**
 * 身分證計算
 * @param id :要檢查的字串
 * @return
 */
	public static final String ID_PATTEN = "[A-Z][1289][0-9]{8}";
	
	public static boolean checkID(String id) {
		if (id != null && id.matches(ID_PATTEN)) {// google:regular expression lib//{}代表要前面的[]要出現幾次
			// TODO:小寫a-z輸入
			// 將第一碼的大寫英文字元轉換對應數字
			char firstChar = id.charAt(0);
			int firstNum = 0;
			if (firstChar >= 'A' && firstChar <= 'H') {
				firstNum = firstChar - 'A' + 10;
			} else if (firstChar >= 'J' && firstChar <= 'N') {
				firstNum = firstChar - 'J' + 18;
			} else if (firstChar >= 'P' && firstChar <= 'V') {
				firstNum = firstChar - 'P' + 23;
				//
				// }else{
				// if(firstChar == 'I') {
				// firstNum = 34;
				// }else if(firstChar == 'O'){
				// firstNum = 35;
				// }else if(firstChar == 'W'){
				// firstNum = 32;
				// }else if(firstChar == 'X'){
				// firstNum = 30;
				// }else if(firstChar == 'Y'){
				// firstNum = 31;
				// }else{
				// firstNum = 33;
				// }
			}else {
				switch (firstChar) {// byte,short,char,int,String,enum
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
			//System.out.println("首碼:" + firstNum);
			
			
			// 2.firstNum的十位數*+個位數+9
			int sum = firstChar / 10 + firstChar % 10 * 9;
             // 3.將第1-9位字元變成數字
			for(int i = 1;i<10;i++) {
				int n =id.charAt(i) - '0';//1
				 //System.out.println(n);
//				 if(i<9) {
//			     sum=sum + n*(9-i); 
//				 }else {
//					 sum=sum + n*1;
//				 }
//				 System.out.println();
				 sum = sum+n*(i<9?(9-i):1);
				// System.out.println("sum"+sum);
			}
			
//			int n3 = id.charAt(1) - '0';
//			int n4 = id.charAt(2) - '0';
//			int n5 = id.charAt(3) - '0';
//			int n6 = id.charAt(4) - '0';
//			int n7 = id.charAt(5) - '0';
//			int n8 = id.charAt(6) - '0';
//			int n9 = id.charAt(7) - '0';
//			int n10 = id.charAt(8) - '0';
//			int n11 = id.charAt(9) - '0';

			// sum = sum +n3*8;.......

			//System.out.println("統一碼:" + firstNum );
         if(sum%10==0) {
			return true;
		
			
		}
	}
	return false;
		}

public char getGender() {
	return gender;
}

public static final char FEMALE='F';
public static final char MALE='M';
public void setGender(char gender) {
	if(gender==MALE || gender==FEMALE) {
	this.gender = gender;
	}else {
		//System.err.println("性別輸入錯誤喔!必須是%s(男)%s(女)");
		throw new DLDataInvalidateException("性別輸入錯誤喔!必須是M(男)F(女)");
		
	}
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public LocalDate getBirthday() {
	return birthday;
}

@Override
public String toString() {
	return  id 
			+ ",\n email=" + email 
			+ ",\n password=" + password 
			+ ",\n name=" + name 
			+ ",\n gender="+ gender 
			+ ",\n birthday=" + birthday 
			+ ",\n phone=" + phone 
			+ ",\n address=" + address ;
			
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
//@Override
//public boolean equals(Object obj) {
//  if(this==obj) return true;
//  if(obj instanceof Customer) {
//	  if(this.id !=null && this.id.equals(((Customer)obj).id)) {
//		  return true;
//	  }
//	  return false;
//  }
//	
//    }

//	private int discount = 5;//0%~95% off,預設8折扣數 
//	
//	public int getDiscount() {
//		return discount;
//	} 
//	
//	public void setDiscount(int discount) {
//		if(discount>=0 && discount<=95) {
//		this.discount = discount;
//		}else {
//			System.err.println("VIP折扣不正確");
//		}
//	}
//	public String getDiscountString() {
//	//getDiscountString()代表中文字串
//		//String discountString;
//		int discount = 100- this.discount;
//	//	 if(discount%10==0) {
//	//		 return discount/10+"折";
//	//	 }
//		return discount + "折";//8折
//		
//		
//	
//	}
}
