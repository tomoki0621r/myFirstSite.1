/**
* inputSがevents.endMD以上でかつ、inputEがevents.startMD以下の値を探し一致しているものを返す。
* 返された数値はisMatchメソッドの中で保管され、EventFilterクラスに移行する。
*/
public class SeasonFilter implements EventFilter{
    
    private int inputS;
            int inputE;
            
    public SeasonFilter(int inputS,int inputE){
      this.inputS = inputS;
      this.inputE = inputE;
    }
    
    public boolean isMatch(EventsData events){
      return inputS  <= events.endMD && inputE >= events.startMD;
    }
}