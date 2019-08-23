package com.umcincoum.dj.model.postgre;

import javax.persistence.*;

@Entity
@Table(name = "TB_JUKEBOX_SUGESTAO")
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class TbJukeboxSugestao {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;

    @Column(columnDefinition = "text")
    private String email;
    @Column(columnDefinition = "text")
    private String description;

    public TbJukeboxSugestao(String email, String description) {
        this.email = email;
        this.description = description;
    }

    public TbJukeboxSugestao() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
