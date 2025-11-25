package controller;

import dao.ProductDao;
import model.Cart;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession();

        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String todo = req.getParameter("todo");

        if (todo == null) {
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
            return;
        }

        switch (todo) {

            case "add":
                String itemID = req.getParameter("itemID");
                Product p = ProductDao.getById(itemID);
                if (p != null) {
                    cart.addItem(p);
                }
                resp.sendRedirect("cart");
                break;

            case "remove":
                cart.removeItem(req.getParameter("itemID"));
                resp.sendRedirect("cart");
                break;

            case "update":
                String id = req.getParameter("itemID");
                int qty = Integer.parseInt(req.getParameter("qty"));
                cart.updateQuantity(id, qty);
                resp.sendRedirect("cart");
                break;

            default:
                req.getRequestDispatcher("cart.jsp").forward(req, resp);
        }
    }
}
