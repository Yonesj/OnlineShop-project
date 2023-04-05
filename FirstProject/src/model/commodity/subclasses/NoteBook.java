package model.commodity.subclasses;

public class NoteBook extends Stationery{
    private int sheets;
    private String paperType;

    public NoteBook(String name,double price,int stock,String madeIN,int sheets,String paperType){
        super(name,price,stock,madeIN);
        this.sheets = sheets;
        this.paperType = paperType;
    }

    @Override
    public String toString(){
        return String.format("%s%-20s%d%-20s%s",super.toString() , "\nSheets:" , getSheets() , "\nPaper Type:" , getPaperType());
    }
    public int getSheets() {
        return sheets;
    }

    public String getPaperType() {
        return paperType;
    }

}
