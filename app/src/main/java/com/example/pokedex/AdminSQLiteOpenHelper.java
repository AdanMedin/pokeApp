package com.example.pokedex;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//Administra la base de datos
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        /**
        //Tabla tipos
        database.execSQL("create table tipos(" +
                "tipo text not null primary key)"
        );
        */

        //Tabla pokemon
        database.execSQL("create table pokemon(" +
                "numeropokemon text not null primary key," +
                "nombre text not null," +
                "altura text not null," +
                "peso text not null," +
                "sexo text," +
                "categoria text not null," +
                "habilidad text not null," +
                "evolucion text," +
                "tipo1 text not null," +
                "tipo2 text," +
                "debilidad1 text not null," +
                "debilidad2 text," +
                "debilidad3 text," +
                "debilidad4 text)"
        );

        /**
        //Tabla evoluciones
        database.execSQL("create table evoluciones(" +
                "numeropokemon text not null," +
                "evo text," +
                "constraint fk_numeropokemon_evoluciones foreign key (numeropokemon) references pokemon(numeropokemon) on delete cascade on update cascade," +
                "constraint fk_evo_evoluciones foreign key (nombre) references pokemon(nombre) on delete cascade on update cascade)"
        );


        //Tabla tipos_pokemon
        database.execSQL("create table tipos_pokemon(" +
                "numeropokemon text not null," +
                "tipo1 text not null," +
                "tipo2 text," +
                "constraint fk_numeropokemon_tipos_pokemon foreign key (numeropokemon) references pokemon(numeropokemon) on delete cascade on update cascade," +
                "constraint fk_tipo1_tipos_pokemon foreign key (tipo) references tipos(tipo) on delete cascade on update cascade," +
                "constraint fk_tipo2_tipos_pokemon foreign key (tipo) references tipos(tipo) on delete cascade on update cascade)"
        );

        //Tabla debilidades_pokemon
        database.execSQL("create table debilidades_pokemon(" +
                "numeropokemon text," +
                "d1 text not null," +
                "d2 text," +
                "d3 text," +
                "d4 text," +
                "constraint fk_numeropokemon_debilidades_pokemon foreign key (numeropokemon) references pokemon(numeropokemon) on delete cascade on update cascade," +
                "constraint fk_d1_debilidades_pokemon foreign key (tipo) references tipos(tipo) on delete cascade on update cascade," +
                "constraint fk_d2_debilidades_pokemon foreign key (tipo) references tipos(tipo) on delete cascade on update cascade," +
                "constraint fk_d3_debilidades_pokemon foreign key (tipo) references tipos(tipo) on delete cascade on update cascade," +
                "constraint fk_d4_debilidades_pokemon foreign key (tipo) references tipos(tipo) on delete cascade on update cascade)"
        );

        database.execSQL("insert into tipos(tipo)" +
                "values('Agua')," +
                "('Fuego')," +
                "('Eléctrico')," +
                "('Tierra')," +
                "('Roca')," +
                "('Hielo')," +
                "('Planta')," +
                "('Veneno')," +
                "('Bicho')," +
                "('Volador')," +
                "('Normal')," +
                "('Hada')," +
                "('Psíquico')," +
                "('Lucha')," +
                "('Acero')," +
                "('Fantasma')," +
                "('Dragón')"
        );

         */

        database.execSQL("insert into pokemon(numeropokemon,nombre,altura,peso,sexo,categoria,habilidad,evolucion,tipo1,tipo2,debilidad1,debilidad2,debilidad3,debilidad4)" +
                "values('0001','Bulbasaur','0,7 m','6,9 kg','♀/♂','Semilla','Espesura','Ivysaur','Planta','Veneno','Fuego','Psíquico','Volador','Hielo')," +
                "('0002','Ivysaur','1,0 m','13,0 kg','♀/♂','Semilla','Espesura','Venusaur','Planta','Veneno','Fuego','Psíquico','Volador','Hielo')," +
                "('0003','Venusaur','2,0 m','100,0 kg','♀/♂','Semilla','Espesura',null,'Planta','Veneno','Fuego','Psíquico','Volador','Hielo')," +
                "('0004','Charmander','0,6 m','8,5 kg','♀/♂','Lagartija','Mar Llamas','Charmeleon','Fuego',null,'Agua','Tierra','Roca',null)," +
                "('0005','Charmeleon','1,1 m','19,0 kg','♀/♂','Llama','Mar Llamas','Charizard','Fuego',null,'Agua','Tierra','Roca',null)," +
                "('0006','Charizard','1,7 m','90,5 kg','♀/♂','Llama','Mar Llamas',null,'Fuego','Volador','Agua','Eléctrico','Roca',null)," +
                "('0007','Squirtle','0,5 m','9,0 kg','♀/♂','Tortuguita','Torrente','Wartortle','Agua',null,'Planta','Eléctrico',null,null)," +
                "('0008','Wartortle','1,0 m','22,5 kg','♀/♂','Tortuga','Torrente','Blastoise','Agua',null,'Planta','Eléctrico',null,null)," +
                "('0009','Blastoise','1,6 m','85,5 kg','♀/♂','Armazón','Torrente',null,'Agua',null,'Planta','Eléctrico',null,null)," +
                "('0010','Caterpie','0,3 m','2,9 kg','♀/♂','Gusano','Polvo Escudo','Metapod','Bicho',null,'Fuego','Volador','Roca',null)," +
                "('0011','Metapod','0,7 m','9,9 kg','♀/♂','Capullo','Mudar','Butterfree','Bicho',null,'Fuego','Volador','Roca',null)," +
                "('0012','Butterfree','1,1 m','32,0 kg','H/M','Mariposa','Ojo Compuesto',null,'Bicho','Volador','Fuego','Volador','Roca','Hielo')," +
                "('0013','Weedle','0,3 m','3,2 kg','♀/♂','Oruga','Polvo Escudo','Kakuna','Bicho','Veneno','Fuego','Volador','Roca','Psíquico')," +
                "('0014','Kakuna','0,6 m','10,0 kg','♀/♂','Capullo','Mudar','Beedrill','Bicho','Veneno','Fuego','Volador','Roca','Psíquico')," +
                "('0015','Beedrill','1,0 m','29,5 kg','♀/♂','Abeja Veneno','Enjambre',null,'Bicho','Veneno','Fuego','Volador','Roca','Psíquico')," +
                "('0016','Pidgey','0,3 m','1,8 kg','♀/♂','Pajarito','Vista Lince, Tumbos','Pidgeotto','Normal','Volador','Eléctrico','Hielo','Roca',null)," +
                "('0017','Pidgeotto','1,1 m','30,0kg','♀/♂','Pájaro','Vista Lince, Tumbos','Pidgeot','Normal','Volador','Eléctrico','Hielo','Roca',null)," +
                "('0018','Pidgeot','1,5 m','39,5 kg','♀/♂','Pájaro','Vista Lince, Tumbos',null,'Normal','Volador','Eléctrico','Hielo','Roca',null)," +
                "('0019','Rattata','0,3 m','3,5 kg','♀/♂','Ratón','Fuga, Agallas','Raticate','Normal',null,'Lucha',null,null,null)," +
                "('0020','Raticate','0,7 m','18,5 kg','♀/♂','Ratón','Fuga, Agallas',null,'Normal',null,'Lucha',null,null,null)," +
                "('0021','Spearow','0,3 m','2,0 kg','♀/♂','Pajarito','Vista Lince','Fearow','Normal','Volador','Eléctrico','Hielo','Roca',null)," +
                "('0022','Fearow','1,2 m','38,0 kg','♀/♂','Pico','Vista Lince',null,'Normal','Volador','Eléctrico','Hielo','Roca',null)," +
                "('0023','Ekans','2,0 m','6,9 kg','♀/♂','Serpiente','Mudar, Intimidación','Arbok','Veneno',null,'Psíquico','Tierra',null,null)," +
                "('0024','Arbok','3,5 m','65,0 kg','♀/♂','Cobra','Mudar, Intimidación',null,'Veneno',null,'Psíquico','Tierra',null,null)," +
                "('0025','Pikachu','0,4 m','6,0 kg','♀/♂','Ratón','Elec. Estática','Raichu','Eléctrico',null,'Tierra',null,null,null)," +
                "('0026','Raichu','0,8 m','30,0 kg','♀/♂','Ratón','Elec. Estática',null,'Eléctrico',null,'Tierra',null,null,null)," +
                "('0027','Sandshrew','0,6 m','12,0 kg','♀/♂','Ratón','Velo Arena','Sandslash','Tierra',null,'Agua','Planta','Hielo',null)," +
                "('0028','Sandslash','1,0 m','29,5 kg','♀/♂','Ratón','Velo Arena',null,'Tierra',null,'Agua','Planta','Hielo',null)," +
                "('0029','Nidoran♀','0,4 m','7,0 kg','♀','Pin Veneno','P. Tóxico, Rivalidad','Nidorina','Veneno',null,'Psíquico','Tierra',null,null)," +
                "('0030','Nidorina','0,9 m','20,0 kg','♀','Pin Veneno','P. Tóxico, Rivalidad','Nidoqueen','Veneno',null,'Psíquico','Tierra',null,null)," +
                "('0031','Nidoqueen','1,3 m','60,0 kg','♀','Taladro','P. Tóxico, Rivalidad',null,'Veneno','Tierra','Psíquico','Tierra','Agua','Hielo')," +
                "('0032','Nidoran♂','0,5 m','9,0 kg','♂','Pin Veneno','P. Tóxico, Rivalidad','Nidorino','Veneno',null,'Psíquico','Tierra',null,null)," +
                "('0033','Nidorino','0,9 m','19,5 kg','♂','Pin Veneno','P. Tóxico, Rivalidad','Nidoking','Veneno',null,'Psíquico','Tierra',null,null)," +
                "('0034','Nidoking','1,4 m','62,0 kg','♂','Taladro','P. Tóxico, Rivalidad','Nidorino','Veneno','Tierra','Psíquico','Tierra','Agua','Hielo')," +
                "('0035','Clefairy','0,6 m','7,5 kg','♀/♂','Hada','G. Encanto, M. Mágico','Clefable','Hada','Acero','Veneno',null,null,null)," +
                "('0036','Clefable','1,3 m','40,0 kg','♀/♂','Hada','G. Encanto, M. Mágico',null,'Hada','Acero','Veneno',null,null,null)," +
                "('0037','Vulpix','0,6 m','9,9 kg','♀/♂','Zorro','Absorbe Fuego','Ninetales','Fuego',null,'Tierra','Roca','Agua',null)," +
                "('0038','Ninetales','1,1 m','19,9 kg','♀/♂','Zorro','Absorbe Fuego',null,'Fuego',null,'Tierra','Roca','Agua',null)," +
                "('0039','Jigglypuff','0,5 m','5,5 kg','♀/♂','Globo','G. Encanto, Tenacidad','Wigglytuff','Normal','Hada','Acero','Veneno',null,null)," +
                "('0040','Wigglytuff','1,0 m','12,0 kg','♀/♂','Globo','G. Encanto, Tenacidad',null,'Normal','Hada','Acero','Veneno',null,null)," +
                "('0041','Zubat','0,8 m','7,5 kg','♀/♂','Murciélago','Fuerza Mental','Golbat','Veneno','Volador','Psíquico','Eléctrico','Hielo','Roca')," +
                "('0042','Golbat','1,6 m','55,0 kg','♀/♂','Murciélago','Fuerza Mental',null,'Veneno','Volador','Psíquico','Eléctrico','Hielo','Roca')," +
                "('0043','Oddish','0,5 m','5,4 kg','♀/♂','Hierbajo','Clorofila','Gloom','Planta','Veneno','Fuego','Psíquico','Hielo','Volador')," +
                "('0044','Gloom','0,8 m','8,6 kg','♀/♂','Hierbajo','Clorofila','Vileplume','Planta','Veneno','Fuego','Psíquico','Hielo','Volador')," +
                "('0045','Vileplume','1,2 m','18,6 kg','♀/♂','Flor','Clorofila',null,'Planta','Veneno','Fuego','Psíquico','Hielo','Volador')," +
                "('0046','Paras','0,3 m','5,4 kg','♀/♂','Hongo','E. Espora, Piel Seca','Parasect','Bicho','Planta','Fuego','Volador','Hielo','Veneno')," +
                "('0047','Parasect','1,0 m','29,5 kg','♀/♂','Hongo','E. Espora, Piel Seca',null,'Bicho','Planta','Fuego','Volador','Hielo','Veneno')," +
                "('0048','Venonat','1,0 m','30,0 kg','♀/♂','Insecto','Ojo comp., Cromolente','Venomoth','Bicho','Veneno','Fuego','Volador','Psíquico','Roca')," +
                "('0049','Venomoth','1,5 m','12,5 kg','♀/♂','Polilla Venenosa','Polvo Esc., Cromolente',null,'Bicho','Veneno','Fuego','Volador','Psíquico','Roca')," +
                "('0050','Diglett','0,2 m','0,8 kg','♀/♂','Topo','V. Arena, T. Arena','Dugtrio','Tierra',null,'Agua','Planta','Hielo',null)," +
                "('0051','Dugtrio','0,7 m','33,3 kg','♀/♂','Topo','V. Arena, T. Arena','Dugtrio','Tierra',null,'Agua','Planta','Hielo',null)"


        );

        /**

        database.execSQL("insert into evoluciones(numeropokemon,evo)" +
                "values('0001','Ivysaur')," +
                "('0002','Venusaur')," +
                "('0004','Charmeleon')," +
                "('0005','Charizard')," +
                "('0007','Wartortle')," +
                "('0008','Blastoise')"
        );

        database.execSQL("insert into tipos_pokemon(numeropokemon,tipo1,tipo2)" +
                "values('0001','Planta','Veneno')," +
                "('0002','Planta','Veneno')," +
                "('0003','Planta','Veneno')," +
                "('0004','Fuego')," +
                "('0005','Fuego')," +
                "('0006','Fuego','Volador')," +
                "('0007','Agua')," +
                "('0008','Agua')," +
                "('0009','Agua')"
        );

        database.execSQL("insert into debilidades_pokemon(numeropokemon,d1,d2,d3,d4)" +
                "values('0001','Fuego','Psíquico','Volador','Hielo')," +
                "('0002','Fuego','Psíquico','Volador','Hielo')," +
                "('0003','Fuego','Psíquico','Volador','Hielo')," +
                "('0004','Agua','Tierra','Roca')," +
                "('0005','Agua','Tierra','Roca')," +
                "('0006','Agua','Eléctrico','Roca')," +
                "('0007','Planta','Eléctrico')," +
                "('0008','Planta','Eléctrico')," +
                "('0009','Planta','Eléctrico')"
        );
         */

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        onUpgrade(database, oldVersion, newVersion);
    }

}
