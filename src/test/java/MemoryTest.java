import org.junit.*;
import org.junit.jupiter.api.Assertions;

public class MemoryTest {



    @Test
    public void checkUser(){
        Memory memory = new Memory();
        memory.add(new User("1","1"));
        boolean result = memory.checkUser(new User("1","1"));
        System.out.println(result);
        Assertions.assertEquals(true,result);
    }

}
