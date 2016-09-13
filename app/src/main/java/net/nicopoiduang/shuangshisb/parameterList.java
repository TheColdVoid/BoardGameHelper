package net.nicopoiduang.shuangshisb;

import java.util.ArrayList;

/**
 * Created by The_Void on 2016/9/6.
 */
public class parameterList {
    public ArrayList<parameter> elements=new ArrayList<parameter>();
    public parameterList(String postData)
    {
        while(postData.indexOf("=")!=-1)
        {
            System.out.println("debug"+postData);
            if(postData.indexOf("&")==-1)
            {
                elements.add(new parameter(postData.substring
                        (0,postData.indexOf("=")),postData.substring(
                        postData.indexOf("=")+1,postData.length())));
                postData="";

            }
            else
            {
                elements.add(new parameter(postData.substring
                        (0,postData.indexOf("=")),postData.substring(
                        postData.indexOf("=")+1,postData.indexOf("&"))));
                postData=postData.substring(postData.indexOf("&")+1,postData.length());
            }
        }
    }
}
