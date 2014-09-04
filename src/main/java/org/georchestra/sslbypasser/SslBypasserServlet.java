package org.georchestra.sslbypasser;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This class offers a convenient way to disable every SSL checks inside a webapp
 * (or jvm-wide ?). This can be triggered by adding the following servlet into the
 * web.xml of the webapp:
 *
 *     <!-- SSL bypassing in effect -->
 *   <servlet>
 *       <servlet-name>sslbypasser</servlet-name>
 *       <servlet-class>org.georchestra.sslbypasser.SslBypasserServlet</servlet-class>
 *       <load-on-startup>1</load-on-startup>
 *   </servlet>
 *
 * Obviously, this is meant for testing purposes without having to fiddle with
 * keystore / truststores, and this should not be used in production.
 *
 * @author pmauduit
 *
 */
public class SslBypasserServlet extends HttpServlet {

    public void init() throws ServletException
    {
          try {
            SslBypasser.disableCertificatesCheck();
        } catch (Exception e) {}
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
              throws ServletException, IOException
    {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("SSL Bypasser - current status: " + SslBypasser.isActivated());
    }

    public void destroy()
    {
    }
}
