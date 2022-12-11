package dev.jujumba.homework4;


import dev.jujumba.homework4.beans.CustomerBeen;
import dev.jujumba.homework4.beans.EmployeeBean;
import dev.jujumba.homework4.dao.CustomerDao;
import dev.jujumba.homework4.dao.EmployeeDao;
import dev.jujumba.homework4.data.Customer;
import dev.jujumba.homework4.data.Employee;

import java.io.*;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.sql.DataSource;

@WebServlet(name = "chinookServlet", value = {
        "/employees", "/add_employee", "/delete_employee", "/edit_employee", "/show_customers_of", "/update_employee"
})
public class ChinookServlet extends HttpServlet {

    @Resource(name = "jdbc/postgres")
    private DataSource ds;

    private EmployeeDao artistDao;

    private CustomerDao albumDao;

    public void init() {
        artistDao = new EmployeeDao(ds);
        albumDao = new CustomerDao(ds);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.endsWith("/employees")) {
            showArtists(request, response);
        } else if (uri.endsWith("/add_employee")) {
            addArtist(request, response);
        } else if (uri.endsWith("/delete_employee")) {
            deleteArtist(request, response);
        } else if (uri.endsWith("/edit_employee")) {
            editArtist(request, response);
        } else if (uri.endsWith("/update_employee")) {
            updateArtist(request, response);
        } else if (uri.endsWith("/show_customers_of")) {
            showAlbumsByArtist(request, response);
        }
    }

    private void showAlbumsByArtist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int artistId = Integer.parseInt(request.getParameter("id"));
        Employee artist = artistDao.findById(artistId);
        List<Customer> albums = albumDao.findByEmployee(artistId);
        request.setAttribute("customerBeen", new CustomerBeen(albums, artist));
        request.getRequestDispatcher("/customers_by_employee.jsp").forward(request, response);
    }

    private void updateArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        artistDao.update(new Employee(id, name));
        response.sendRedirect("employees");
    }

    private void editArtist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Employee employee = artistDao.findById(id);
        if (employee == null) {
            request.setAttribute("employee_id", id);
            request.getRequestDispatcher("/artist_not_found.jsp").forward(request, response);
        } else {
            request.setAttribute("employee", employee);
            request.getRequestDispatcher("/edit_artist.jsp").forward(request, response);
        }
    }

    private void deleteArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        artistDao.deleteById(id);
        response.sendRedirect("employee");
    }

    private void addArtist(HttpServletRequest request, HttpServletResponse response) throws IOException {
        artistDao.add(request.getParameter("employeename"));
        response.sendRedirect("employees");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void showArtists(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> artists = artistDao.findAll();
        request.setAttribute("employeeBean", new EmployeeBean(artists));
        request.getRequestDispatcher("/employees.jsp").forward(request, response);
    }

}