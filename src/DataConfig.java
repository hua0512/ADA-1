public class DataConfig {

  private long comps = 0, asigs = 0, time = 0;


  public void incrementarComps(int comps) {
    this.comps += comps;
  }

  public void incrementarComps() {
    comps++;
  }

  public void incrementarAsigs() {
    asigs++;
  }

  public void incrementarAsigs(int asigs) {
    this.asigs += asigs;
  }

  public void incrementarTime(long time) {
    this.time += time;
  }

  public long getAsigs() {
    return asigs;
  }

  public void setAsigs(int asigs) {
    this.asigs = asigs;
  }

  public long getComps() {
    return comps;
  }

  public void setComps(int comps) {
    this.comps = comps;
  }

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }
}
