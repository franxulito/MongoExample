package com.fran.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fran.config.DBConfig;
import com.fran.exception.DAOException;
import com.fran.persistence.DAO;
import com.fran.persistence.DaoFactory;
import com.fran.persistence.MongoDAOFactory;
import com.mongodb.BasicDBObject;

public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger LOGGER = LoggerFactory.getLogger(InitServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		MongoDAOFactory factory = null;
		try {
			factory = (MongoDAOFactory) DaoFactory.createFactory("com.fran.persistence.MongoDAOFactory");
		} catch (DAOException e) {
			LOGGER.error("Error obteniendo la factory.", e);
		}
		
		DAO dao = factory.getMongoDAO();
		/*try {
			dao.insertMany(DBConfig.getInstance().getCollectionName());
		} catch (DAOException e) {
			LOGGER.error("Error en el insert.", e);
		}*/
		
		/*try {
			dao.update(DBConfig.getInstance().getCollectionName(), new BasicDBObject("age", new BasicDBObject("$eq", 25)));
		} catch (DAOException e) {
			LOGGER.error("Error en el insert.", e);
		}*/
		
		List<Document> result = null;
		try {
			result = dao.select(DBConfig.getInstance().getCollectionName(), new BasicDBObject("age", new BasicDBObject("$eq", 25)));
		} catch (DAOException e) {
			LOGGER.error("Error en el select.", e);
		}
		

		
		
		pw.println("<html>");
		pw.println("<head><title>Hello Mongo</title></title>");
		pw.println("<body>");
		for (Document element : result) {			
			pw.println("<p>" + element.toJson() + "</p>");
		}
		pw.println("</body></html>");
	}
}
