/**
* eventsの中のprefecturesを参照して、kenの数値に一致する都道府県があるかを探し一致しているものを返す。
* 返された都道府県はisMatchメソッドの中で保管され、EventFilterクラスに移行する。
*/
public class PrefecturesFilter implements EventFilter{
    
    private String ken;
    
    public PrefecturesFilter(String ken){
      this.ken = ken;
    }
    
    public boolean isMatch(EventsData events){
      return ken.equals(events.prefectures);
    }
}