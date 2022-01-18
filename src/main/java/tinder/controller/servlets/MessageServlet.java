package tinder.controller.servlets;

import tinder.controller.unils.TemplateEngine;
import tinder.domain.User;
import tinder.domain.Message;
import tinder.dao.MessageDao_v;
import tinder.dao.UserDao_v;

import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MessageServlet extends HttpServlet {
    private final TemplateEngine templateEngine;
    private final UserDao_v userDao_hikari;
    private final MessageDao_v msgDao_hikari;
    private User loggedUser = null;
    private User contact = null;
    private boolean rendering;

    public MessageServlet(UserDao_v userDao_hikari, MessageDao_v msgDao_hikari, TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        this.userDao_hikari = userDao_hikari;
        this.msgDao_hikari = msgDao_hikari;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res){
        doGet(req, res);
    };


    private int filterOffContactId(HttpServletRequest req){
        String rawId = "1";
        if (req.getRequestURI().split("/").length >= 3
                && req.getRequestURI().split("/")[2] != null)
        {
            rawId = req.getRequestURI().split("/")[2];
            System.out.println("rawId: " + rawId);
        }
        return Integer.parseInt(rawId);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        if (rendering) {
            rendering = false;
            return;
        }
        HashMap<String, Object> data = new HashMap<>();
        HttpSession session = req.getSession(false);

        contact = userDao_hikari.retrieveById(filterOffContactId(req));

        loggedUser = (User) session.getAttribute("user");
        ArrayList<Message> senderMessages = msgDao_hikari.retrieveAllMessages(loggedUser.getId(), getContactId(contact) );
        ArrayList<Message> receiverMessages = msgDao_hikari.retrieveAllMessages(getContactId(contact), loggedUser.getId());
        System.out.println("senderMessages: " + senderMessages);
        System.out.println("receiverMessages: " + receiverMessages);
        if (senderMessages == receiverMessages) System.out.println("senderMessages = receiverMessages");
        Message newMessage = null;
        String newMsgStr;
        if (req.getParameter("message") != null) {
            newMsgStr = req.getParameter("message");
            newMessage = new Message(loggedUser.getId(), getContactId(contact), newMsgStr);
            senderMessages.add(newMessage);
            msgDao_hikari.createMessage(newMessage);
        }



        data.put("user", loggedUser);
        data.put("contact", contact);
        data.put("senderMessages", senderMessages);
        data.put("receiverMessages", receiverMessages);
        rendering = true;
        templateEngine.render("messages.ftl", data, resp);
        data = null;

    }

    private int getContactId(User contact){
        return contact == null? 0 : contact.getId();
    }

}
