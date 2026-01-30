package servlets;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RemoveFromCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            int id = Integer.parseInt(idStr);
            HttpSession session = request.getSession();
            List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");

            if (cart != null) {
                // Remove item with matching id
                cart.removeIf(item -> (Integer) item.get("id") == id);
                session.setAttribute("cart", cart); // update session
            }
        }
        response.sendRedirect("cart.jsp");
    }
}
