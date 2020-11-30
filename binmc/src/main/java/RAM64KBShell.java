import java.io.*;

public class RAM64KBShell implements ControlPanel{
  RAM65536x8 ram;
  BufferedReader br;

  RAM64KBShell(){
    ram=new RAM65536x8();
    InputStreamReader isr=new InputStreamReader(System.in);
    br=new BufferedReader(isr);
  }

  public void feeddata(String addr,String w,String di){
    if(addr.length()!=16 || w.length()!=1 || di.length()!=8){
      throw new RuntimeException("[ERR] bit length not proper!");
    }
    ram.feed(addr,w,di);
  }
  
  public String collectdata(){
    return (ram.do7?"1":"0")+(ram.do6?"1":"0")+(ram.do5?"1":"0")+(ram.do4?"1":"0")
          +(ram.do3?"1":"0")+(ram.do2?"1":"0")+(ram.do1?"1":"0")+(ram.do0?"1":"0");
  }
  
  void repl() {
    try{
      for(;;){
        System.out.print("[RAM_64KB]> ");
        String instr=br.readLine();
        String[] parts=instr.split(" ");
        String cmd=parts[0];
        if(cmd.equalsIgnoreCase("exit") || cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("x") || cmd.equalsIgnoreCase("q")){
          break;
        }
        if(cmd.equalsIgnoreCase("r")){
          String addr=parts[1];
          try{
            feeddata(addr,"0","00000000");
            System.out.println(collectdata());
          }catch(Exception e){
            System.err.println(e.getMessage());
          }
        }else if(cmd.equalsIgnoreCase("w")){
          String addr=parts[1];
          String dat=parts[2];
          try{
            feeddata(addr,"1",dat);
            System.out.println(collectdata());
          }catch(Exception e){
            System.err.println(e.getMessage());
          }
        }else{
          System.out.println("[ERR] unknown command!");
        }
      }
    }catch(IOException ioe){
      System.err.println("IO exception: "+ioe.getMessage());
    }
  }
  
  public static void main(String[] args){
    RAM64KBShell ramsh=new RAM64KBShell();
    ramsh.repl();
  }
}

