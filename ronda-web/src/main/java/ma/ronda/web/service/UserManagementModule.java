package ma.ronda.web.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import ma.ronda.web.entity.User;

import org.apache.log4j.Logger;

@Path("/user-management")
public class UserManagementModule {

	Logger logger = Logger.getLogger(getClass());

	@GET
	@Path("/users/{id}")
	@Produces("application/json")
	public Response getUserById(@PathParam("id") Integer id) {
		User user = new User();
		user.setId(id);
		user.setFirstName("Lokesh");
		user.setLastName("Gupta");
		Response build = Response.status(200).entity(user).build();
		MatchManager matchManager = new MatchManager();

		PushExample pushExample = new PushExample();
		matchManager.getMyResource(pushExample);
		logger.debug(id);
		return build;
	}
}