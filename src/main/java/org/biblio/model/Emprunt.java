package org.biblio.model;

import java.util.Date;

public class Emprunt {
    private Integer id;
    private Date dateDebut;
    private Date dateFin;
    private Boolean retour;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Boolean getRetour() {
        return retour;
    }

    public void setRetour(Boolean retour) {
        this.retour = retour;
    }
}
