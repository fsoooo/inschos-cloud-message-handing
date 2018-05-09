import com.inschos.cloud.message.handing.extend.aliyun.sms.AliyunSms;
import org.junit.Test;

/**
 * Created by IceAnt on 2018/5/4.
 */
public class ParseTest {

    @Test
    public void test1(){
        String ss = "asdas${code}asdasd${name}asad";

    }

    @Test
    public void testSms(){
        AliyunSms aliyunSms = new AliyunSms("LTAIsNwfH66NoX4W", "TbMmKE0bbnzWRbUrxpKjgDhzTgYs6a");
        aliyunSms.sendSms("天眼互联","SMS_133964015","{\"code\":\"123456\"}",null,"15101691357");
    }
}
