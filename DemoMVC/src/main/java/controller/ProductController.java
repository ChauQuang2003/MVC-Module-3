package controller;

import model.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "productController", value = "/products")
public class ProductController extends HttpServlet {

    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        if (path == null) {
            path="";
        }
        switch (path) {
            case "home":
                showHome(req, resp);
                break;
            case "add":
                showFormAdd(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "edit":
                showFormEdit(req, resp);
                break;
            case "detail":
                showDetail(req, resp);
                break;
            default:
                showError(req, resp);
                break;
        }
    }

    private void showDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDetail = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(idDetail);
        req.setAttribute("product", product);
        req.getRequestDispatcher("detail.jsp").forward(req, resp);
    }


    private void showFormEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idEidt = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(idEidt);
        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/edit.jsp");
        dispatcher.forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idDelete = Integer.parseInt(req.getParameter("id"));
        productService.delete(idDelete);
        resp.sendRedirect("/products?path=home");
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("error.jsp");
        dispatcher.forward(req, resp);
    }

    private void showFormAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/add.jsp");
        dispatcher.forward(req, resp);
    }

    private void showHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productService.getAll();
        RequestDispatcher dispatcher = req.getRequestDispatcher("/product-view/home.jsp");
        req.setAttribute("list", list);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        switch (path) {
            case "add":
                addProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            default:
                showError(req, resp);
                break;
        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String image = req.getParameter("image");
            Product newProduct = new Product(name, price, image);
            productService.add(newProduct);
            resp.sendRedirect("/products?path=home");

        } catch (Exception e) {
            showErrorPage(req, resp);
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String image = req.getParameter("image");
            Product newProduct = new Product(id, name, price, image);
            productService.update(id, newProduct);
            resp.sendRedirect("/products?path=home");
        } catch (Exception e) {
            showErrorPage(req, resp);
        }
    }

    private void showErrorPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
