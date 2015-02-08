<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <title>Twitter Gateway</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <tag:notloggedin>
            <div>Twitter Gateway</div>
            <form action="/APIMgmtGW/login.arihant15" method="POST">
                <div class="formInfo">
                    <p>Click the button to connect this application with your Twitter account.</p>
                </div>
                <p><button type="submit">Connect to Twitter</button></p>
            </form>
        </tag:notloggedin>
        <tag:loggedin>    
            <h4>Welcome ${session.getAttribute("t").screenName} ${session.getAttribute("t").id}</h4>
            <div style="width: 100%;">
                
                <div style="float: left; width: 10%;">
                    <h4>
                    <a href="/APIMgmtGW/writeTweet.jsp" target="frame">Write Tweet</a><br>
                    <a href="/APIMgmtGW/getTimeline.arihant15" target="frame">Timeline</a><br>
                    <a href="/APIMgmtGW/getFriends.arihant15" target="frame">Friends</a><br>
                    
                    </h4>
                </div>
                <div style="float: left; width: 5%;"><br></div>
                <div style="float: left; width: 60%;">
                    <iframe width="100%" height="100%" name="frame">Please use a browser that supports iframes</iframe>
                </div>
                <div style="float: left; width: 5%;"><br></div>
                <div style="float: left; width: 10%;">
                    <a href="/APIMgmtGW/logout.arihant15">logout</a>
                </div>
                <br style="clear: left;" />
            </div>
            
        </tag:loggedin>
        
    </body>
    
</html>
             
