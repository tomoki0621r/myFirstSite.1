/**
* inputYMDの数値が開始日であるstartYMDと終了日であるendYMDの中に入っているか探し、入っている数値を返す。
* 返された数値はisMatchメソッドの中で保管され、EventFilterクラスに移行する。
*/
public class DataFilter implements EventFilter{
    
    private int inputYMD;
    
    public DataFilter(int inputYMD){
      this.inputYMD = inputYMD;
    }
    
    public boolean isMatch(EventsData events){
      return events.startYMD <= inputYMD && inputYMD <= events.endYMD;
    }
}