package util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Alex on 5/8/2017.
 */
public class ExportXmlUtil {


    public static Document toDocument(ResultSet rs) throws ParserConfigurationException,SQLException{

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element results = doc.createElement("Results");
        doc.appendChild(results);
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        while (rs.next())
        {
            Element row = doc.createElement("Row");
            results.appendChild(row);

            for (int i = 1; i <= colCount; i++)
            {
                String columnName = rsmd.getColumnName(i);
                Object value = rs.getObject(i);

                Element node = doc.createElement(columnName);
                node.appendChild(doc.createTextNode(value.toString()));
                row.appendChild(node);
            }
        }
        return doc;

    }

    public static String serialize(Document doc) throws IOException
    {
        StringWriter writer = new StringWriter();
        OutputFormat format = new OutputFormat();
        format.setIndenting(true);

        XMLSerializer serializer = new XMLSerializer(writer, format);
        serializer.serialize(doc);

        return writer.getBuffer().toString();
    }

}
