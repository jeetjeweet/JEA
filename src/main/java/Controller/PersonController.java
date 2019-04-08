package Controller;

import dao.PersonDAO;
import model.Person;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/Person")
public class PersonController extends HttpServlet {
    @EJB
    private PersonDAO personDAO;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response){
        Person newPerson = new Person(request.getParameter("name"), request.getParameter("password"));
        personDAO.save(newPerson);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        request.getRequestDispatcher("home.jsp");
    }
}
