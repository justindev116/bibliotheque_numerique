package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Adherent;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (Adherent.verifierConnexion(login, password)) {
            // Succès → rediriger vers accueil
            response.sendRedirect("accueil.html");
        } else {
            // Échec → renvoyer sur connexion avec message d’erreur
            request.setAttribute("erreur", "Login ou mot de passe incorrect !");
            RequestDispatcher rd = request.getRequestDispatcher("connexion.html");
            rd.forward(request, response);
        }
    }
}