package controller;

import dao.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // you technically don't need this because CustomerDAO methods are static,
    // but it's fine to keep
    private CustomerDAO customerDAO;

    @Override
    public void init() throws ServletException {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // show the registration page
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // these names MUST match your <input name="..."> in register.jsp
        String firstName          = request.getParameter("firstName");
        String lastName           = request.getParameter("lastName");
        String email              = request.getParameter("email");
        String password           = request.getParameter("password");
        String creditCardNumber   = request.getParameter("creditCardNumber");
        String creditCardExpiry   = request.getParameter("creditCardExpiry");
        String creditCardCVV      = request.getParameter("creditCardCVV");
        String addressIdParam     = request.getParameter("addressId"); // optional/hidden for now

        // basic validation (you can tighten this later)
        if (firstName == null || lastName == null || email == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() ||
                email.isEmpty() || password.isEmpty()) {

            request.setAttribute("error", "First name, last name, email, and password are required.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try {
            // 1) check if customer already exists
            Customer existing = CustomerDAO.findByEmail(email);
            if (existing != null) {
                request.setAttribute("error", "An account with that email already exists.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            // 2) build Customer object
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPassword(password);  // plain text for now (DB column passwordHash)
            customer.setCreditCardNumber(creditCardNumber);
            customer.setCreditCardExpiry(creditCardExpiry);
            customer.setCreditCardCVV(creditCardCVV);

            // addressID is nullable in DB, so handle safely
            if (addressIdParam != null && !addressIdParam.isEmpty()) {
                try {
                    customer.setAddressId(Integer.parseInt(addressIdParam));
                } catch (NumberFormatException e) {
                    customer.setAddressId(null); // bad/blank input → treat as null
                }
            } else {
                customer.setAddressId(null);
            }

            // 3) save to DB (uses your CustomerDAO.createCustomer)
            boolean created = CustomerDAO.createCustomer(customer);

            if (!created) {
                request.setAttribute("error", "Could not create account. Please try again.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
                return;
            }

            
            response.sendRedirect(request.getContextPath() + "/items.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
