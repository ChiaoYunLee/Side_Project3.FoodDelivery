import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/**
		  enroll
		*/
//		System.out.print("Please input the following data (Name, Sex, Phone, Email, Password):");
//		String a,b,c,d,e="";
//		Scanner in=new Scanner(System.in);
//		a=in.next();
//		b=in.next();
//		c=in.next();
//		d=in.next();
//		e=in.next();
//		Connection conn=null;
//		conn=Sql_Connection.connection_mysql();
//		PreparedStatement st=conn.prepareStatement("SELECT Courier_Phone FROM COURIER WHERE Courier_Phone = ?");
//		st.setString(1,c);
//		ResultSet result=st.executeQuery();
//		if(result==null)//資料中沒有相同的phone才可註冊
//		{
//			Courier courier=new Courier();
//			courier.addCourier(a,b,c,d,e);
//		}
//		else
//		{
//			System.out.println("Invalid phone number");//再篩長度？
//		}
		/**
		 * log in
		 */
		System.out.println("Please enter your phone number and password:");
		Scanner in2=new Scanner(System.in);
		String phone=in2.next();
		String pw=in2.next();
		Connection conn2=null;
		conn2=Sql_Connection.connection_mysql();
		PreparedStatement st2=conn2.prepareStatement("SELECT * FROM COURIER WHERE Courier_Phone = ?");
		st2.setString(1,phone);
		ResultSet result2=st2.executeQuery();
		String id="";
		if(result2.next()&&(result2.getString("Courier_Password")).equals(pw))
		{
			id=result2.getString("Courier_ID");//提供後面查看之前評價使用
			Courier courier=new Courier();
			courier.logIn(phone,pw);
			System.out.println(courier.getInfo()+"\n-----------------------------------------");
		}
		else
		{
			System.out.println("Invalid Input");
		}
		/**
		 * past reviews
		 */
		Connection conn3=null;
		conn3=Sql_Connection.connection_mysql();
		PreparedStatement st3=conn3.prepareStatement("SELECT Order_ID, GROUP_CONCAT(COURIER_REVIEW.Comment_Description) AS descrip, SUM(Point) AS point FROM REVIEW, COURIER_REVIEW, COMMENT WHERE Courier_ID=? AND Review_type='CO' AND Review_ID= CourierReview_ID AND COURIER_REVIEW.Comment_ID=COMMENT.Comment_ID GROUP BY Order_ID");
		st3.setString(1,id);
		ResultSet result3=st3.executeQuery();
		System.out.println("Courier's past reviews:\n");
		while(result3.next())
		{
			 System.out.println(result3.getString("Order_ID") + "    " + result3.getString("descrip")+"    "+result3.getString("point"));
		}
		System.out.println("------------------------------------------");
		/**
		 * accept order or not & 看顧客評價
		 */
		String customerID="";
		Connection conn4=null;
		conn4=Sql_Connection.connection_mysql();
		PreparedStatement st4=conn4.prepareStatement("SELECT ORDER.Order_ID, Customer_ID, GROUP_CONCAT(ITEM.Item_Name) AS food, Food_Price + Delivery_Fee AS price, Order_PaymentMethod FROM dbms_group5.ORDER, ORDER_ITEM, ITEM WHERE Courier_ID IS NULL AND ORDER.Order_ID=ORDER_ITEM.Order_ID AND ORDER_ITEM.Item_ID= ITEM.Item_ID GROUP BY Order_ID");
		ResultSet result4=st4.executeQuery();
		System.out.println("Order to be accepted:");
		while(result4.next())
		{
			 customerID=result4.getString("Customer_ID");
			 System.out.println("\n"+result4.getString("Order_ID")+ "    " + result4.getString("food")+"    "+result4.getString("price")+"   "+result4.getString("Order_PaymentMethod")); 
			 PreparedStatement st5=conn4.prepareStatement("SELECT Order_ID, GROUP_CONCAT(CUSTOMER_REVIEW.Comment_Description) AS descrip, SUM(Point) AS point FROM REVIEW, CUSTOMER_REVIEW, COMMENT WHERE REVIEW.Customer_ID=? AND Review_type='CU' AND Review_ID= CustomerReview_ID AND CUSTOMER_REVIEW.Comment_ID=COMMENT.Comment_ID GROUP BY Order_ID");
			 st5.setString(1,customerID);
			 ResultSet result5=st5.executeQuery();
			 while(result5.next())
			 {
				 System.out.println("\nThis customer's past reviews:\n"+result5.getString("Order_ID") + "    " + result5.getString("descrip")+"    "+result5.getString("point")+"\n-------------");
			 }
		}
//
//		System.out.println("Do you want to accept this order?");
//		Connection conn6=null;
//		conn6=Sql_Connection.connection_mysql();
//		Scanner in3=new Scanner(System.in);
//		String answer=in3.next();
//		if(answer.equals("yes"))
//		{
//			PreparedStatement st6=conn6.prepareStatement("UPDATE dbms_group5.ORDER SET Courier_ID= ? WHERE Order_ID= ?");
//			st6.setString(1,id);
//			st6.setString(2,"O6");
//			int affectedRows=st6.executeUpdate();
//			if(affectedRows>0)
//			{
//				System.out.println("You got this order!");
//			}
//		}
		/**
		 * 查看未完成訂單
		 */
		Connection conn7=null;
		conn7=Sql_Connection.connection_mysql();
		System.out.println("------------------------------------------\nOrder Uncompleted:");
		PreparedStatement st7=conn7.prepareStatement("SELECT Order_ID FROM dbms_group5.ORDER WHERE NOT EXISTS (SELECT Order_ID FROM REVIEW WHERE Review_Type='CO'AND ORDER.Order_ID=REVIEW.Order_ID)AND ORDER.Courier_ID=?");
		st7.setString(1,id);
		ResultSet result7=st7.executeQuery();
		while(result7.next())
		{
			System.out.println(result7.getString("Order_ID"));
		}
		/**
		 * show評價
		 */
//		//Good
//		System.out.println("------------------------------------------\nThese are good comments: ");
//		Connection conn8=null;
//		conn8=Sql_Connection.connection_mysql();
//		PreparedStatement st8=conn8.prepareStatement("SELECT Comment_Description FROM dbms_group5.COMMENT WHERE Comment_Type='CUG'");
//		ResultSet result8=st8.executeQuery();
//		while(result8.next())
//		{
//			System.out.println(result8.getString("Comment_Description")+"  ");
//		}
//		//Bad
//		System.out.println("\nThese are bad comments: ");
//		Connection conn9=null;
//		conn9=Sql_Connection.connection_mysql();
//		PreparedStatement st9=conn9.prepareStatement("SELECT Comment_Description FROM dbms_group5.COMMENT WHERE Comment_Type='CUB'");
//		ResultSet result9=st9.executeQuery();
//		while(result9.next())
//		{
//			System.out.println(result9.getString("Comment_Description")+"  ");
//		}
		/**
		 * 給評價
		 */
		//先加在review大表
//		Connection conn10=null;
//		conn10=Sql_Connection.connection_mysql();
//		Statement st10= conn10.createStatement();
//		String reviewID="";
//		ResultSet result10 = st10.executeQuery("SELECT Review_ID FROM REVIEW ORDER BY Review_ID DESC limit 1");
//		if(result10.next())
//		{
//			String lastID=result10.getString("Review_ID");
//			String lastNum=lastID.substring(1,lastID.length());
//			int currentNum=Integer.parseInt(lastNum)+1;
//			reviewID="R"+currentNum;
//		}
//		PreparedStatement st11=conn10.prepareStatement("INSERT INTO dbms_group5.REVIEW VALUES (?,?,?,?,?)");
//		st11.setString(1,reviewID);
//		st11.setString(2, "O6");//該單的ID
//		st11.setString(3, "A5");//該單顧客ID(可能要再透過sql找
//		st11.setString(4, id);//外送員ID
//		st11.setString(5, "CU");
//		st11.executeUpdate();
//		System.out.println("How do you think of this customer?");
//		Scanner in3=new Scanner(System.in);
//		int point=0;
//		while(in3.hasNext())
//		{
//			String review=in3.next();
//			String description="";
//			PreparedStatement st12=conn10.prepareStatement("SELECT Comment_Description,Point FROM COMMENT WHERE Comment_ID=? ");
//			st12.setString(1,review);
//			ResultSet result12=st12.executeQuery();
//			while (result12.next())
//			{
//				description=result12.getString("Comment_Description");
//				point+=result12.getInt("Point");
//			}
//			PreparedStatement st13=conn10.prepareStatement("INSERT INTO CUSTOMER_REVIEW VALUES (?,?,?)");
//			st13.setString(1, reviewID);
//			st13.setString(2, review);
//			st13.setString(3, description);
//			st13.executeUpdate();
//		}
//		PreparedStatement st14=conn10.prepareStatement("UPDATE CUSTOMER SET Customer_Point += ? WHERE Customer_ID=?");
//		st14.setInt(1, point);
//		st14.setString(2,"A5");//要再改
//		st14.executeUpdate();
//		System.out.println("Thankyou for your review!");
		
		//再分細項加
		
		
		
	}

}
