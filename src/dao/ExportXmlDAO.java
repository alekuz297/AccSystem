package dao;

import org.w3c.dom.Document;
import util.DBUtil;
import util.ExportXmlUtil;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Alex on 5/8/2017.
 */
public class ExportXmlDAO {

    public Document getAccount_NEList()
    {  Document doc = null;
        try
        {
           // Statement stmt = conn.createStatement();
            ResultSet rs = DBUtil.dbExecuteQuery("select * from export_xml;");

            doc = ExportXmlUtil.toDocument(rs);

            rs.close();
           // stmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return doc;

    }

}
