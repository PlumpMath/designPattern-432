package spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by Administrator on 2017/4/10.
 */
public class TestDefaultListableBeanFactory {

    public static void main(String[] args) {
        //定位
        ClassPathResource classPathResource = new ClassPathResource("beans.xml");
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        //解析 + 注册
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(classPathResource);
        System.out.println("numbers: " + defaultListableBeanFactory.getBeanDefinitionCount());
        ((Person)defaultListableBeanFactory.getBean("person")).work();

        //一步到位
        ApplicationContext applicationContext = new FileSystemXmlApplicationContext("classpath:beans.xml");
    }

}
