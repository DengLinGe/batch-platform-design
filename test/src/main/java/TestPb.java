import com.kevin.domain.Msg;

/**
 * @Author: Deng.
 * @Description:
 * @Date Created in 2025 -02 -13 0:16
 * @Modified By:
 */
public class TestPb {
    public static void main(String[] args) {

        System.out.println(Msg.MsgBody.newBuilder().setChannelId("1").build());


    }
}
