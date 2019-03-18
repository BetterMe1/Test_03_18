/*
打印购物小票：
1.商品信息用户输入
2.折扣通过用户输入
3.输入计算的金额
4.用户输入支付金额
5.输出完整信息
*/
import java.util.Scanner;

public class HomeWork{
	public static void main(String[] args){
		//1.订单
		Order order =  new Order();
		//2.商品
		Goods[] goodsArray = new Goods[3];
		order.setGoodsArray(goodsArray);
		System.out.println("请输入商品信息格式如下：");
		System.out.println("商品名称  商品价格  商品数量");
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<3; i++){
			String line  = sc.nextLine();
			String[] segments = line.split(" ");
			goodsArray[i] = new Goods(segments[0],
			                          Double.parseDouble(segments[1]),
									  Integer.parseInt(segments[2])
									  );
		}
		
		//3.折扣
		System.out.println("请输入折扣 1-9数字");
		String dcPrint = sc.nextLine();
		order.setDiscount(Integer.parseInt(dcPrint));
		
		//4.计算金额
		Double totalPrice = order.getTotalPrice();
		System.out.println("总金额为：" + totalPrice );
		System.out.println("请输入支付金额：");
		String payPrint = sc.nextLine();
		order.setPay(Double.parseDouble(payPrint));
		
		//5.打印订单
		System.out.println(order);
	}
}
//订单
class Order{
	private Integer discount = 10;//折扣
	private Double pay;//支付
	private Goods[] goodsArray;//商品
	
	public void setDiscount(Integer discount){
		this.discount = discount;
	}
	
	public void setPay(Double pay){
		this.pay = pay;
	}
	
	public void setGoodsArray(Goods[] goodsArray){
		this.goodsArray = goodsArray;
	}
	//打折后的总金额
	public Double getTotalPrice(){
		Double total = 0.0;
		for(int i=0; i<goodsArray.length; i++){
			total += goodsArray[i].getTotalPrice();
		}
		return total * this.discount * 0.1D;
	}
	//找钱
	public Double getChange(){
		return this.pay - this.getTotalPrice();
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("***********消费单***********\n")
		  .append("名称\t单价\t数量\t金额\n");
		  for(int i=0; i<this.goodsArray.length; i++){
			  sb.append(this.goodsArray[i].toString()+"\n");
		  }
		  sb.append("\n")
		    .append("---------------------------\n")
			.append("折扣：").append( "￥"+this.discount).append("折\n")
			.append("消费总金额：").append("￥"+this.getTotalPrice()+"\n")
			.append("实际支付：").append("￥"+this.pay+"\n")
			.append("找零：").append("￥"+this.getChange()+"\n")
			.append("本次积分：").append(this.getTotalPrice()+"\n");
		return sb.toString();
	}
	
}
class Goods{
	private String name;
	private Double price;
	private Integer number;
	
	public Goods(String name,Double price,Integer number){
		this.name = name;
		this.price = price;
		this.number = number;
	}
	public Double getTotalPrice(){
		return this.number * this.price;
	}
	//覆写toString
	public String toString(){
		return this.name + "\t" + "￥" + this.price +"\t" 
		+this.number + "\t" + this.getTotalPrice();
	}
}