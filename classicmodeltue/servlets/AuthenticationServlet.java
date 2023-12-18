package sit.int202.classicmodeltue.servlets;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.classicmodeltue.entities.Customer;
import sit.int202.classicmodeltue.repositories.CustomerRepository;

import java.io.IOException;

@WebServlet(name = "AuthenticationServlet", value = "/login")
public class AuthenticationServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
////        doPost(request, response);
//    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String userName = request.getParameter("userName");
        if (userName == null || userName.trim().length() == 0) {
            response.addHeader("error", "Invalid user name !!!");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findByName(userName);
        if (customer == null) {
            response.addHeader("error", "User '"+ userName+ "' does not exist !!!");
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        } else {
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2d, 16, 16);
            char[] passwordArray = password.toCharArray();
            System.out.println("-------------------------");
            System.out.println(customer.getPassword());
            System.out.println(password);

            boolean isOk = argon2.verify(customer.getPassword(), passwordArray);
            if (!isOk) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                response.addHeader("error", "Incorrect password !!!");
            } else {
                request.getSession().setAttribute("user", customer);
            }
        }
    }
}