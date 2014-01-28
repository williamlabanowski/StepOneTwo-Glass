package test.book.glass;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import test.book.glass.auth.AuthUtils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.mirror.Mirror;
import com.google.api.services.mirror.Mirror.Timeline;
import com.google.api.services.mirror.model.MenuItem;
import com.google.api.services.mirror.model.MenuValue;
import com.google.api.services.mirror.model.TimelineItem;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
public class StepOneTwo {

	public static String getRandomVideoName(){
		String[] vidOptions={"VideoOne","VideoTwo","VideoThree","VideoFour"};
		int choice=new Random().nextInt(vidOptions.length);
		return vidOptions[choice];
	}
	
	public static String render(ServletContext ctx, String template, Map<String, Object> data) throws IOException, ServletException{
		Configuration config = new Configuration();
		config.setServletContextForTemplateLoading(ctx, "WEB-INF/views");
		config.setDefaultEncoding("UTF-8");
		Template ftl=config.getTemplate(template);
		try{
			StringWriter writer=new StringWriter();
			ftl.process(data, writer);
			return writer.toString();
		}
		catch (TemplateException e){
			throw new ServletException("Problem while processing template",e);
		}
	}
	
	public static void insertSimpleCard(HttpServletRequest req)throws IOException{
		Mirror mirror=MirrorUtils.getMirror(req);
		Timeline timeline=mirror.timeline();
		TimelineItem timelineitem=new TimelineItem();
		timelineitem.setText(getRandomVideoName());
		timelineitem.setTitle("Step One Two");
		TimelineItem tli=timeline.insert(timelineitem).execute();
		//timeline.insert(timelineitem).executeAndDownloadTo(System.out);
		
	}
	
}
