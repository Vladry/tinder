package tinder.controller;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream os = resp.getOutputStream()) {
            String rqName = req.getRequestURI();
            ClassLoader classLoader = this.getClass().getClassLoader();

            byte[] bytes = IOUtils.toByteArray(Objects.requireNonNull(
                    classLoader.getResourceAsStream(rqName.substring(1))));
//   изначально было так:  byte[] bytes = IOUtils.readAllBytes(classLoader.getResourceAsStream(rqName.substring(1)));
            os.write(bytes);

        } catch (NullPointerException ex) {
            resp.setStatus(404);
        }
    }
}