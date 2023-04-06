package view;

import control.CommodityControl;
import model.commodity.Commodity;
import model.connectors.*;
import model.user.*;

import java.util.Scanner;

public class CommodityPanel {
    private Admin admin = Admin.getAdmin();
    private Customer customer;
    private CommodityControl commodityControl;
    private Scanner scanner;
    private int pages = (int) Math.ceil(admin.getCommoditylistLen() / 9.0);

    public CommodityPanel(){
        scanner = new Scanner(System.in);
        commodityControl = new CommodityControl();
        showPage(1);
    }

    public CommodityPanel(Customer customer){
        scanner = new Scanner(System.in);
        commodityControl = new CommodityControl();
        this.customer = customer;
        showPage(1);
    }
    public void showPage(int pageNumber){
        Commodity[][] page = new Commodity[3][3];
        int commodityIndex = (pageNumber - 1) * 9;

        commodityControl.fillSeed();
        for (int i=0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if((commodityControl.getSeed(commodityIndex)) != null) {
                    page[i][j] = commodityControl.getSeed(commodityIndex);
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
            System.out.printf("[5] Next         ");
        }
        System.out.printf("[6] Exit\n>>");
        int command = scanner.nextInt();

        switch (command){
            case 1:
                search();
                break;
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
            case 6:
                MainPanel.mainPage();
                break;
        }
    }


    public void search(){
        scanner.nextLine();
        System.out.printf("Enter the ID:\n>>");
        String inputID = scanner.nextLine();

        for(Commodity commodity : admin.getCommodityList()){
            if(inputID.equals(commodity.getID())){
                System.out.println(commodity.toString());
                System.out.println("[1] back      ");
                if(customer != null){
                    System.out.println("[2]Add to the cart      [3] Comment      [4] Score");
                }

                int input = scanner.nextInt();

                switch (input){
                    case 1:
                        showPage(1);
                        break;

                    case 2:
                        addToCart(commodity);
                        break;

                    case 3:
                        comment(customer,commodity);
                        break;
                    case 4:
                }
            }
        }

        System.out.println("no product with this id found!");
        showPage(1);
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
        System.out.println("[1] Filter by Price\n[2] Filter by score\n[3] َAvailables\n[4] Reset Filters\n>>");
        int input3 = scanner.nextInt();

        switch (input3){
            case 1:
                System.out.printf("upper limit:     ");
                double upLimit = scanner.nextDouble();
                System.out.printf("lower limit:     ");
                double downLimet = scanner.nextDouble();

                commodityControl.setPriceDOWNlimit(downLimet);
                commodityControl.setPriceUPlimit(upLimit);
                showPage(1);
                break;

            case 2:
                System.out.printf("Score limit:      ");
                float scoreLimit = scanner.nextFloat();
                commodityControl.setScoreLimit(scoreLimit);
                showPage(1);
                break;

            case 3:
                commodityControl.setAvaliableFilter(true);
                showPage(1);
                break;

            case 4:
                commodityControl.setAvaliableFilter(false);
                commodityControl.setPriceUPlimit(-1);
                commodityControl.setPriceDOWNlimit(-1);
                commodityControl.setScoreLimit(0);
                showPage(1);
                break;
        }
    }


    public void addToCart(Commodity commodity){
        System.out.printf("how many item do you wanna purchase?\n>>");
        int input2 = scanner.nextInt();
        if(commodity.getStock() > input2) {
            for (int i = 0; i < input2; i++) {
                customer.AddToCart(commodity);
            }
            System.out.println("this product added to the cart successfully!");
        }else {
            System.out.println("not enough stock!");
        }

        showPage(1);
    }

    public void comment(Customer customer,Commodity commodity){
        System.out.printf("text>>");
        String inputText = scanner.nextLine();
        boolean isBuyes = false;

        for (Invoice invoice: customer.getShoppinHistory()){
            for (Commodity commodity1 : invoice.getCommodities()){
                if(commodity.getID().equals(commodity1.getID())){
                    isBuyes = true;
                    break;
                }
            }
        }

        Comment comment = new Comment(customer,commodity.getID(),inputText,isBuyes, Status.WAITING);
        Request commentRequest = new Request(customer,comment,commodity);
        admin.addRequest(commentRequest);
        System.out.println("your request has been sent to the admin!");
        showPage(1);
    }

    public void score(Customer customer,Commodity commodity){
        boolean isBuyed = false;
        for (Invoice invoice: customer.getShoppinHistory()){
            for (Commodity commodity1 : invoice.getCommodities()){
                if(commodity.getID().equals(commodity1.getID())){
                    isBuyed = true;
                }
            }
        }

        if(!isBuyed){
            System.out.println( "you have to buy product first!");
            showPage(1);
        }

        System.out.printf("Enter your score to this product [0 - 5]\n>>");
        float inputScore = scanner.nextFloat();

        Score score = new Score(customer,inputScore,commodity);
        showPage(1);
    }
}
