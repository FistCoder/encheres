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

}
