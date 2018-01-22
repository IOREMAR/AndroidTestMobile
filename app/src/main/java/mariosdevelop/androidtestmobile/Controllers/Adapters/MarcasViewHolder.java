package mariosdevelop.androidtestmobile.Controllers.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mariosdevelop.androidtestmobile.Controllers.Interface.onMarcaSelected;
import mariosdevelop.androidtestmobile.Controllers.MarcaActivity;
import mariosdevelop.androidtestmobile.R;

/**
 * Created by marioaguilar on 19/01/18.
 */

public class MarcasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

   public TextView MarcaName;
    public ImageView MarcaImage;

    private onMarcaSelected Marca;


    public MarcasViewHolder(View Contentview ,onMarcaSelected Marca ){
        super(Contentview);
        this.Marca=Marca;

        Contentview.setOnClickListener(this);

        MarcaName = (TextView)Contentview.findViewById(R.id.country_name);
        MarcaImage = (ImageView)Contentview.findViewById(R.id.country_photo);


    }

    @Override
    public void onClick(View view) {

      Marca.OnMarcaSelected(getPosition());

    }
}
