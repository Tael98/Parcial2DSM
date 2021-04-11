package sv.edu.udb.appfarmaciadsm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class medAdapter extends FirebaseRecyclerAdapter<Medicina, medAdapter.medViewHolder>
{
    public medAdapter(@NonNull FirebaseRecyclerOptions<Medicina> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull medViewHolder holder, int position, @NonNull Medicina model)
    {
        holder.txtNombre.setText(model.getNombre());
        holder.txtPrecio.setText("$" + String.valueOf(model.getPrecio()));
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
        TextView txtNombre, txtPrecio;
        public medViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.img1);
            txtNombre = itemView.findViewById(R.id.nameText);
            txtPrecio = itemView.findViewById(R.id.priceText);
        }
    }
}
