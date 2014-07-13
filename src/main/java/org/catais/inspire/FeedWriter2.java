package org.catais.inspire;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.module.Module;
import com.sun.syndication.feed.module.georss.GeoRSSModule;
import com.sun.syndication.feed.module.georss.SimpleModuleImpl;
import com.sun.syndication.feed.module.georss.geometries.Envelope;
import com.sun.syndication.feed.synd.SyndLink;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.impl.Atom10Generator;
import de.weichand.inspire.inspirerome.types.SpatialDatasetIdentifier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class FeedWriter2 {
	
	public FeedWriter2() throws FeedException, IOException {
	    Feed atomFeed = new Feed("ATOM1.0");        
	    atomFeed.setTitle("INSPIRE Download Service (predefined ATOM) für Geodaten des Kantons Solothurn");
	    Content subtitle = new Content();
	    subtitle.setValue("Ich bin der Subtitle");
	    atomFeed.setSubtitle(subtitle);
	    atomFeed.setUpdated(new Date());
	    
        ArrayList links = new ArrayList();

        Link link = new Link();
        link.setHref("http://csw.bgr.de/deegree-webservices/services?service=CSW&request=GetRecordById&version=2.0.2&ElementSetName=full&outputFormat=application/xml&outputSchema=http://www.isotc211.org/2005/gmd&Id=http://www.bgr.de/app/Produktblatt/show.php?productid=DE-SV-0997");
        links.add(link);
        
        link = new Link();
        link.setRel("self");
        link.setHreflang("de");
        link.setType("application/atom+xml");
        link.setHref("http://www.catais.org/atomfeeds/service.xml");
        links.add(link);

        atomFeed.setOtherLinks(links);

	    
	    
	    
        Document atomXml = new Atom10Generator().generate(atomFeed);
        
        // print JDOM-Document to System.out
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(atomXml, System.out);                


	}
	

}
