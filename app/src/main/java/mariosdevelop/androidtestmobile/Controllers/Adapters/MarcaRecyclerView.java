package mariosdevelop.androidtestmobile.Controllers.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mariosdevelop.androidtestmobile.Controllers.Interface.onMarcaSelected;
import mariosdevelop.androidtestmobile.Controllers.MarcaActivity;
import mariosdevelop.androidtestmobile.R;

/**
 * Created by marioaguilar
 */

public class MarcaRecyclerView extends RecyclerView.Adapter<MarcasViewHolder> {


    private ArrayList<String> ListMarcas =  new ArrayList<>();
   private Activity Context;
   CharSequence Chars1 = "Claro";
   CharSequence Chars2 = "Tuenti";
   CharSequence Chars3 = "Entel";
   private onMarcaSelected Marca;

   public  MarcaRecyclerView (ArrayList<String> ArrayMarcas, Activity Activity , onMarcaSelected marca ){
    this.ListMarcas = ArrayMarcas;
    this.Context = Activity;
    this.Marca = marca;
   }




    @Override
    public MarcasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View ViewContainer;

           ViewContainer =  LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, null);

           MarcasViewHolder VH =  new MarcasViewHolder(ViewContainer,Marca);

        return VH;
    }

    @Override
    public void onBindViewHolder(MarcasViewHolder holder, int position) {

       holder.MarcaName.setText(ListMarcas.get(position));
       if(ListMarcas.get(position).contains(Chars1))
       holder.MarcaImage.setImageResource(R.drawable.ic_claro);
       else if(ListMarcas.get(position).contains(Chars2))
        holder.MarcaImage.setImageResource(R.drawable.ic_tuenti);
       else if(ListMarcas.get(position).contains(Chars3))
        holder.MarcaImage.setImageResource(R.drawable.ic_entel);

    }

    @Override
    public int getItemCount() {
        return this.ListMarcas.size();
    }
}
