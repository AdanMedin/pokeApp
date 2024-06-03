package com.example.pokedex;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Objects;

public class DataActivity extends AppCompatActivity implements
        Animation.AnimationListener {

    TextView tvNevo, tvNumeroEvo, tvNumero,tvNombre,tvTipo1,tvTipo2,tvDebilidad1,tvDebilidad2,tvDebilidad3,tvDebilidad4,tvAltura,tvPeso,tvSexo,tvCategoria,tvHabilidad,tvEvolucion;
    ImageView ivPokemon,ivPokemonEvo,ivFondoResp;

    //Handler para la animación de parpadeo random de la luz roja
    Handler handler = new Handler();
    Pokemon pokemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //Inicia la toolbar
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //Elimina el titulo de la toolBar
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        //Añade boton ir atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        tvNevo = findViewById(R.id.tvNevo);
        tvNumeroEvo = findViewById(R.id.tvNumeroEvo);
        tvNumero = findViewById(R.id.tvNumero);
        tvNombre = findViewById(R.id.tvNombrePokemon);
        tvAltura = findViewById(R.id.tvAltura);
        tvPeso = findViewById(R.id.tvPeso);
        tvSexo = findViewById(R.id.tvSexo);
        tvCategoria = findViewById(R.id.tvCategoria);
        tvHabilidad = findViewById(R.id.tvHabilidad);
        tvEvolucion = findViewById(R.id.tvNombreEvo);
        tvTipo1 = findViewById(R.id.tvTipo1);
        tvTipo2 = findViewById(R.id.tv_tipo2);
        tvDebilidad1 = findViewById(R.id.tvDebilidad1);
        tvDebilidad2 = findViewById(R.id.tvDebilidad2);
        tvDebilidad3 = findViewById(R.id.tvDebilidad3);
        tvDebilidad4 = findViewById(R.id.tvDebilidad4);
        ivPokemon = findViewById(R.id.ivPokemon);
        ivPokemonEvo = findViewById(R.id.ivPokemonEvo);
        ivFondoResp = findViewById(R.id.ivFondoS);



        //Animación parpadeo random luz
        Animation a = AnimationUtils.loadAnimation (getApplicationContext(), R.anim.fade_i_o);

        final int[] timeRand = {(int) (Math.random() * 2000) + 1};

        handler.postDelayed(new Runnable() {

            public void run() {
                timeRand[0] = (int) (Math.random() * 500) + 1;

                ivFondoResp.startAnimation(a);

                handler.postDelayed(this, timeRand[0]);
            }

        }, timeRand[0]);

    }

    //Cargamos pokemon al iniciar el activity
    @Override
    protected void onStart() {
        super.onStart();
        //Tomamos el objeto pokemon enviado desde MainActivity, extraemos los datos y seteamos en el activity_data.
        //Para poder pasar el objeto pokemon, la clase contacto tine que implementar serializable.
        pokemon = (Pokemon) getIntent().getSerializableExtra("pokemon");
        setPokemon(pokemon);
    }

    /********************************** MÉTODOS ****************************************/

    //Metodo para setear el pokemon pasado
    public void setPokemon(Pokemon pokemon){
        String numPok = pokemon.getNumero();
        int imagenPokemon = pokemon.getImagen();

        Pokemon pokemonFull = loadPokemon(numPok,imagenPokemon);

        //Sets
        tvNumero.setText(pokemonFull.getNumero());
        tvNombre.setText(pokemonFull.getNombre());
        tvAltura.setText(pokemonFull.getAltura());
        tvPeso.setText(pokemonFull.getPeso());
        tvSexo.setText(pokemonFull.getSexo());
        tvCategoria.setText(pokemonFull.getCategoria());
        tvHabilidad.setText(pokemonFull.getHabilidad());
        tvEvolucion.setText(pokemonFull.getEvolucion());
        tvTipo1.setText(pokemonFull.getTipo1());
        tvTipo2.setText(pokemonFull.getTipo2());
        tvDebilidad1.setText(pokemonFull.getDebilidad1());
        tvDebilidad2.setText(pokemonFull.getDebilidad2());
        tvDebilidad3.setText(pokemonFull.getDebilidad3());
        tvDebilidad4.setText(pokemonFull.getDebilidad4());
        ivPokemon.setImageResource(pokemonFull.getImagen());
        ivPokemonEvo.setImageResource(pokemonFull.getImagenEvo());

        bgTipoSelector(pokemonFull);
        bgDebilidadSelector(pokemonFull);

        if (pokemonFull.getEvolucion() != null){

            int numEvo = Integer.parseInt(pokemonFull.getNumero()) + 1;
            String numeroEvo = "000" + numEvo;

            tvNevo.setText("nº: ");
            tvNumeroEvo.setText(numeroEvo);
        } else {
            tvNevo.setText("");
            tvNumeroEvo.setText("");
            ivPokemonEvo.setVisibility(View.INVISIBLE);
        }
    }

    //Metodo carga el pokemon seleccionado
    public Pokemon loadPokemon (String numPok, int imagenPokemon) {
        //Creamos conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "adminPokemon", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();//Modo lectura y escritura
        Pokemon pokemon = null;

        //Consulta
        Cursor fila = database.rawQuery("select p.* from pokemon p where numeropokemon =?", new String[]{numPok});

        //Cargamos las imagenes de los pokemon
        ArrayList<Integer> imagenesPokemon = pokemonImgLoad();

        int posicion = imagenesPokemon.indexOf(imagenPokemon);

        if (fila.moveToFirst()) {
            do {
                int imagenPokemonEvo;

                //Tomamos los datos y creamos un objeto pokemon que se guarda en el array para cada uno de los resultados obtenidos en con la consulta
                String numeropokemon = fila.getString(0);
                String nombre = fila.getString(1);
                String altura = fila.getString(2);
                String peso = fila.getString(3);
                String sexo = fila.getString(4);
                String categoria = fila.getString(5);
                String habilidad = fila.getString(6);
                String evolucion = fila.getString(7);
                String tipo1 = fila.getString(8);
                String tipo2 = fila.getString(9);
                String debilidad1 = fila.getString(10);
                String debilidad2= fila.getString(11);
                String debilidad3 = fila.getString(12);
                String debilidad4 = fila.getString(13);

                if(evolucion != null) {
                    imagenPokemonEvo = imagenesPokemon.get(posicion + 1);
                } else {
                    imagenPokemonEvo = R.drawable.pokeball;
                }

                pokemon = new Pokemon(numeropokemon,nombre,altura,peso,sexo,categoria,habilidad,evolucion,tipo1,tipo2,debilidad1,debilidad2,debilidad3,debilidad4,imagenPokemon,imagenPokemonEvo);

            } while (fila.moveToNext());
            database.close();

        } else {
            Toast.makeText(this, "No se ha podido acceder a la base de datos", Toast.LENGTH_SHORT).show();
            database.close();
        }
        database.close();
        fila.close();
        return pokemon;
    }

    //Metodo que cambia el background del textView Tipo según el tipo
    public void bgTipoSelector(Pokemon pokemon){
        if (pokemon.getTipo1().equals("Agua")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getTipo1().equals("Fuego")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getTipo1().equals("Eléctrico")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getTipo1().equals("Tierra")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getTipo1().equals("Roca")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getTipo1().equals("Hielo")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getTipo1().equals("Planta")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getTipo1().equals("Veneno")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getTipo1().equals("Bicho")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getTipo1().equals("Volador")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getTipo1().equals("Normal")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getTipo1().equals("Hada")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getTipo1().equals("Psíquico")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getTipo1().equals("Lucha")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getTipo1().equals("Acero")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getTipo1().equals("Fantasma")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getTipo1().equals("Dragón")){
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            tvTipo1.setBackgroundResource(R.drawable.background_tipo_no);
        }

        if (pokemon.getTipo2() != null && pokemon.getTipo2().equals("Agua")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Fuego")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Eléctrico")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Tierra")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Roca")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Hielo")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Planta")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Veneno")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Bicho")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Volador")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Normal")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Hada")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Psíquico")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Lucha")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Acero")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Fantasma")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getTipo2() != null && pokemon.getTipo2().equals("Dragón")){
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            tvTipo2.setBackgroundResource(R.drawable.background_tipo_no);
        }
    }

    //Metodo que cambia el background del textView debilidad según el tipo
    public void bgDebilidadSelector(Pokemon pokemon){
        if (pokemon.getDebilidad1().equals("Agua")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getDebilidad1().equals("Fuego")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getDebilidad1().equals("Eléctrico")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getDebilidad1().equals("Tierra")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getDebilidad1().equals("Roca")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getDebilidad1().equals("Hielo")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getDebilidad1().equals("Planta")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getDebilidad1().equals("Veneno")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getDebilidad1().equals("Bicho")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getDebilidad1().equals("Volador")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getDebilidad1().equals("Normal")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getDebilidad1().equals("Hada")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getDebilidad1().equals("Psíquico")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getDebilidad1().equals("Lucha")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getDebilidad1().equals("Acero")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getDebilidad1().equals("Fantasma")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getDebilidad1().equals("Dragón")){
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            tvDebilidad1.setBackgroundResource(R.drawable.background_tipo_no);
        }

        if (pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Agua")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Fuego")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Eléctrico")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Tierra")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Roca")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Hielo")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Planta")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Veneno")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Bicho")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Volador")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Normal")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Hada")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Psíquico")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Lucha")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Acero")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Fantasma")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getDebilidad2() != null && pokemon.getDebilidad2().equals("Dragón")){
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            tvDebilidad2.setBackgroundResource(R.drawable.background_tipo_no);
        }


        if (pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Agua")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Fuego")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Eléctrico")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Tierra")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Roca")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Hielo")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Planta")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Veneno")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Bicho")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Volador")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Normal")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Hada")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Psíquico")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Lucha")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Acero")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Fantasma")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getDebilidad3() != null && pokemon.getDebilidad3().equals("Dragón")){
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            tvDebilidad3.setBackgroundResource(R.drawable.background_tipo_no);
        }


        if (pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Agua")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_agua);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Fuego")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_fuego);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Eléctrico")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_electrico);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Tierra")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_tierra);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Roca")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_roca);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Hielo")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_hielo);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Planta")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_planta);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Veneno")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_veneno);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Bicho")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_bicho);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Volador")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_volador);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Normal")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_normal);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Hada")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_hada);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Psíquico")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_psiquico);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Lucha")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_lucha);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Acero")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_acero);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Fantasma")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_fantasma);
        } else if(pokemon.getDebilidad4() != null && pokemon.getDebilidad4().equals("Dragón")){
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_dragon);
        } else {
            tvDebilidad4.setBackgroundResource(R.drawable.background_tipo_no);
        }
    }

    //Metodo que carga las imagenes de los pokemon en un array
    public ArrayList<Integer> pokemonImgLoad(){

        ArrayList<Integer> imagenesPokemon = new ArrayList<>();
        imagenesPokemon.add(R.drawable.p1);
        imagenesPokemon.add(R.drawable.p2);
        imagenesPokemon.add(R.drawable.p3);
        imagenesPokemon.add(R.drawable.p4);
        imagenesPokemon.add(R.drawable.p5);
        imagenesPokemon.add(R.drawable.p6);
        imagenesPokemon.add(R.drawable.p7);
        imagenesPokemon.add(R.drawable.p8);
        imagenesPokemon.add(R.drawable.p9);
        imagenesPokemon.add(R.drawable.p10);
        imagenesPokemon.add(R.drawable.p11);
        imagenesPokemon.add(R.drawable.p12);
        imagenesPokemon.add(R.drawable.p13);
        imagenesPokemon.add(R.drawable.p14);
        imagenesPokemon.add(R.drawable.p15);
        imagenesPokemon.add(R.drawable.p16);
        imagenesPokemon.add(R.drawable.p17);
        imagenesPokemon.add(R.drawable.p18);
        imagenesPokemon.add(R.drawable.p19);
        imagenesPokemon.add(R.drawable.p20);
        imagenesPokemon.add(R.drawable.p21);
        imagenesPokemon.add(R.drawable.p22);
        imagenesPokemon.add(R.drawable.p23);
        imagenesPokemon.add(R.drawable.p24);
        imagenesPokemon.add(R.drawable.p25);
        imagenesPokemon.add(R.drawable.p26);
        imagenesPokemon.add(R.drawable.p27);
        imagenesPokemon.add(R.drawable.p28);
        imagenesPokemon.add(R.drawable.p29);
        imagenesPokemon.add(R.drawable.p30);
        imagenesPokemon.add(R.drawable.p31);
        imagenesPokemon.add(R.drawable.p32);
        imagenesPokemon.add(R.drawable.p33);
        imagenesPokemon.add(R.drawable.p34);
        imagenesPokemon.add(R.drawable.p35);
        imagenesPokemon.add(R.drawable.p36);
        imagenesPokemon.add(R.drawable.p37);
        imagenesPokemon.add(R.drawable.p38);
        imagenesPokemon.add(R.drawable.p39);
        imagenesPokemon.add(R.drawable.p40);
        imagenesPokemon.add(R.drawable.p41);
        imagenesPokemon.add(R.drawable.p42);
        imagenesPokemon.add(R.drawable.p43);
        imagenesPokemon.add(R.drawable.p44);
        imagenesPokemon.add(R.drawable.p45);
        imagenesPokemon.add(R.drawable.p46);
        imagenesPokemon.add(R.drawable.p47);
        imagenesPokemon.add(R.drawable.p48);
        imagenesPokemon.add(R.drawable.p49);
        imagenesPokemon.add(R.drawable.p50);
        imagenesPokemon.add(R.drawable.p51);

        return imagenesPokemon;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}