package fr.eni.projet.encheres.bo;

import java.util.Objects;

public class Retrait {

    private String rue;
    private String code_postal ;
    private String ville ;
    private ArticleVendu articleVendu = new ArticleVendu();

    public Retrait(String rue, String code_postal, String ville, ArticleVendu articleVendu) {
        this.rue = rue;
        this.code_postal = code_postal;
        this.ville = ville;
        this.articleVendu = articleVendu;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Retrait retrait = (Retrait) o;
        return Objects.equals(rue, retrait.rue) && Objects.equals(code_postal, retrait.code_postal) && Objects.equals(ville, retrait.ville) && Objects.equals(articleVendu, retrait.articleVendu);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Retrait{");
        sb.append("rue='").append(rue).append('\'');
        sb.append(", code_postal='").append(code_postal).append('\'');
        sb.append(", ville='").append(ville).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
