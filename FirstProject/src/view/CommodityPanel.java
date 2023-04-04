package view;

import control.CommodityControl;
import model.commodity.Commodity;
import model.user.Admin;

import java.util.Scanner;

public class CommodityPanel {
    private Admin admin = Admin.getAdmin();
    private CommodityControl commodityControl;
    private Scanner scanner;
    private int pages = (int) Math.ceil(admin.getCommoditylistLen() / 9.0);

    public CommodityPanel(){
        scanner = new Scanner(System.in);
        commodityControl = new CommodityControl();
        showPage(1);
    }
    public void showPage(int pageNumber){
        Commodity[][] page = new Commodity[3][3];
        int commodityIndex = (pageNumber - 1) * 9;

        commodityControl.fillSeed();
        for (int i=0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if((commodityControl.getSeed(commodityIndex)) != null) {
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

        System.out.printf("[1] Search       [2] Shop by Department      [3] Filter      ");
        if(pageNumber != 1){
            System.out.println("[4] Prev        ");
        }
        if(pageNumber < pages){
            System.out.printf("[5] Next");
        }
        System.out.printf("\n>>");
        int command = scanner.nextInt();

        switch (command){
            case 1:
            case 2:
                shopByDepartment();
                break;
            case 3:
                filter();
                break;
            case 4:
                showPage(--pageNumber);
                break;
            case 5:
                showPage(++pageNumber);
                break;
        }
    }

    public void shopByDepartment(){
        scanner.nextLine();
        System.out.printf("1.Electronics [%s]\n2.Vehicles [%s]\n3.Stationery [%s]\n4.Foods [%s]\n5.Show results\n>>",
                (commodityControl.isElectronicFilter()) ? "+" : " ",
                (commodityControl.isVehicleFilter()) ? "+" : " ",
                (commodityControl.isStationeryFilter()) ? "+" : " ",
                (commodityControl.isFoodFilter()) ? "+" : " ");

        int input = scanner.nextInt();
        int input2;

        switch (input){
            case 1:
                System.out.printf("1.Personal computers [%s]\n2.Data storages[%s]\n3.All Electronics [%s]\n>>",
                        (commodityControl.isPcFilter()) ? "+" : " " ,
                        (commodityControl.isDataStorageFilter()) ? "+" : " ",
                        (commodityControl.isElectronicFilter()) ? "+" : " " );

                input2 = scanner.nextInt();
                switch (input2){
                    case 1:
                        commodityControl.setPcFilter(!commodityControl.isPcFilter());
                        shopByDepartment();
                        break;
                    case 2:
                        commodityControl.setDataStorageFilter(!commodityControl.isDataStorageFilter());
                        shopByDepartment();
                        break;
                    case 3:
                        commodityControl.setElectronicFilter(!commodityControl.isElectronicFilter());
                        shopByDepartment();
                        break;
                }

            case 2:
                System.out.printf("1.Cars [%s]\n2.Bicycles [%s]\n3.All Vehicles\n>>",
                        (commodityControl.isCarFilter()) ? "+" : " " ,
                        (commodityControl.isBicycleFilter()) ? "+" : " ",
                        (commodityControl.isVehicleFilter()) ? "+" : " " );

                input2 = scanner.nextInt();
                switch (input2){
                    case 1:
                        commodityControl.setCarFilter(!commodityControl.isCarFilter());
                        shopByDepartment();
                        break;
                    case 2:
                        commodityControl.setBicycleFilter(!commodityControl.isBicycleFilter());
                        shopByDepartment();
                        break;
                    case 3:
                        commodityControl.setVehicleFilter(!commodityControl.isVehicleFilter());
                        shopByDepartment();
                        break;
                }

            case 3:
                System.out.printf("1.NoteBooks [%s]\n2.Pens [%s]\n3.Pencils [%s]\n4.All Stationery [%s]\n>>",
                        (commodityControl.isNoteBookFilter()) ? "+" : " " ,
                        (commodityControl.isPenFilter()) ? "+" : " ",
                        (commodityControl.isPencilFilter()) ? "+" : " " ,
                        (commodityControl.isStationeryFilter()) ? "+" : " " );

                input2 = scanner.nextInt();
                switch (input2){
                    case 1:
                        commodityControl.setNoteBookFilter(!commodityControl.isNoteBookFilter());
                        shopByDepartment();
                        break;
                    case 2:
                        commodityControl.setPenFilter(!commodityControl.isPenFilter());
                        shopByDepartment();
                        break;
                    case 3:
                        commodityControl.setPencilFilter(!commodityControl.isPencilFilter());
                        shopByDepartment();
                        break;
                    case 4:
                        commodityControl.setStationeryFilter(!commodityControl.isStationeryFilter());
                        shopByDepartment();
                        break;
                }

            case 4:
                commodityControl.setFoodFilter(!commodityControl.isFoodFilter());
                shopByDepartment();
                break;

            case 5:
                showPage(1);
                break;
        }
    }
    public void filter(){
        scanner.nextLine();
        System.out.println("[]");
    }
}
