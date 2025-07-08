package fr.eni.projet.encheres.bo;

import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Utilisateur {

    private int noUtilisateur;

    @NotBlank
    @Size(max = 30)
    private String pseudo;

    @NotBlank
    @Size(max = 30)
    private String nom;

    @NotBlank
    @Size(max = 30)
    private String prenom;

    @NotBlank
    @Size(max = 50)
    @Email
    @Pattern(regexp = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @Size(max = 15)
    private String telephone;

    @Size(max = 30)
    private String rue;

    @Min(0)
    @Max(99999)
    private int codePostal;

    @Size(max = 50)
    private String ville;

    @NotBlank
    @Size(min = 2, max = 80)
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    private String motDePasse;

    @Min(0)
    private int credit;

    private boolean administrateur;


    private List<ArticleVendu> articlesVendus = new ArrayList<ArticleVendu>();
    private List<Enchere> encheres = new ArrayList<Enchere>();

    public Utilisateur() {
    }

    public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, int codePostal, String ville, String motDePasse, int credit, boolean administrateur) {
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    public int getNoUtilisateur() {
        return noUtilisateur;
    }

    public void setNoUtilisateur(int noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    public List<ArticleVendu> getArticlesVendus() {
        return articlesVendus;
    }

    public void setArticlesVendus(List<ArticleVendu> articlesVendus) {
        this.articlesVendus = articlesVendus;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return noUtilisateur == that.noUtilisateur && codePostal == that.codePostal && credit == that.credit && administrateur == that.administrateur && Objects.equals(pseudo, that.pseudo) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(email, that.email) && Objects.equals(telephone, that.telephone) && Objects.equals(rue, that.rue) && Objects.equals(ville, that.ville) && Objects.equals(motDePasse, that.motDePasse);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Utilisateur{");
        sb.append("noUtilisateur=").append(noUtilisateur);
        sb.append(", pseudo='").append(pseudo).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", rue='").append(rue).append('\'');
        sb.append(", codePostal=").append(codePostal);
        sb.append(", ville='").append(ville).append('\'');
        sb.append(", motDePasse='").append(motDePasse).append('\'');
        sb.append(", credit=").append(credit);
        sb.append(", administrateur=").append(administrateur);
        sb.append('}');
        return sb.toString();
    }
}
