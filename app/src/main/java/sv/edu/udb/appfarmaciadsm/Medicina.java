package sv.edu.udb.appfarmaciadsm;

public class Medicina {
    public String nombre;
    public double precio;
    public String imgUrl;

    public Medicina() { }

    public Medicina(String nombre, double precio)
    {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String key) {
        this.imgUrl = key;
    }
}
