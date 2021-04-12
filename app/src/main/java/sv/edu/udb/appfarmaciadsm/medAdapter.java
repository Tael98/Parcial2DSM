package sv.edu.udb.appfarmaciadsm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class medAdapter extends FirebaseRecyclerAdapter<Medicina, medAdapter.medViewHolder>
{
    public int[] prodCounter = new int[10];
    public double[] prices = new double[10];
    public int counter = 0;
    String price = "0";
    public medAdapter(@NonNull FirebaseRecyclerOptions<Medicina> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull medViewHolder holder, int position, @NonNull Medicina model)
    {
        //Seteando los campos de cada medicina
        //Nombre
        holder.txtNombre.setText(model.getNombre());
        //Precio
        price = String.valueOf(model.getPrecio());
        if(price.substring(price.lastIndexOf(".") + 1).length() < 2)
        {
            //Se añade un 0 para uniformidad
            price = price + "0";
        }
        holder.txtPrecio.setText("$" + price);
        if(counter > 9) counter = 9;
        prices[counter] = model.getPrecio();
        counter = counter + 1;
        //Imagen
        Glide.with(holder.imgView.getContext()).load(model.getImgUrl()).into(holder.imgView);
    }

    @NonNull
    @Override
    public medViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new medViewHolder(view);
    }

    class medViewHolder extends RecyclerView.ViewHolder
    {
        CircleImageView imgView;
        TextView txtNombre, txtPrecio, txtCantidad;
        public medViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img1);
            txtNombre = itemView.findViewById(R.id.nameText);
            txtPrecio = itemView.findViewById(R.id.priceText);
            txtCantidad = itemView.findViewById(R.id.cantidadText);

            //Asignando métodos a los botones
            itemView.findViewById(R.id.btnAñadir).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeActivity.total = HomeActivity.total + prices[getAdapterPosition()];
                    prodCounter[getAdapterPosition()] = prodCounter[getAdapterPosition()] + 1;
                    txtCantidad.setText(String.valueOf(prodCounter[getAdapterPosition()]));

                    Log.d("a", String.valueOf(prodCounter[getAdapterPosition()]));
                    Log.d("a", String.valueOf(HomeActivity.total));
                }
            });

            itemView.findViewById(R.id.btnQuitar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(HomeActivity.total - prices[getAdapterPosition()] >= 0 && prodCounter[getAdapterPosition()] - 1 >= 0)
                    {
                        HomeActivity.total = HomeActivity.total - prices[getAdapterPosition()];
                        prodCounter[getAdapterPosition()] = prodCounter[getAdapterPosition()] - 1;
                        txtCantidad.setText(String.valueOf(prodCounter[getAdapterPosition()]));

                        Log.d("a", String.valueOf(prodCounter[getAdapterPosition()]));
                        Log.d("a", String.valueOf(HomeActivity.total));
                    }
                }
            });
        }
    }
}
