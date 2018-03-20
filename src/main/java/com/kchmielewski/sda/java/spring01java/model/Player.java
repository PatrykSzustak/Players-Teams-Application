package com.kchmielewski.sda.java.spring01java.model;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Player {
    private Integer id;

    @NotEmpty(message = "{name.empty}")
    @Size(max = 16, message = "{name.error}")
    private String name;

    @NotEmpty(message = "{surname.empty}")
    @Size(min = 3, max = 32, message = "{surname.error}")
    private String surname;

    private String teamName;

    public Player() {

    }

    public Player(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Player(Integer id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public Player(Integer id, String name, String surname, String teamName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) &&
                Objects.equals(surname, player.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return " " + name + " " + surname + " ";
    }
}
