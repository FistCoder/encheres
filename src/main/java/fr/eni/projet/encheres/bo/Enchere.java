package fr.eni.projet.encheres.bo;


import java.time.LocalDate;
import java.util.Objects;

public class Enchere {
    private LocalDate dateEnchere;
    private long montant_enchere;
    private ArticleVendu articleVendu = new ArticleVendu();
    private Utilisateur utilisateur = new Utilisateur();

    public Enchere() {
    }

    public Enchere(LocalDate dateEnchere, long montant_enchere) {
        this.dateEnchere = dateEnchere;
        this.montant_enchere = montant_enchere;
    }

    public Enchere(LocalDate dateEnchere, long montant_enchere, ArticleVendu articleVendu, Utilisateur utilisateur) {
        this.dateEnchere = dateEnchere;
        this.montant_enchere = montant_enchere;
        this.articleVendu = articleVendu;
        this.utilisateur = utilisateur;
    }

    public LocalDate getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(LocalDate dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public long getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(long montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    public ArticleVendu getArticleVendu() {
        return articleVendu;
    }

    public void setArticleVendu(ArticleVendu articleVendu) {
        this.articleVendu = articleVendu;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchere = (Enchere) o;
        return montant_enchere == enchere.montant_enchere && Objects.equals(dateEnchere, enchere.dateEnchere);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enchere{");
        sb.append("dateEnchere=").append(dateEnchere);
        sb.append(", montant_enchere=").append(montant_enchere);
        sb.append('}');
        return sb.toString();
    }


}
