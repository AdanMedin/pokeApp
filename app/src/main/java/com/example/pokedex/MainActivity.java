package com.example.pokedex;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView aCText;
    PokemonAdapter adapter;
    ListView pokemonListView;
    ImageView ivFondoResp, btnExit;
    ImageButton ibPDex;

    //Handler para la animación de parpadeo random de la luz roja
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivFondoResp = findViewById(R.id.ivFondoS);
        pokemonListView = findViewById(R.id.listVPokemon);
        btnExit = findViewById(R.id.btnExit);
        ibPDex = findViewById(R.id.ibPDex);

        pokemonListView.setOnItemClickListener((parent, view, position, id) -> {

            //Guardamos el pokemon seleccionado para pasarlo a la siguiente activity
            Pokemon currentPokemon = adapter.getItem(position);

            Bundle extras = new Bundle();
            extras.putSerializable("pokemon", currentPokemon);

            //Iniciamos activity en la que mostraremos los datos
            Intent intent = new Intent(MainActivity.this, DataActivity.class);
            intent.putExtras(extras);
            startActivity(intent);

            //Si esta visible el boton regresar a lista de pokemon, lo hacemos invisible
            ibPDex.setVisibility(View.GONE);

        });

        //Boton exit función
        btnExit.setOnClickListener(v -> {
            Animation e = AnimationUtils.loadAnimation (getApplicationContext(), R.anim.click);
            btnExit.startAnimation(e);
            System.exit(0);
        });

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

        //Autocomplete text
        aCText = findViewById(R.id.acText);

        //Importamos las strings del xml creado
        String[] pokemonNames = getResources().getStringArray(R.array.pokName);
        //Adapter
        ArrayAdapter<String> adapterString = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, pokemonNames);
        aCText.setAdapter(adapterString);

        aCText.setOnItemClickListener((parent, v, position, id) -> {
            String currentPok = adapterString.getItem(position);
            ArrayList<Pokemon> pokemons = new ArrayList<>();

            Pokemon pok = loadPokemon(currentPok);

            pokemons.add(pok);

            //Instanciamos el adapter y le pasamos el array con el pokemon seleccionado en el autocompleteText
            adapter = new PokemonAdapter(this, pokemons);
            pokemonListView.setAdapter(adapter);
            ibPDex.setVisibility(View.VISIBLE);
            aCText.setText("");
            //Ocultamos teclado
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(aCText.getWindowToken(), 0);
        } );

        //Función ir a lista de pokemon de nuevo
        ibPDex.setOnClickListener(v -> {
            searchAll();
            ibPDex.setVisibility(View.GONE);
        });

    }

    //Cargamos pokemon de la base de datos al iniciar el activity
    @Override
    protected void onStart() {
        super.onStart();
        searchAll();
    }

    /********************************** MÉTODOS ****************************************/

    //Metodo carga todas pokemon en un array
    public ArrayList<Pokemon> loadData () {
        //Creamos conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "adminPokemon", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();//Modo lectura y escritura
        ArrayList<Pokemon> pokemons = new ArrayList<>();

        //Consulta
        Cursor fila = database.rawQuery("select numeropokemon,nombre,tipo1,tipo2 from pokemon", null);


        //Cargamos las imagenes de los pokemon
        ArrayList<Integer> imagenesPokemon = pokemonImgLoad();

        if (fila.moveToFirst()) {
            int contador = 0;
            do {
                //Identificador de las imagenes de los pokemon
                int imagenPokemon = imagenesPokemon.get(contador);

                //Tomamos los datos y creamos un objeto pokemon que se guarda en el array para cada uno de los resultados obtenidos en con la consulta
                String numeropokemon = fila.getString(0);
                String nombre = fila.getString(1);
                String tipo1 = fila.getString(2);
                String tipo2 = fila.getString(3);

                pokemons.add(new Pokemon(numeropokemon,nombre,tipo1,tipo2,imagenPokemon));

                contador++;

            } while (fila.moveToNext());

        } else {
            Toast.makeText(this, "No se ha podido acceder a la base de datos", Toast.LENGTH_SHORT).show();
        }
        database.close();
        fila.close();
        return pokemons;
    }

    //Metodo para consultar todos los pokemon
    public void searchAll() {
        ArrayList<Pokemon> pokemons;
        pokemons = loadData();

        //Instanciamos el adapter y le pasamos el array con los pokemon encontrados
        adapter = new PokemonAdapter(this, pokemons);
        pokemonListView.setAdapter(adapter);
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

    //Metodo carga el pokemon seleccionado en el autocompleteText
    public Pokemon loadPokemon (String nombrePok) {
        //Creamos conexión con la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "adminPokemon", null, 1);
        SQLiteDatabase database = admin.getWritableDatabase();//Modo lectura y escritura
        Pokemon pokemon = null;

        //Consulta
        Cursor fila = database.rawQuery("select numeropokemon,nombre,tipo1,tipo2 from pokemon where nombre =?", new String[]{nombrePok});

        //Cargamos las imagenes de los pokemon
        ArrayList<Integer> imagenesPokemon = pokemonImgLoad();

        if (fila.moveToFirst()) {
            do {
                //Tomamos los datos y creamos un objeto pokemon
                String numeropokemon = fila.getString(0);
                String nombre = fila.getString(1);
                String tipo1 = fila.getString(2);
                String tipo2 = fila.getString(3);

                //Para seleccionar la imagen que le corresponde
                int numPok = Integer.parseInt(numeropokemon);
                int imagenPokemon = imagenesPokemon.get(numPok - 1);

                pokemon = new Pokemon(numeropokemon,nombre,tipo1,tipo2,imagenPokemon);

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

}