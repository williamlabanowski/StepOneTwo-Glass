package test.book.glass;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import test.book.glass.auth.AuthUtils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.mirror.Mirror;

public final class MirrorUtils
{
  public static Mirror getMirror( HttpServletRequest req )
      throws IOException
  {
    String userId = SessionUtils.getUserId( req );
    Credential cred = AuthUtils.getCredential(userId);
    return getMirror(cred);
  }

  //if i pass the userID string, it builds a credential from that and gets a Mirror object based on that credential
  public static Mirror getMirror( String userId )
      throws IOException
  {
    Credential cred = AuthUtils.getCredential(userId);
    return getMirror(cred);
  }

  //if I pass getMirror a credential, I use it to build a Mirror object 
  //mirror object allows me to communicate with the Mirror API
  public static Mirror getMirror( Credential cred )
      throws IOException
  {
	//initialize the mirror object
    return new Mirror.Builder(new UrlFetchTransport(), new JacksonFactory(), cred)
    //set the application name
    .setApplicationName("Step One Two")
    //build it
    .build();
  }
}