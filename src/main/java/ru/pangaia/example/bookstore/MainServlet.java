package ru.pangaia.example.bookstore;

import jetbrains.exodus.entitystore.PersistentEntityStore;
import jetbrains.exodus.entitystore.PersistentEntityStores;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet
{
    PersistentEntityStore store;
    Logger logger;
    private static final String STORE_ADDR_DIR = "/home/oneuro/.bookshelfData";

    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        logger = LoggerFactory.getLogger(MainServlet.class);
        store = PersistentEntityStores.newInstance(STORE_ADDR_DIR);
        getServletContext().setAttribute("store", store);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy()
    {
        store.close();
        logger.info("closing store...");
        super.destroy();
    }
}
