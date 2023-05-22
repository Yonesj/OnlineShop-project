package control;

import model.commodity.subclasses.*;
import model.connectors.*;
import model.user.*;
import model.commodity.*;
import view.AdminPanel;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AdminControl {
    static int count = 0;
    private static Admin admin = Admin.getAdmin();
    public static String processCommand(String command){
        String[] subString = command.split("--");

        switch (subString[0].trim()){
            case "Add":
                String name = subString[2];
                double price = Double.parseDouble(subString[3].trim());
                int stock = Integer.parseInt(subString[4].trim());

                switch (subString[1].trim()){
                    case "car":
                        String company = subString[5];
                        double engineVolume = Double.parseDouble(subString[6].trim());
                        boolean isAuto = Boolean.parseBoolean(subString[7]);
                        Car car = new Car(name,price,stock,company,engineVolume,isAuto);
                        admin.addCommodity(car);
                        return "task completed successfully";

                    case "bicycle":
                        String companyB = subString[5];
                        BicycleType bicycleType;
                        switch (subString[6].trim()){
                            case "hybryd":
                                bicycleType = BicycleType.HYBRYD;
                                break;
                            case "road":
                                bicycleType = BicycleType.ROAD;
                                break;
                            case "mountain":
                                bicycleType = BicycleType.MOUNTAIN;
                                break;
                            case "city":
                                bicycleType = BicycleType.CITY;
                                break;
                            default:
                                return "invalid arguman [6],use Help";
                        }
                        Bicycle bicycle = new Bicycle(name,price,stock,companyB,bicycleType);
                        admin.addCommodity(bicycle);
                        return "task completed successfully";

                    case "pc":
                        double pcWeight = Double.parseDouble(subString[5].trim());
                        String pcSize = subString[6];
                        String cpuModel = subString[7];
                        int ramMemory = Integer.parseInt(subString[8].trim());
                        PersonalComputer pc = new PersonalComputer(name,price,stock,pcWeight,pcSize,cpuModel,ramMemory);
                        admin.addCommodity(pc);
                        return "task completed successfully";

                    case "usb":
                        double usbWeight = Double.parseDouble(subString[5].trim());
                        String usbSize = subString[6];
                        String usbCapacity = subString[7];
                        String usbVersion = subString[8];
                        USB usb = new USB(name,price,stock,usbWeight,usbSize,usbCapacity,usbVersion);
                        admin.addCommodity(usb);
                        return "task completed successfully";

                    case "ssd":
                        double ssdWeight = Double.parseDouble(subString[5].trim());
                        String ssdSize = subString[6];
                        String ssdCapacity = subString[7];
                        double readingSpeed = Double.parseDouble(subString[8].trim());
                        double writingSpeed = Double.parseDouble(subString[9].trim());
                        SSD ssd = new SSD(name,price,stock,ssdWeight,ssdSize,ssdCapacity,readingSpeed,writingSpeed);
                        admin.addCommodity(ssd);
                        return "task completed successfully";

                    case "notebook":
                        String madeIN1 = subString[5];
                        int sheets = Integer.parseInt(subString[6].trim());
                        String paperType = subString[7];
                        NoteBook noteBook = new NoteBook(name,price,stock,madeIN1,sheets,paperType);
                        admin.addCommodity(noteBook);
                        return "task completed successfully";

                    case "pen":
                        String madeIN2 = subString[5];
                        String color = subString[6];
                        Pen pen = new Pen(name,price,stock,madeIN2,color);
                        admin.addCommodity(pen);
                        return "task completed successfully";

                    case "pencil":
                        String madeIN3 = subString[5];
                        PencilType pencilType;
                        switch (subString[6].trim()){
                            case "HB":
                                pencilType = PencilType.HB;
                                break;
                            case "H":
                                pencilType = PencilType.H;
                                break;
                            case "B":
                                pencilType = PencilType.B;
                                break;
                            case "2H":
                                pencilType = PencilType.H2;
                                break;
                            case "F":
                                pencilType = PencilType.F;
                                break;
                            default:
                                return "invalid arguman [6] , use Help";
                        }
                        Pencil pencil = new Pencil(name,price,stock,madeIN3,pencilType);
                        admin.addCommodity(pencil);
                        return "task completed successfully";

                    case "food":
                        String manufactureDate = subString[5];
                        String expirationDate = subString[6];
                        Food food = new Food(name,price,stock,manufactureDate,expirationDate);
                        admin.addCommodity(food);
                        return "task completed successfully";

                    default:
                        return "invalid arguman [1],use Help";
                }

            case "Edit":
                String id = subString[1];
                String newName = subString[2];
                double newPrice = Double.parseDouble(subString[3].trim());
                int newStock = Integer.parseInt(subString[4].trim());
                boolean found = false;
                for (Commodity commodity: admin.getCommodityList()){
                    if(commodity.getID().equals(id)){
                        found = true;
                        if(!newName.equals("0")){
                            commodity.setName(newName);
                        }
                        if(newPrice != 0){
                            commodity.setPrice(newPrice);
                        }
                        if(newStock != 0){
                            commodity.setStock(newStock);
                        }
                    }
                }
                if(found){
                    return "task completed successfully";
                }else {
                    return "no commodity with this id exist in list!";
                }


            case "Remove":
                String ID = subString[1];
                boolean found2 = false;
                for (Commodity commodity: admin.getCommodityList()){
                    if(commodity.getID().equals(ID)){
                        admin.removeCom(commodity);
                        found2 = true;
                        break;
                    }
                }
                if(found2){
                    return "task completed successfully";
                }else {
                    return "no commodity with this id exist in list!";
                }


            case "View":
                switch (subString[1].trim()){
                    case "users":
                        StringBuilder viewUsers = new StringBuilder();
                        for (Customer customer: CustomerControl.getCustomers()) {
                            viewUsers.append(customer.toString());
                        }
                        return viewUsers.toString();

                    case "requests":
//                        int counts = 1;
                        StringBuilder viewRequests = new StringBuilder();
                        for (Request request: admin.getRequests()) {
                            viewRequests.append("\n#" + count + " ");
                            count++;
                            viewRequests.append(request.toString());
                        }
                        return viewRequests.toString();

                    case "discounts":
                        StringBuilder viewDiscounts = new StringBuilder();
                        for (Discount discount: admin.getDiscounts()){
                            viewDiscounts.append(discount.toString() + "\n");
                        }
                        return viewDiscounts.toString();

                    default:
                        return "invalid arguman [1],use help";
                }

            case "ManageRequest":
                int index = Integer.parseInt(subString[1].trim());
                boolean isAccepted;

                if(subString[2].equals("accept")){
                    isAccepted = true;
                }else if(subString[2].equals("reject")){
                    isAccepted = false;
                }else {
                    return "invalid arguman [2] ,use help";
                }

                if(index >= 1 && index <= admin.getRequestLen()){
                    Request request;
                    if(admin.getRequest(index) != null) {
                        request = admin.getRequest(index);
                    }else {
                        return "null pointer!";
                    }

                    if(request.getRequestType() == RequestType.SIGNIN){
                        if(isAccepted) {
                            CustomerControl.addCustomer(request.getCustomer());
                        }
                        admin.removeReq(index);
                        return "";
                    }else if(request.getRequestType() == RequestType.COMMENT){
                        if(isAccepted){
                            request.getComment().setStatus(Status.ACCEPTED);
                            Commodity hold = request.getCommodity();
                            hold.addComment(request.getComment());
                        }else {
                            request.getComment().setStatus(Status.REJECTED);
                        }
                        admin.removeReq(index);
                        return "";

                    }else if(request.getRequestType() == RequestType.INCRESECREDIT){
                        if(isAccepted){
                            Customer hold = request.getCustomer();
                            hold.setCredit(hold.getCredit() + request.getAmount());
                        }
                        admin.removeReq(index);
                        return "";
                    }

                }else {
                    return  "out of bound error!";
                }

            case "AddDiscount":
                double percent = Double.parseDouble(subString[1].trim());
                int capacity = Integer.parseInt(subString[2].trim());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDate date = LocalDate.parse(subString[3],formatter);

                Discount discount = new Discount(percent,capacity,date);
                admin.addDiscount(discount);
                return "Discount added successfully";

            case "GiftDiscount":
                String code = subString[1].trim();

                boolean isfound = false;
                Discount discountHolder = null;
                for (Discount dizz : admin.getDiscounts()){
                    if(dizz.getCode().equals(code)){
                        discountHolder = dizz;
                        isfound = true;
                        break;
                    }
                }
                if(!isfound){
                    return "no discount with this code found";
                }

                switch (subString[2].trim()){
                    case "firstPurchase":
                        for (Customer customer : CustomerControl.getCustomers()){
                            if(customer.getShoppinHistory().size() == 1){
                                customer.addDiscount(discountHolder);
                            }
                        }
                        return "Discount added successfully";

                    case "loyalCustomers":
                        for (Customer customer : CustomerControl.getCustomers()){
                            if(customer.getShoppinHistory().size() > 5){
                                customer.addDiscount(discountHolder);
                            }
                        }
                        return "Discount added successfully";

                    case "bestCustomers":
                        for (Customer customer : CustomerControl.getCustomers()){
                            for (Invoice invoice : customer.getShoppinHistory()){
                                if(invoice.getAmount() > 2000){
                                    customer.addDiscount(discountHolder);
                                    break;
                                }
                            }
                        }
                        return "Discount added successfully";

                    default:
                        return "invalid arguman,use help";
                }

            case "help":
                return String.format("%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%s\n\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                        "COMMAND","FUNCTION",
                        "Add","Add a product to the shop.",
                        "Edit","Edit information of a product.",
                        "help","Provides Help information for Admin commands.",
                        "Help","provides specific information about Add command.",
                        "Logout","Call the main panel.",
                        "ManageRequest","Mange user s requests.",
                        "Remove","Remove a product from the shop.",
                        "View","Display information of users or requests.",
                        "AddDiscount","Add a product to the shop.",
                        "How to use:",
                        "Add             [department] [name] [price] [stock] [special argumans]",
                        "Edit            [id] [new name] [new price] [new stock] (put 0 for those you dont want to chang)",
                        "Remove          [id]",
                        "View            [users / requests]",
                        "MangeRequest    [index] [accept / reject]",
                        "AddDiscount     [percent] [capacity] [expiration date]");

            case "Help":
                return String.format("\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                        "Add car       [name] [price] [stock] [company name] [engine volume] [is it auto]",
                        "Add bicycle   [name] [price] [stock] [company name] [bicycle Type]",
                        "Add pc        [name] [price] [stock] [weight] [size] [cpu model] [ram memory]",
                        "Add usb       [name] [price] [stock] [weight] [size] [capacity] [usb version]",
                        "Add ssd       [name] [price] [stock] [weight] [size] [capacity] [reading speed] [writing speed]",
                        "Add notebook  [name] [price] [stock] [Made in] [sheets] [paper type]",
                        "Add pen       [name] [price] [stock] [Made in] [color]",
                        "Add pencil    [name] [price] [stock] [Made in] [pencil type]",
                        "Add food      [name] [price] [stock] [manufacture Date] [expiration Date]");

            default:
                return "invalid command,use help";
        }
    }

    public static void loggin(String inputUsername,String inputPassword){
        if(inputUsername.equals("admin") && inputPassword.equals("admin")){
            System.out.println("\nwelcome to Admin panel!");
            AdminPanel.adminPage();
        }
    }

}
