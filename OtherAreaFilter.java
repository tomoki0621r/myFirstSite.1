/**
* eventsの中のOtherAreaFilterを参照して、otherChiikiの数値に一致するその他地域があるかを探し一致しているものを返す。
* 返されたその他地域はisMatchメソッドの中で保管され、EventFilterクラスに移行する。
*/
public class OtherAreaFilter implements EventFilter{
    
    private String otherChiiki;
    
    public OtherAreaFilter(String otherChiiki){
      this.otherChiiki = otherChiiki;
    }
    
    public boolean isMatch(EventsData events){
      return otherChiiki.equals(events.sonota);
    }
}