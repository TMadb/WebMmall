package advanced.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLTest {


    public static void main(String[] args) {
        Test();
    }

    public static void Test(){
        //文件读取流
        InputStream in = XMLTest.class.getResourceAsStream("/Test.xml");
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(in);
            Element rootElement = doc.getRootElement();
            List<Element> element = rootElement.elements();
            List<Element> elements = null;
            for(Element e : element){
                System.out.println(e.getName());
                elements = e.elements();
                for(Element element1:elements){
                    System.out.println(element1.getText());
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
