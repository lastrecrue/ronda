package ma.ronda.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Suspend;
import org.jboss.resteasy.spi.AsynchronousResponse;

@Path("/user-management")
public class MatchManager {
	@Path("/myresource")
	@GET
	public void getMyResource(@Suspend final AsynchronousResponse response) {
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					Response jaxrs = Response.ok("basic").type(MediaType.TEXT_PLAIN).build();
					response.setResponse(jaxrs);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
}
