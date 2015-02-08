<%-- 
    Document   : loggedin
    Created on : Feb 6, 2015, 6:40:37 PM
    Author     : Arihant
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="message"%>

<%-- any content can be specified here e.g.: --%>
<%if(null != session.getAttribute("t")){%><jsp:doBody/><%}%>