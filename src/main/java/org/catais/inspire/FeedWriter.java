package org.catais.inspire;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedOutput;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FeedWriter {
	
	private static final DateFormat DATE_PARSER = new SimpleDateFormat("yyyy-MM-dd");

	
	public FeedWriter() throws FeedException, IOException {
		
		SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("atom_1.0");
        
        feed.setTitle("Sample Feed (created with ROME)");
        
        ArrayList links = new ArrayList();

        SyndLink link = new SyndLinkImpl();
        link.setHref("http://csw.bgr.de/deegree-webservices/services?service=CSW&request=GetRecordById&version=2.0.2&ElementSetName=full&outputFormat=application/xml&outputSchema=http://www.isotc211.org/2005/gmd&Id=http://www.bgr.de/app/Produktblatt/show.php?productid=DE-SV-0997");
        links.add(link);
        
        link = new SyndLinkImpl();
        link.setRel("self");
        link.setHreflang("de");
        link.setType("application/atom+xml");
        link.setHref("http://www.catais.org/atomfeeds/service.xml");
        links.add(link);

        feed.setLinks(links);
        

        
        feed.setDescription("This feed has been created using ROME (Java syndication utilities");
        
        
        
        
        SyndFeedOutput output = new SyndFeedOutput();
        Writer writer = new FileWriter("/home/stefan/tmp/myfeed.xml");
        output.output(feed, writer, true);
//        output.output(feed,writer);
		
	}

}
