package sv.edu.udb.appfarmaciadsm.datos;

public class Compra {
    public String fecha;
    public double precio;
    String key;

    public Compra() { }

    public Compra(String fecha, double precio)
    {
        this.fecha = fecha;
        this.precio = precio;
    }

    public String getFecha() { return fecha; }

    public void setFecha(String fecha) { this.fecha = fecha; }

    public double getPrecio() { return precio; }

    public void setPrecio(double precio) { this.precio = precio; }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }
}
