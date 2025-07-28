import java.util.ArrayList;
import java.util.Objects;

public class Memory {
    private static ArrayList<User> arrayList = new ArrayList<>();
    private static final String host = "localhost";
    private static final int port = 8345;


    public void add(User u){
        boolean status = false;
        for (User x: arrayList){
            if(x.equals(u)){
                status = true;
            } else {
                status = false;
            }
        }
        if (!(status)) {
            arrayList.add(u);
        }
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void print(){
        for (User u: arrayList) {
            System.out.println(u);
        }
    }

    public boolean checkUser(String login, String password){
        for (User x: arrayList){
            if (Objects.equals(x.getLogin(), login) && Objects.equals(x.getPassword(), password)){
                return true;
            }
        }
        return false;

    }
    public boolean checkUser(User u){
        if (arrayList.contains(u)){
            return true;
        }
        return false;

    }
    public int getSize(){
        return arrayList.size();
    }
    public void remove(User u){
        arrayList.remove(u);
    }
}
