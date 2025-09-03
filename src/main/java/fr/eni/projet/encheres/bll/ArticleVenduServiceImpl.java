package fr.eni.projet.encheres.bll;

import fr.eni.projet.encheres.bo.ArticleVendu;
import fr.eni.projet.encheres.dal.ArticleVenduDAO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class ArticleVenduServiceImpl for
 *
 */
@Service
public class ArticleVenduServiceImpl implements ArticleVenduService{

    private ArticleVenduDAO articleVenduDAO;

    public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
        this.articleVenduDAO = articleVenduDAO;
    }


    @Override
    public List<ArticleVendu> getAllArticleVenduAndUser() {
        return articleVenduDAO.getAllArticleVenduAndUser();
    }

    public List<ArticleVendu> getAllArticleVenduAndUserByMail(String mail) {
        return articleVenduDAO.getArticleVenduEnCoursByUserMail(mail);
    }

    @Override
    public List<ArticleVendu> getArticleVenduNonDebuteeByUserMail(String email) {
        return articleVenduDAO.getArticleVenduNonDebuteeByUserMail(email);
    }

    @Override
    public List<ArticleVendu> getArticleVenduTermineeByUserMail(String email) {
        return articleVenduDAO.getArticleVenduTermineeByUserMail(email);
    }

    @Override
    public List<ArticleVendu> getEnchereOuvertes() {
        return articleVenduDAO.getEnchereOuvertes();
    }

    @Override
    public List<ArticleVendu> getMesEncheresByUserMail(String email) {
        return articleVenduDAO.getMesEncheresByUserMail(email);
    }

    @Override
    public List<ArticleVendu> getEncheresRemporteesByUserMail(String email) {
        return articleVenduDAO.getEncheresRemporteesByUserMail(email);
    }


}
