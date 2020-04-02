/**
* eventsの中のAreaFilterを参照して、chiikiAreaの数値に一致する地域があるかを探し一致しているものを返す。
* 返された地域はisMatchメソッドの中で保管され、EventFilterクラスに移行する。
*/
public class AreaFilter implements EventFilter{
    
    private String chiikiArea;
    
    public AreaFilter(String chiikiArea){
      this.chiikiArea = chiikiArea;
    }
    
    public boolean isMatch(EventsData events){
      return chiikiArea.equals(events.area);
    }
}