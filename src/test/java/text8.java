import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class text8 {
    public static void main(String[] args) {
        AccountManager accountManager = new AccountManager();
        System.out.println(accountManager);
    }
}
class AccountManager{
    private Map<String,Integer> accountTotals=new HashMap<String,Integer>();
    private int rtirmontfund;
    public int getBalance(String accountName){

        Integer total =(Integer) accountTotals.get(accountName);
        if (total==null){
            total=Integer.valueOf(0);
        }
        return total.intValue();
    }
    public void setBalance(String accountName,int account){
//        accountTotals.put(accountName,Integer.valueOf(account));
        accountTotals.put(accountName,account);
    }

}
