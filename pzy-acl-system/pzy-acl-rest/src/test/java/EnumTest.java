import org.pzy.archetypesystem.base.module.comm.enums.WinterLogType;
import org.testng.annotations.Test;

/**
 * EnumTest
 *
 * @author pan
 * @date 4/11/20
 */
public class EnumTest {

    @Test
    public void test(){
        System.out.println(Enum.valueOf(WinterLogType.class,"").getCode());
        System.out.println(WinterLogType.Operate.getCode());
    }
}
