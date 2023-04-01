package model.user;

public class Admin extends User {
    private static Admin admin;

    private Admin(String username,String emailAddress,String phoneNumber,String password){
        super(username,emailAddress,phoneNumber,password);
    }

    public static Admin getAdmin() {
        if(admin == null){
            admin = new Admin("admin","admin@gmail.com","09123456789","admin1382");
        }
        return admin;
    }
}
