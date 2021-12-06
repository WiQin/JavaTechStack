import com.demo.DemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/07/13
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mvc.xml");
        DemoService service = (DemoService) context.getBean("demoService");

        String result = service.doSomething("调用dubbo服务");
        System.out.println(result);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
