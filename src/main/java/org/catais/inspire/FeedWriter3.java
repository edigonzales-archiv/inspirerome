package org.catais.inspire;

import com.sun.syndication.feed.atom.Entry;
import com.sun.syndication.feed.atom.Feed;
import com.sun.syndication.feed.atom.Content;
import com.sun.syndication.feed.atom.Link;
import com.sun.syndication.feed.atom.Person;
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


public class FeedWriter3 {
	
	public FeedWriter3() throws FeedException, IOException {
		
		String uuid_dummy = "f7d54771-f7bb-42ac-8a44-4ea37b012c73";
		
		// Dataset Feed
	    Feed atomFeed = new Feed("ATOM1.0");        
	    atomFeed.setTitle("Amtliche Vermessung der Gemeinde XXXXX (BfS-Nr.) Kanton Solothurn"); // human readable
	    Content subtitle = new Content();
	    subtitle.setValue("Ich bin der Subtitle"); // human readable
	    atomFeed.setSubtitle(subtitle);
	    
        ArrayList links = new ArrayList();

        Link link = new Link();
        link.setRel("describedby");
        link.setHreflang("de");
        // Zeigt auf geocat.ch CSW: Amtliche Vermessung Liegenschaften Schweiz.
        // Die Daten liegen im ITF komplett vor. Im CSW sind sie einzeln? Wie löst man das?
        link.setHref("http://www.geocat.ch/geonetwork/srv/ger/csw?service=CSW&request=GetRecordById&version=2.0.2&ElementSetName=full&outputFormat=application/xml&outputSchema=http://www.isotc211.org/2005/gmd&Id=ab7a03e2-4bdd-4a49-bd92-4b0028bfcd51");
        links.add(link);

        Link link2 = new Link();
        link2.setRel("self");
        link.setHreflang("de");        
        link2.setType("application/atom+xml");
        link2.setHref("http://www.catais.org/atomfeeds/data_"+uuid_dummy+".xml");
        links.add(link2);

        Link link3 = new Link();
        link3.setRel("search");
        link3.setType("application/opensearchdescription+xml");
        link3.setTitle("Open Search Description for XYZ download service");
        link3.setHref("http://www.catais.org/search/opensearchdescription.xml");
        links.add(link3);        
        
        Link link4 = new Link();
        link4.setRel("up");
        link4.setHref("http://www.catais.org/atomfeeds/service.xml");
        links.add(link4);

        atomFeed.setOtherLinks(links);
        
	    atomFeed.setUpdated(new Date()); // last update (of feed???? sagt TG Req 24 -> nur logisch falls fedd == data interpretiert wird)
	    
	    Person author = new Person();
	    author.setName("Amt für Geoinformation Kanton Solothurn");
	    author.setEmail("agi@bd.so.ch");
	    
	    atomFeed.setAuthors(Arrays.asList(author));
        
	    atomFeed.setId("http://www.catais.org/atomfeeds/data_"+uuid_dummy+".xml"); // http uri zu sich selber

	    atomFeed.setRights("Kategorie A. Bla bla Nutzungsbedingungen..."); // rights or restrictions
	    
	    
	    
        Document atomXml = new Atom10Generator().generate(atomFeed);
        
        // print JDOM-Document to System.out
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(atomXml, System.out);                


	}
	

}
