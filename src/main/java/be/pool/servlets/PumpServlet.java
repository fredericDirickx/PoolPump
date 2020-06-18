package be.pool.servlets;

import be.pool.models.Controller;
import be.pool.models.PumpController;
import be.pool.models.TestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pumpOnOf")
public class PumpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            Controller controller = (Controller) session.getAttribute("controller");
            String pumpStatus = controller.getStatusSwitch()?"off":"on";
            req.setAttribute("pumpStatus",pumpStatus);
            req.getRequestDispatcher("/WEB-INF/pages/poolPump.jsp").forward(req,resp);

        }catch (NullPointerException ex){
            Controller controller = new PumpController();
            session.setAttribute("controller",controller);
            String pumpStatus = controller.getStatusSwitch()?"off":"on";
            req.setAttribute("pumpStatus",pumpStatus);
            req.setAttribute("exceptionMessage",ex.getMessage());
            req.getRequestDispatcher("/WEB-INF/pages/poolPump.jsp").forward(req,resp);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
             HttpSession session = req.getSession();
         try {
             Controller controller = (Controller) session.getAttribute("controller");
             String pumpStatus = controller.getStatusSwitch()?"off":"on";
             req.setAttribute("pumpStatus",pumpStatus);
             controller.setSwitch(!controller.getStatusSwitch());
             resp.sendRedirect("/PoolPump/pumpOnOf");
         }catch (NullPointerException ex){
             Controller controller = new PumpController();
             String pumpStatus = controller.getStatusSwitch()?"off":"on";
             session.setAttribute("controller",controller);
             req.setAttribute("pumpStatus",pumpStatus);
             controller.setSwitch(!controller.getStatusSwitch());
             resp.sendRedirect("/PoolPump/pumpOnOf");
         }


    }
}
