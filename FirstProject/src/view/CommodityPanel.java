package view;

import model.commodity.Commodity;
import model.user.Admin;

import java.util.Scanner;

public class CommodityPanel {
    Admin admin = Admin.getAdmin();
    private Scanner scanner;
    private int pages = (int) Math.ceil(admin.getCommoditylistLen() / 9.0);

    public CommodityPanel(){
        scanner = new Scanner(System.in);
        showPage(1);
    }
    public void showPage(int pageNumber){
        Commodity[][] page = new Commodity[3][3];
        int commodityIndex = (pageNumber - 1) * 9;

        for (int i=0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if(admin.getCommodity(commodityIndex) != null) {
                    page[i][j] = admin.getCommodity(commodityIndex);
                    commodityIndex++;
                }
            }
        }

        System.out.printf("\n%-100s","*******************************************************************************************************\n");
        for (int i = 0; i < 3; i++) {
            int k = 0;
            for (int j = 0; j < 3; j++) {
                if(j == 0){
                    System.out.printf("*");
                }
                if(page[i][j] != null){
                    if(k == 0){
                        System.out.printf(" Name:  %-25s",page[i][j].getName());
                    }else if(k == 1){
                        System.out.printf(" Price: %-25.2f",page[i][j].getPrice());
                    }else if(k == 2){
                        System.out.printf(" Score: %-25.1f",page[i][j].getAveScore());
                    }else if(k == 3) {
                        System.out.printf(" ID:    %-25s",page[i][j].getID());
                    }else if(k == 4){
                        if(page[i][j].getStock() == 0){
                            System.out.printf("%-33s","out of stock!");
                        }else {
                            System.out.printf("%-33s"," ");
                        }
                    }
                }else {
                    System.out.printf("%-33s"," ");
                }
                System.out.printf("*");
                if(j == 2 && k != 4){
                    System.out.println();
                    k++;
                    j = -1;
                }
            }
            System.out.printf("\n%-100s","*******************************************************************************************************\n");
        }

        System.out.printf("[1] Search       [2] Filter      ");
        if(pageNumber != 1){
            System.out.println("[3] Prev        ");
        }
        if(pageNumber < pages){
            System.out.printf("[4] Next");
        }
        System.out.printf("\n>>");
        int command = scanner.nextInt();

        switch (command){
            case 1:
            case 2:
            case 3:
                showPage(--pageNumber);
                break;
            case 4:
                showPage(++pageNumber);
                break;
        }
    }
}
