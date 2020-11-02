public class Latch8b{
  boolean clk;
  boolean dq0,dq1,dq2,dq3,dq4,dq5,dq6,dq7;
  
  void feed(byte clk,
            byte d7,byte d6,byte d5,byte d4,byte d3,byte d2,byte d1,byte d0){
    feed(clk==0?false:true,
         d7==0?false:true,d6==0?false:true,d5==0?false:true,d4==0?false:true,d3==0?false:true,d2==0?false:true,d1==0?false:true,d0==0?false:true);
  }
  
  void feed(boolean clk,
            boolean d7,boolean d6,boolean d5,boolean d4,boolean d3,boolean d2,boolean d1,boolean d0){
    if(clk){
      dq0=d0;
      dq1=d1;
      dq2=d2;
      dq3=d3;
      dq4=d4;
      dq5=d5;
      dq6=d6;
      dq7=d7;
    }
  }
}
