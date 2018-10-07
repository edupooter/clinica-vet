package br.com.senacrs.appcrudcontatosdao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.senacrs.appcrudcontatosdao.R;
import br.com.senacrs.appcrudcontatosdao.model.Pet;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder>
{
    private List<Pet> listaPets;
    private Context contexto;
    private PetOnClickListener petOnClickListener;

    public PetAdapter(Context contexto, List<Pet> listaPets, PetOnClickListener petOnClickListener) {
        this.contexto = contexto;
        this.listaPets = listaPets;
        this.petOnClickListener = petOnClickListener;
    }

    public void setListaPets(List<Pet> listaPets) {
        this.listaPets = listaPets;
    }

    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
        inflate(R.layout.list_item_pet,parent,false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PetAdapter.ViewHolder holder, final int position) {
            holder.imagePet.setImageResource(listaPets.get(position).getImagemR());
            holder.textNomePet.setText(listaPets.get(position).getNomePet());
            holder.textNomeDono.setText(listaPets.get(position).getNomeDono());
            holder.textTelefone.setText(listaPets.get(position).getTelefone());

            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                            petOnClickListener.onClickPet(holder.itemView, position);
                        }
                    });
    }

    @Override
    public int getItemCount() {
        return listaPets.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagePet;
        public TextView textNomePet;
        public TextView textNomeDono;
        public TextView textTelefone;

        public ViewHolder(View view) {
                super(view);
                this.imagePet = view.findViewById(R.id.imagePet);
                this.textNomePet = view.findViewById(R.id.textNomePet);
                this.textNomeDono = view.findViewById(R.id.textNomeDono);
                this.textTelefone = view.findViewById(R.id.textTelefone);
        }
    }

    public interface PetOnClickListener{
        public void onClickPet(View view, int pos);
    }

}
