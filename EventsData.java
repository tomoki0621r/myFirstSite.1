/**
* それぞれの検索データのフィールドを作成。
*/
public class EventsData {
    
    String name;
    String prefectures;
    String note;
    String area;
    int startYear;
    int startMonth;
    int startDay;
    int startYMD;
    int startMD;
    int endYear;
    int endMonth;
    int endDay;
    int endYMD;
    int endMD;
    String kanto;
    String kansai;
    String tohoku;
    String shikoku;
    String chugoku;
    String kyushu;
    String sonota;
    
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrefectures(String prefectures){
        this.prefectures = prefectures;
    }
    
    public void setNote(String note){
        this.note = note;
    }
    
    public void setArea(String area){
        this.area = area;
    }
    
    public void setStartYear(int startYear){
        this.startYear = startYear;
        this.startYMD = this.startYear * 10000 + this.startMonth * 100 + this.startDay;
        this.startMD =  this.startMonth * 100 + this.startDay;
    }
    
    public void setStartMonth(int startMonth){
        this.startMonth = startMonth;
        this.startYMD = this.startYear * 10000 + this.startMonth * 100 + this.startDay;
        this.startMD =  this.startMonth * 100 + this.startDay;
    }
    
    public void setStartDay(int startDay){
        this.startDay = startDay;
        this.startYMD = this.startYear * 10000 + this.startMonth * 100 + this.startDay;
        this.startMD =  this.startMonth * 100 + this.startDay;
    }
    
    public void setEndYear(int endYear){
        this.endYear = endYear;
        this.endYMD = this.endYear * 10000 + this.endMonth * 100 + this.endDay;
        this.endMD =  this.endMonth * 100 + this.endDay;
    }
    
    public void setEndMonth(int endMonth){
        this.endMonth = endMonth;
        this.endYMD = this.endYear * 10000 + this.endMonth * 100 + this.endDay;
        this.endMD =  this.endMonth * 100 + this.endDay;
    }
    
    public void setEndDay(int endDay){
        this.endDay = endDay;
        this.endYMD = this.endYear * 10000 + this.endMonth * 100 + this.endDay;
        this.endMD =  this.endMonth * 100 + this.endDay;
    }
    
    public void setKanto(String kanto){
        this.kanto = kanto;
    }
    
    public void setKansai(String kansai){
        this.kansai = kansai;
    }
    
    public void setTohoku(String tohoku){
        this.tohoku = tohoku;
    }
    
    public void setShikoku(String shikoku){
        this.shikoku = shikoku;
    }
    
    public void setChugoku(String chugoku){
        this.chugoku = chugoku;
    }
    
    public void setKyushu(String kyushu){
        this.kyushu = kyushu;
    }
    
    public void setSonota(String sonota){
        this.sonota = sonota;
    }
    
    /**
    * イベント表示がされるときに横一列で表示できるものを共通化したメソッド。
    */
    public String toString(){
        return name + " "
         + (startYear+"/"+startMonth+"/"+startDay) + "～" + (endYear+"/"+endMonth+"/"+endDay) 
        + " (" + prefectures + ")";
    }
    
    /**
    * イベント詳細表示画面を共通化したメソッド。
    */
    public void printDetail(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
        System.out.println(
        "イベント名：" + name + "\n"
        + "期間：" + (startYear+"/"+startMonth+"/"+startDay) + "～" + (endYear+"/"+endMonth+"/"+endDay) + "\n"
        + "地域："+ area + "("+ prefectures +")"+"\n"
        + "イベント詳細：" + note 
        ); 
    }
}