package fr.eni.projet.encheres.bo;

import java.util.List;
import java.util.Objects;

public class Categorie {
    private int noCategorie;
    private String libelle;
    private List<ArticleVendu> articlesVendusList;

    public Categorie() {
    }

    public Categorie(int noCategorie, String libelle) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
    }

    public Categorie(int noCategorie, String libelle, List<ArticleVendu> articlesVendusList) {
        this.noCategorie = noCategorie;
        this.libelle = libelle;
        this.articlesVendusList = articlesVendusList;
    }

    public int getNoCategorie() {
        return noCategorie;
    }

    public void setNoCategorie(int noCategorie) {
        this.noCategorie = noCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<ArticleVendu> getArticlesVendusList() {
        return articlesVendusList;
    }

    public void setArticlesVendusList(List<ArticleVendu> articlesVendusList) {
        this.articlesVendusList = articlesVendusList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return noCategorie == categorie.noCategorie && Objects.equals(libelle, categorie.libelle);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Categorie{");
        sb.append("noCategorie=").append(noCategorie);
        sb.append(", libelle='").append(libelle).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
