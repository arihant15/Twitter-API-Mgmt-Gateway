/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arihant15;
/**
 *
 * @author Arihant
 */

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.IDs;
import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;


@Controller
public class ActionServlet extends HttpServlet {
    
    String ConsumerKey = "Enter Consumer Key";
    String ConsumerSecret = "Enter Consumer Secret";
    
    @RequestMapping("/login.arihant15")
    public void doLogin(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {            
            ConfigurationBuilder configuration = new ConfigurationBuilder();
            configuration.setOAuthConsumerKey(ConsumerKey);
            configuration.setOAuthConsumerSecret(ConsumerSecret);
            TwitterFactory twitterfactory = new TwitterFactory(configuration.build());
            Twitter twitter = twitterfactory.getInstance();
            req.getSession().setAttribute("t",twitter);
            RequestToken requestToken = twitter.getOAuthRequestToken();
            req.getSession().setAttribute("rToken", requestToken);
            res.sendRedirect(requestToken.getAuthenticationURL());
        }
        catch(Exception e)
        { 
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/callback.arihant15")
    public void doCallback(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        try
        {
            AccessToken accessToken;
            Twitter twitter = (Twitter) req.getSession().getAttribute("t");
            RequestToken requestToken = (RequestToken) req.getSession().getAttribute("rToken");
            String verifier = req.getParameter("oauth_verifier");
            accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            req.getSession().removeAttribute("rToken");         
        }
        catch(Exception e)
        {
            (res.getWriter()).println(e);
        }
        res.sendRedirect(req.getContextPath()+"/");
    }
    
    @RequestMapping("/getTimeline.arihant15")
    public void doTimeline(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        try
        {
            Twitter twitter = (Twitter) req.getSession().getAttribute("t");
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses)
            {
                (res.getWriter()).println(status.getUser().getScreenName() + ":" + status.getText() + "\n");
            }
        }
        catch(Exception e)
        {
            (res.getWriter()).println(e);
        }
    }
    
    @RequestMapping("/postTweet.arihant15")
    public void doTweet(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        try
        {
            Twitter twitter = (Twitter) req.getSession().getAttribute("t");
            String text = req.getParameter("text");
            twitter.updateStatus(text);
            (res.getWriter()).println("Tweeted Successfully");
        }
        catch(Exception e)
        {
            (res.getWriter()).println(e);
        }
    }
    
    @RequestMapping("/getFriends.arihant15")
    public void doFriends(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        try
        {
            Twitter twitter = (Twitter) req.getSession().getAttribute("t");
            IDs ids = twitter.getFollowersIDs(-1);
            PagableResponseList<User> users;
            for(long id : ids.getIDs())
            {
                (res.getWriter()).println("@" + twitter.showUser(id).getName() + "\n");
            }
        }
        catch(Exception e)
        {
            (res.getWriter()).println(e);
        }
    }
    
    @RequestMapping("/logout.arihant15")
    public void dologout(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        try
        {
            req.getSession().invalidate();
            res.sendRedirect(req.getContextPath()+ "/");
        }
        catch(Exception e)
        {
            (res.getWriter()).println(e);
        }
    }
}
