SSL toolchain bypasser
=========================

This is a simple jar that can be provided to georchestra's tomcats, to redefine the SSL trustchain java mechanisms, so that every SSL certificates will end up trusted. This is a big security concern, but might help you to get rid of trusting issues. This should probably not be used in production, or at your own risks.

The project provides a servlet, so that it can be easily activated in a webapp, by adding this piece of code into your `web.xml` and include the jar into the underlying  `WEB-INF/lib`:


```xml
    <servlet>
        <servlet-name>sslbypasser</servlet-name>
        <servlet-class>org.georchestra.sslbypasser.SslBypasserServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

```
