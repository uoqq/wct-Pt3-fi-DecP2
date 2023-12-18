package sit.int202.classicmodeltue.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import sit.int202.classicmodeltue.entities.Product;
import sit.int202.classicmodeltue.repositories.ProductRepository;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductListServlet", value = "/product-list")
public class ProductListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductRepository productRepository = new ProductRepository();
        String pageParam = request.getParameter("page");
        String pageSizeParam = request.getParameter("pageSize");
        int page = pageParam == null ? 1 : Integer.valueOf(pageParam);
        int pageSize = pageSizeParam == null ? productRepository.getDefaultPageSize() : Integer.valueOf(pageSizeParam);
        List<Product> productList = productRepository.findAll(page, pageSize);
        request.setAttribute("products", productList);
        request.setAttribute("page", page);
        request.setAttribute("pageSize", pageSize);
        int itemCount = productRepository.countAll();
        request.setAttribute("itemCount", itemCount);
        int totalPage = itemCount / pageSize + (itemCount % pageSize == 0 ? 0 : 1);
        request.setAttribute("totalPage", totalPage);
        getServletContext().getRequestDispatcher("/new-product-list.jsp").forward(request, response);
//        getServletContext().getRequestDispatcher("/product-list.jsp").forward(request, response);
    }
}
