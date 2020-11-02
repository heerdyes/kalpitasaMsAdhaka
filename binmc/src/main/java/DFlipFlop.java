public class DFlipFlop
{
  boolean clk, d, q;

  public DFlipFlop() { clk = d = q = false; }

  public void setClk(boolean v)
  {
    boolean oldv = clk;
    clk =v;
    if(!oldv && v)
    {
      q = d;
      d = !q;
    }
  }
}

