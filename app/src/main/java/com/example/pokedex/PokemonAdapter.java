package com.example.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class PokemonAdapter extends ArrayAdapter<Pokemon> {

    public PokemonAdapter(@NonNull Context context, @NonNull List<Pokemon> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Obtener inflater
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Creamos HolderView
        PokemonAdapter.ViewHolder holder;

        //Comprobamos si ya está infada la view
        if (convertView == null) {

            //Si no existe, entonces inflamos con item_cita.xml
            convertView = inflater.inflate(R.layout.item_pokemon, parent, false);

            //Inicializamos HolderView
            holder = new PokemonAdapter.ViewHolder();

            //Referencias
            holder.n = convertView.findViewById(R.id.tv_numero);
            holder.tvNumero = convertView.findViewById(R.id.tv_N);
            holder.tvNombre = convertView.findViewById(R.id.tv_nombre);
            holder.tvTipo1 = convertView.findViewById(R.id.tv_tipo1);
            holder.tvTipo2 = convertView.findViewById(R.id.tv_tipo2);
            holder.ivPokemon = convertView.findViewById(R.id.iv_pokemon);

            convertView.setTag(holder);

        } else {
            holder = (PokemonAdapter.ViewHolder) convertView.getTag();
        }

        //Pokemon actual
        Pokemon pokemon = getItem(position);

        //Setup
        holder.n.setText("nº:");
        holder.tvNumero.setText(pokemon.getNumero());
        holder.tvNombre.setText(pokemon.getNombre());
        holder.tvTipo1.setText(pokemon.getTipo1());
        holder.tvTipo2.setText(pokemon.getTipo2());
        holder.ivPokemon.setImageResource(pokemon.getImagen());


        //Cambiamos el background del textView Tipo según el tipo
        if (pokemon.getTipo1().equals("Agua")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getTipo1().equals("Fuego")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getTipo1().equals("Eléctrico")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getTipo1().equals("Tierra")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getTipo1().equals("Roca")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getTipo1().equals("Hielo")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getTipo1().equals("Planta")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getTipo1().equals("Veneno")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getTipo1().equals("Bicho")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getTipo1().equals("Volador")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getTipo1().equals("Normal")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getTipo1().equals("Hada")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getTipo1().equals("Psíquico")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getTipo1().equals("Lucha")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getTipo1().equals("Acero")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getTipo1().equals("Fantasma")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getTipo1().equals("Dragón")){
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            holder.tvTipo1.setBackgroundResource(R.drawable.background_tipo_no);
        }

        if (pokemon.getTipo2() != null && pokemon.getTipo2().equals("Agua")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Fuego")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Eléctrico")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Tierra")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Roca")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Hielo")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Planta")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Veneno")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Bicho")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Volador")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Normal")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Hada")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Psíquico")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Lucha")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Acero")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Fantasma")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Dragón")){
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            holder.tvTipo2.setBackgroundResource(R.drawable.background_tipo_no);
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvNumero;
        TextView n;
        TextView tvNombre;
        TextView tvTipo1;
        TextView tvTipo2;
        ImageView ivPokemon;
    }
}
