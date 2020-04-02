import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import com.opencsv.bean.CsvToBeanBuilder;

/**
 * イベントの情報をCSVファイルから読み込み、ユーザーが検索して
 * イベントの情報を確認できるアプリケーション。
 * 
 * メインメニュー(mainメソッド)
 *  →　詳細検索メニュー(selectMenuメソッド)
 * 　→　日付検索（dataMenuメソッド）
 * 　→　都道府県検索（prefecturesMenuメソッド）
 *  →　1-3月、4-6月、7-9月、10-12月のイベント検索（seasonメソッド）
 *  →　エリア検索（areaMenuメソッド）
 *  →　その他エリア検索（otherAreaMenuメソッド）
 *  →　全てのイベント一覧表示（allEventsメソッド）
 */
public class Main {
  
  /** EventsDataクラスの中のeventsがnullでないかを判定する */
  static List<EventsData> events = null;
  
  /** Scannerの変数名をscnとする */
  static Scanner scn;
  
  /**
  * メインメニュー表示画面、検索画面を共通化したメソッド。
  * 
  */
  public static void main(String[] args){
    // csvのデータをpathに代入している
    String path = "./eventsData.csv";
    
    // tryで入力時に想定外のことが起こった場合にcatchに移行し、エラー内容を表示させる
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"))) {
      CsvToBeanBuilder<EventsData> builder = new CsvToBeanBuilder<EventsData>(reader);
      builder.withType(EventsData.class);
      events = builder.build().parse();
    } catch (Exception e){
      e.printStackTrace();
    }
    
    scn = new Scanner(System.in);
    while(true){
      
      // 画面をクリアする
      System.out.print("\033[H\033[2J");  
      System.out.flush();  
      
      System.out.println("1. イベント情報詳細検索（日付or都道府県）");
      System.out.println("2. １月〜３月イベント一覧");
      System.out.println("3. ４月〜６月イベント一覧");
      System.out.println("4. ７月〜９月イベント一覧");
      System.out.println("5. １０月〜１２月イベント一覧");
      System.out.println("6. 関東・関西・東北・四国・中国・九州イベント一覧");
      System.out.println("7. その他地域イベント一覧");
      System.out.println("8. イベント一覧");
      System.out.println("9. プログラム終了");
      System.out.print("ご希望の番号を選択して下さい"+" : ");
      
      // 入力された番号に対して、番号に対応したメソッドを参照する。
      int bango = scn.nextInt();
      switch (bango) {}
      if(bango == 9){
        break;
      }else if(bango == 1){
        selectMenu();
      }else if(bango == 2){
        season(1,1,3,31);
      }else if(bango == 3){
        season(4,1,6,30);
      }else if(bango == 4){
        season(7,1,9,30);
      }else if(bango == 5){
        season(10,1,12,31);
      }else if(bango == 6){
        areaMenu();
      }else if(bango == 7){
        otherAreaMenu();
      }else if(bango == 8){
        allEvents();
      }
    }
  }
 
  /**
  * このメソッドは、日付、都道府県、での検索メニューを選択するための画面を表示する
  * それぞれのメニューの詳細については、dataMenuメソッド、prefecturesMenuメソッドを参照すること。
  */ 
  public static void selectMenu(){
    while(true){
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("1.日付を入力する");
      System.out.println("2.都道府県で検索する");
      System.out.println("3.メインメニューに戻る");
      System.out.print("ご希望の番号を選択してください"+":");
      int subSelectMenu = scn.nextInt();
      if(subSelectMenu == 3){
        break;
      } else if(subSelectMenu == 1){
        dataMenu();
      } else if(subSelectMenu == 2){
        prefecturesMenu();
      }
    }
  }
 
  /**
  * 検索処理を共通化したメソッド。
  * 引数として、EventFilterを取る。
  * この引数で、どのような検索をするのか（検索条件）を、呼出元から指定することができる。
  */ 
  public static List<EventsData> searchEvents(EventFilter filter){
    List<EventsData> selEvents = new ArrayList<EventsData>(); 
    for (EventsData a : events ){ 
      if(filter.isMatch(a)){
        selEvents.add(a);
        System.out.println("" + selEvents.size() + " : " + a.toString());
      }
    }
    return selEvents;
  }

  /**
  * イベントが表示された後の処理を共通化したメソッド。
  * イベント名と番号が表示され、希望のイベント番号を選択されると、入力されたイベントの詳細が表示される。
  * このイベント詳細は、EventsDataクラスのprintDetailメソッドを参照する。
  */
  public static void showDetail(List<EventsData> events, String returnMenu) {
    if (events.size() > 0){
      while(true){
        System.out.print("ご希望のイベント番号を入力して下さい(0で" + returnMenu + "に戻る)"+" : ");
        int inputDataMenuNo = scn.nextInt();
        if(inputDataMenuNo == 0){
          break;
        }else{
          if (inputDataMenuNo > 0 && inputDataMenuNo <= events.size()) {
            events.get(inputDataMenuNo -1).printDetail();
          }
        }
        System.out.print("2で" + returnMenu + "に戻る"+" : ");
        int subDataMenu = scn.nextInt();
        if(subDataMenu==2){
          break;
        }
      }
    }else{
      while(true){
        System.out.print("条件に合致するイベント情報が見つかりませんでした(0で" + returnMenu + "に戻る)"+" : ");
        int menu = scn.nextInt();
        if(menu==0) break;
      }
    }
  }
  
  /**
  * 年月日を入力するとその期間に当てはまるイベントが表示されるメソッド。
  * その後、共通化しているshowDetailメソッドにて検索後の処理を行う。
  */
  public static void dataMenu(){
    //画面をクリアする
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    
    //年月日それぞれを入力させる
    System.out.print("ご希望年を入力してください【2019年→→2019】"+" : ");
    int inputYear = scn.nextInt();
    System.out.print("ご希望月を入力してください【6月→→6】"+" : ");
    int inputMonth = scn.nextInt();
    System.out.print("ご希望日を入力してください【21日→→21】"+" : ");
    int inputDay = scn.nextInt();
    
    //バラバラに入力された年月日を比較しやすいように1つの整数にまとめる
    int inputYMD = inputYear*10000+inputMonth*100+inputDay;
    
    //検索処理と、詳細表示
    List<EventsData> selEvents = searchEvents(new DataFilter(inputYMD));
    showDetail(selEvents, "イベント詳細検索メニュー");
  }
  
  /**
  * 都道府県名を入力すると都道府県名が一致したイベント名を表示するメソッド。
  * その後、共通化しているshowDetailメソッドにて検索後の処理を行う。
  */
  public static void prefecturesMenu(){
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.print("お探しの都道府県名を入力して下さい"+" : ");
    String ken = scn.next();
    List<EventsData> selEvents = searchEvents(new PrefecturesFilter(ken));
    showDetail(selEvents, "イベント詳細検索メニュー");
  }
  
  /**
  * メインメニューで選択されたシーズン（月）に当てはまるイベントを表示する。
  * メインメニューの2,3,4,5番に開始日から終了日までの数値が元から設定してある。
  * sMonth＝開始月、sDay＝開始日、eMonth＝終了月、eDay＝終了日。
  * 引数としてSeasonFilterを取り、その中に変数inputS,inputEのデータを代入。
  * その後、searchEventsメソッドで検索処理を行う。(開始日と終了日の間の期間に行われているイベントだけを表示)
  * その後、共通化しているshowDetailメソッドにて検索後の処理を行う。
  */
  public static void season(int sMonth, int sDay, int eMonth, int eDay){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    
    // inputSには開始日の月日の数値が入力され、inputEには終了日の月日の数値が入力される。
    int inputS = sMonth*100+sDay;
    int inputE = eMonth*100+eDay;
    List<EventsData> selEvents = searchEvents(new SeasonFilter(inputS,inputE)); 
    showDetail(selEvents, "メインメニュー");
  }
  
  /**
  * 表示された地域名を入力すると地域名が一致したイベント名を表示するメソッド。
  * その後、共通化しているshowDetailメソッドにて検索後の処理を行う。
  */
  public static void areaMenu(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.println("関東・関西・東北・四国・中国・九州");
    System.out.print("ご希望の地域を入力して下さい"+" : ");
    String chiikiArea = scn.next();
    List<EventsData> selEvents = searchEvents(new AreaFilter(chiikiArea));
    showDetail(selEvents, "メインメニュー");
  }
 
  /**
  * 表示されたその他地域名を入力するとその他地域名が一致したイベント名を表示するメソッド。
  * その後、共通化しているshowDetailメソッドにて検索後の処理を行う。
  */
  public static void otherAreaMenu(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    System.out.print("その他地域を入力して下さい"+" : ");
    String otherChiiki= scn.next();
    List<EventsData> selEvents = searchEvents(new OtherAreaFilter(otherChiiki));
    showDetail(selEvents, "メインメニュー");
  }
  
  /**
  * eventsデータにあるイベントを全て表示させる。
  * その表示されたイベントに番号を付け、最後にEventsDataクラスのtoStringメソッドを呼び出し、表示させる。
  * その後、共通化しているshowDetailメソッドにてeventsのデータを使用���、検索後の処理を行う。
  */
  public static void allEvents(){
    System.out.print("\033[H\033[2J");  
    System.out.flush();
    for (EventsData ev : events ){
      System.out.println("" + (events.indexOf(ev) + 1) + " : " + ev.toString());
    }
    showDetail(events, "メインメニュー");
  }
}