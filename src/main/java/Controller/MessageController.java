package Controller;

import dao.MessageDAO;
import model.Message;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("messages")
public class MessageController {

    @EJB
    private MessageDAO messageDAO;

    @GET
    @Path("/{MessageID}")
    public Message getMessage(@PathParam("MessageID")long id, @Context UriInfo uriInfo){
        Message message = messageDAO.findById(id);
        String url = getUriForSelf(uriInfo,message);
        message.addLink(url,"self");
        return message;
    }

    private String getUriForSelf(UriInfo info, Message message){
        String url = info.getBaseUriBuilder()
                .path(MessageController.class)
                .path(Long.toString(message.getId()))
                .build()
                .toString();
        return url;
    }
}
