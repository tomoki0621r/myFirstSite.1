/**
* 全てのFilterクラスに対応したインタフェースを作成。
*/
public interface EventFilter{
    boolean isMatch(EventsData events);
}