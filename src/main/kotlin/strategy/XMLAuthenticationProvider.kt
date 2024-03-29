package strategy

import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathFactory
import org.w3c.dom.Document

class XMLAuthenticationProvider : IAuthenticationStrategy {
    private val rolXPath = "Users/User[@userName='%s' and @password='%s']/@rol"

    override fun authenticate(userName: String?, passwrd: String?): Principal? {
        var file: InputStream? = null
        return try {
            file = javaClass.classLoader.getResourceAsStream("strategy/UserFile.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()
            val builder = builderFactory.newDocumentBuilder()
            val xmlDocument: Document = builder.parse(file)
            val xPath: XPath = XPathFactory.newInstance().newXPath()
            val xpath = java.lang.String.format(rolXPath, userName, passwrd)
            val rol: String = xPath.compile(xpath).evaluate(xmlDocument)
            if (rol != null && !rol.isEmpty()) {
                Principal(userName!!, rol)
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } finally {
            try {
                file?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
