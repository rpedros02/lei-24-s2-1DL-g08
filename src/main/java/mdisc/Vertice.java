package mdisc;

import java.util.Objects;

public class Vertice {

    private String vertice;

    public Vertice(String vertice) {    // construtor
        this.vertice = vertice;
    }

    public String getVertice() {
        return vertice;
    }

    public void setVertice(String vertice) {
        this.vertice = vertice;
    }

    public String toString() {
        return getVertice();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vertice other = (Vertice) obj;
        return Objects.equals(vertice, other.vertice);
    }
}
