import java.util.*;
class ATMSimulator{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int count=1;
        while(true){
            if(count>=4){
                System.out.println("User is not authendicated");
                return;
            }
            String password=sc.next();
            if(password.equals(Constant.password)){
                System.out.println("Entered Successfully");
                break;
            }else{
                System.out.println("Wrong password Try Again");
                count++;
            }
            
        }
        int option=0;
        ArrayList<Double> transactions=new ArrayList<>();
        do{
            System.out.println("===== ATM =====\n1.Check Balance\n2.Deposite\n3.Withdraw\n4.Mini statement\n5.Exit");
            try{
                option=sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Invalid input");
                sc.next();
                continue;
            }
            switch (option) {
                case 1:
                    System.out.println("Balance: "+Account.balance);
                    break;
                case 2:{
                    double amount;
                    try{
                        amount=sc.nextDouble();
                        if(amount<=0){
                            System.out.println("Invalid input");
                            continue;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input");
                        sc.next();
                        continue;
                    }
                    Account.balance+=amount;
                    transactions.add(amount);
                    System.out.println("The amount "+amount+" is Deposited");
                    break;
                }
                case 3:{
                    double amount;
                    try{
                        amount=sc.nextDouble();
                        if(amount<=0){
                            System.out.println("Invalid input");
                            continue;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Invalid input");
                        sc.next();
                        continue;
                    }
                    if(Account.balance>=amount){
                        Account.balance-=amount;
                        transactions.add(-(amount));
                        System.out.println("The amount "+amount+" is withdrawed");
                    }else{
                        System.out.println("Insufficient Balance");
                    }
                    break;
                }
                case 4:{
                    System.out.println("--------mini statement----------");
                    for(double num:transactions){
                        if(num<0){
                            System.out.println("Withdraw: "+-(num));
                        }else{
                            System.out.println("Deposite: "+num);
                        }
                    }
                    System.out.println("---------------------------------");
                    System.out.println("Balance: "+Account.balance);
                    break;
                }
                case 5:
                    System.out.println("Exiting....");
                    break;
                default:
                    System.out.println("Invalid input");
                    continue;
            }
        }while(option!=5);
        
    }
}