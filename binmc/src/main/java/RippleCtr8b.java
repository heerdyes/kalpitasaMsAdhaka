import java.util.Timer;
import java.util.TimerTask;

public class RippleCtr8b extends TimerTask
{
  long T;
  volatile boolean clk;
  DFlipFlop f0, f1, f2, f3, f4, f5, f6, f7;

  public RippleCtr8b(long millis)
  {
    T = millis;
    clk = false;
    f0 = new DFlipFlop();
    f1 = new DFlipFlop();
    f2 = new DFlipFlop();
    f3 = new DFlipFlop();
    f4 = new DFlipFlop();
    f5 = new DFlipFlop();
    f6 = new DFlipFlop();
    f7 = new DFlipFlop();
  }

  public void run()
  {
    clk = !clk;
    f0.setClk(clk);
    f1.setClk(!f0.q);
    f2.setClk(!f1.q);
    f3.setClk(!f2.q);
    f4.setClk(!f3.q);
    f5.setClk(!f4.q);
    f6.setClk(!f5.q);
    f7.setClk(!f6.q);
    if(clk)
    {
      System.out.println((f7.q?"1":"0") + (f6.q?"1":"0") + (f5.q?"1":"0") + (f4.q?"1":"0") + (f3.q?"1":"0") + (f2.q?"1":"0") + (f1.q?"1":"0") + (f0.q?"1":"0"));
    }
  }

  /////////////////////////////////////
  // --------- for testing ----------//
  /////////////////////////////////////

  public static void main(String[] args)
  {
    RippleCtr8b c = new RippleCtr8b(100);
    Timer t = new Timer();

    t.scheduleAtFixedRate(c, c.T, c.T);
  }
}

