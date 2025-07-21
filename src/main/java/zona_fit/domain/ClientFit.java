package zona_fit.domain;

import java.util.Objects;

public class ClientFit {
    private int id;
    private String name;
    private String lastName;
    private int suscription;

    public ClientFit(){}

    public ClientFit(int id){
        this.id = id;
    }

    public ClientFit(String name, String lastName, int suscription){
        this.name = name;
        this.lastName = lastName;
        this.suscription = suscription;
    }

    public ClientFit(int id, String name, String lastName, int suscription) {
        this(name, lastName, suscription);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSuscription() {
        return suscription;
    }

    public void setSuscription(int suscription) {
        this.suscription = suscription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", suscription=" + suscription;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClientFit clientFit = (ClientFit) o;
        return id == clientFit.id && suscription == clientFit.suscription && Objects.equals(name, clientFit.name) && Objects.equals(lastName, clientFit.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, suscription);
    }
}
