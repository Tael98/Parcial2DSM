package sv.edu.udb.appfarmaciadsm.datos;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import java.util.List;

import sv.edu.udb.appfarmaciadsm.R;

public class compAdapter extends ArrayAdapter<Compra>
{
    List<Compra> compras;
    private Activity context;

    public compAdapter(@NonNull Activity context, @NonNull List<Compra> compras) {
        super(context, R.layout.compra_row, compras);
        this.context = context;
        this.compras = compras;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;

        if (view == null)
            rowview = layoutInflater.inflate(R.layout.compra_row,null);
        else rowview = view;

        TextView txtFecha = rowview.findViewById(R.id.txtFecha);
        TextView txtGasto = rowview.findViewById(R.id.txtGasto);

        txtFecha.setText("Fecha: " + compras.get(position).getFecha());
        txtGasto.setText("Total: " + compras.get(position).getPrecio());

        return rowview;
    }
}
