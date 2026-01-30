package servlets;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        HttpSession session = request.getSession();
        List<Map<String, Object>> cart = (List<Map<String, Object>>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        boolean found = false;
        for (Map<String, Object> item : cart) {
            if ((Integer) item.get("id") == id) {
                // Item already in cart → update quantity
                int existingQty = (int) item.get("quantity");
                item.put("quantity", existingQty + quantity);
                found = true;
                break;
            }
        }

        if (!found) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", id);
            item.put("name", name);
            item.put("price", price);
            item.put("quantity", quantity);
            cart.add(item);
        }

        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}
